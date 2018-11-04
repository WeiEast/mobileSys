package bl;

import entity.NationalFlowFee;
import entity.NationalFlowPlanUsage;
import entity.Plan;
import entity.UserPlan;
import mapper.NationalFlowFeeMapper;
import mapper.NationalFlowPlanUseMapper;
import mapper.PlanMapper;
import mapper.UserPlanMapper;
import org.apache.ibatis.session.SqlSession;
import service.NationalFlowFeeService;
import utils.DBUtil;
import utils.DateUtil;
import utils.DefaultPlanUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NationalFlowFeeServiceImpl implements NationalFlowFeeService {
    @Override
    public void createNationalFlowFee(Long uid, Double volume, Integer time) {
        createNationalFlowFee(uid,volume , new Date(), time);
    }

    @Override
    public void createNationalFlowFee(Long uid, Double volume, Date begin, Integer time) {
        SqlSession session = DBUtil.openSqlSession();

        NationalFlowFee nationalFlowFee = new NationalFlowFee();
        List<NationalFlowPlanUsage> nationalFlowPlanUsages = new ArrayList<>();

        Plan plan = null;
        double overTime = 0;

        //轮询套餐,生成消费情况
        UserPlanMapper userPlanMapper = session.getMapper(UserPlanMapper.class);
        List<UserPlan> userPlans = userPlanMapper.listUserPlan(uid);
        if (userPlans != null){
            //消耗免费时间
            overTime = consumeFree(userPlans, volume, userPlanMapper, nationalFlowPlanUsages);
            //若time还有剩余 就收费
            if (overTime > 0){
                plan = findPlanWithMinOverCost(userPlans);
            }

        }

        //如果没有订购任何套餐,使用默认套餐
        if (plan==null){plan = DefaultPlanUtil.getDefaultPlan();}
        NationalFlowPlanUsage usage = findAndRemoveUsage(nationalFlowPlanUsages, plan.getPid());
        double cost = overTime*plan.getOverNationalCost();
        if (usage==null){usage = new NationalFlowPlanUsage();}
        usage.setPid(plan.getPid());
        usage.setPname(plan.getName());
        usage.setOverVolume(overTime);
        usage.setCost(cost);
        nationalFlowPlanUsages.add(usage);


        //构造通话资费信息
        nationalFlowFee.setBeginDate(begin);
        nationalFlowFee.setUid(uid);
        nationalFlowFee.setEndDate(DateUtil.addMin(begin, time));
        nationalFlowFee.setCost(cost);
        NationalFlowFeeMapper NationalFlowFeeMapper = session.getMapper(NationalFlowFeeMapper.class);
        NationalFlowFeeMapper.insertNationalFlowFee(nationalFlowFee);
        Long cfid = nationalFlowFee.getNffid();

        NationalFlowPlanUseMapper nationalFlowPlanUseMapper = session.getMapper(NationalFlowPlanUseMapper.class);
        nationalFlowPlanUsages.forEach(x->{
            x.setNffid(cfid);
            nationalFlowPlanUseMapper.insert(x);
        });
    }

    @Override
    public String getNationalFlowFeeByUserId(Long uid) {
        String result = "";

        SqlSession session = DBUtil.openSqlSession();
        NationalFlowFeeMapper nationalFeeMapper = session.getMapper(NationalFlowFeeMapper.class);
        NationalFlowPlanUseMapper nationalPlanUseMapper = session.getMapper(NationalFlowPlanUseMapper.class);

        NationalFlowFee nationalFee = nationalFeeMapper.selectNationalFlowFeeByUserId(uid);
        List<NationalFlowPlanUsage> nationalPlanUsages = nationalPlanUseMapper
                .listNationalFlowPlanUsages(nationalFee.getNffid());
        result += nationalFee.toString()+"\n";

        if (nationalPlanUsages != null){
            for (NationalFlowPlanUsage cp: nationalPlanUsages){
                result += cp.toString()+ "\n";
            }
        }
        else {result += "未使用套餐";}

        return result;
    }

    @Override
    public List<String> listConcreteNationalFlowFeeByUserId(Long uid, int times) {
        return null;
    }

    private Double consumeFree(List<UserPlan> userPlans, Double volume,
                               UserPlanMapper userPlanMapper,
                               List<NationalFlowPlanUsage> NationalFlowPlanUsages){
        //消耗免费时间
        for (UserPlan plan: userPlans){
            Double leftNationalFlow = plan.getLeftNFlow();
            if (leftNationalFlow != null && leftNationalFlow>0){
                Double consumeVal = Math.min(leftNationalFlow, volume);
                leftNationalFlow -= consumeVal;
                volume -= consumeVal;
                //更新套餐用量
                plan.setLeftLFlow(leftNationalFlow);
                userPlanMapper.updateUserPlan(plan);

                NationalFlowPlanUsage usage = new NationalFlowPlanUsage();
                usage.setPid(plan.getPid());
                usage.setFreeVolume(consumeVal);
                NationalFlowPlanUsages.add(usage);

                //若volume已经消耗完,则退出
                if (volume==0){return volume;}
            }
        }
        return volume;
    }

    private Plan findPlanWithMinOverCost(List<UserPlan> userPlans){
        SqlSession session = DBUtil.openSqlSession();
        PlanMapper planMapper = session.getMapper(PlanMapper.class);
        for(UserPlan userPlan: userPlans){

        }
        Optional<Plan> opMinVal = userPlans.stream()
                .map(userPlan->planMapper.selectPlanByPlanId(userPlan.getPid()))
                .filter(x->x.getOverNationalCost()!=null)
                .min((x,y)->(int)(x.getOverNationalCost()*10-y.getOverNationalCost()*10));

        if (opMinVal.isPresent()){
            return opMinVal.get();
        }
        return null;
    }

    private NationalFlowPlanUsage findAndRemoveUsage(List<NationalFlowPlanUsage> usages, Long pid){
        NationalFlowPlanUsage tar = null;
        for (NationalFlowPlanUsage usage: usages){
            if (pid.equals(usage.getPid())){
                tar =  usage;
            }
        }
        if (tar!=null){usages.remove(tar);}
        return tar;
    }

}
