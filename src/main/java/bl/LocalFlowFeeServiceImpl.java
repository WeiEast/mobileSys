package bl;

import entity.*;
import mapper.*;
import org.apache.ibatis.session.SqlSession;
import service.LocalFlowFeeService;
import utils.DBUtil;
import utils.DateUtil;
import utils.DefaultPlanUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LocalFlowFeeServiceImpl implements LocalFlowFeeService {
    @Override
    public void createLocalFlowFee(Long uid, Double volume, Integer time) {
        createLocalFlowFee(uid,volume , new Date(), time);
    }

    @Override
    public void createLocalFlowFee(Long uid, Double volume, Date begin, Integer time) {
        SqlSession session = DBUtil.openSqlSession();

        LocalFlowFee localFlowFee = new LocalFlowFee();
        List<LocalFlowPlanUsage> LocalFlowPlanUsages = new ArrayList<>();

        Plan plan = null;
        double overTime = 0;

        //轮询套餐,生成消费情况
        UserPlanMapper userPlanMapper = session.getMapper(UserPlanMapper.class);
        List<UserPlan> userPlans = userPlanMapper.listUserPlan(uid);
        if (userPlans != null){
            //消耗免费时间
            overTime = consumeFree(userPlans, volume, userPlanMapper, LocalFlowPlanUsages);
            //若time还有剩余 就收费
            if (overTime > 0){
                plan = findPlanWithMinOverCost(userPlans);
            }

        }

        //如果没有订购任何套餐,使用默认套餐
        if (plan==null){plan = DefaultPlanUtil.getDefaultPlan();}
        LocalFlowPlanUsage usage = findAndRemoveUsage(LocalFlowPlanUsages, plan.getPid());
        double cost = overTime*plan.getOverLocalCost();
        if (usage==null){usage = new LocalFlowPlanUsage();}
        usage.setPid(plan.getPid());
        usage.setPname(plan.getName());
        usage.setOverVolume(overTime);
        usage.setCost(cost);
        LocalFlowPlanUsages.add(usage);


        //构造通话资费信息
        localFlowFee.setBeginDate(begin);
        localFlowFee.setUid(uid);
        localFlowFee.setEndDate(DateUtil.addMin(begin, time));
        localFlowFee.setCost(cost);
        LocalFlowFeeMapper LocalFlowFeeMapper = session.getMapper(LocalFlowFeeMapper.class);
        LocalFlowFeeMapper.insertLocalFlowFee(localFlowFee);
        Long cfid = localFlowFee.getLffid();

        LocalFlowPlanUseMapper LocalFlowPlanUseMapper = session.getMapper(LocalFlowPlanUseMapper.class);
        LocalFlowPlanUsages.forEach(x->{
            x.setLffid(cfid);
            LocalFlowPlanUseMapper.insert(x);
        });
    }

    @Override
    public String getLocalFlowFeeByUserId(Long uid) {
        String result = "";

        SqlSession session = DBUtil.openSqlSession();
        LocalFlowFeeMapper localFeeMapper = session.getMapper(LocalFlowFeeMapper.class);
        LocalFlowPlanUseMapper localPlanUseMapper = session.getMapper(LocalFlowPlanUseMapper.class);

        LocalFlowFee LocalFee = localFeeMapper.selectLocalFlowFeeByUserId(uid);
        List<LocalFlowPlanUsage> LocalPlanUsages = localPlanUseMapper
                .listLocalFlowPlanUsages(LocalFee.getLffid());
        result += LocalFee.toString()+"\n";

        if (LocalPlanUsages != null){
            for (LocalFlowPlanUsage cp: LocalPlanUsages){
                result += cp.toString()+ "\n";
            }
        }
        else {result += "未使用套餐";}

        return result;
    }

    @Override
    public List<String> listConcreteLocalFlowFeeByUserId(Long uid, int times) {
        return null;
    }

    private Double consumeFree(List<UserPlan> userPlans, Double volume,
                                UserPlanMapper userPlanMapper,
                                List<LocalFlowPlanUsage> localFlowPlanUsages){
        //消耗免费时间
        for (UserPlan plan: userPlans){
            Double leftLocalFlow = plan.getLeftLFlow();
            if (leftLocalFlow != null && leftLocalFlow>0){
                Double consumeVal = Math.min(leftLocalFlow, volume);
                leftLocalFlow -= consumeVal;
                volume -= consumeVal;
                //更新套餐用量
                plan.setLeftLFlow(leftLocalFlow);
                userPlanMapper.updateUserPlan(plan);

                LocalFlowPlanUsage usage = new LocalFlowPlanUsage();
                usage.setPid(plan.getPid());
                usage.setFreeVolume(consumeVal);
                localFlowPlanUsages.add(usage);

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
                .filter(x->x.getOverLocalCost()!=null)
                .min((x,y)->(int)(x.getOverLocalCost()*10-y.getOverLocalCost()*10));

        if (opMinVal.isPresent()){
            return opMinVal.get();
        }
        return null;
    }

    private LocalFlowPlanUsage findAndRemoveUsage(List<LocalFlowPlanUsage> usages, Long pid){
        LocalFlowPlanUsage tar = null;
        for (LocalFlowPlanUsage usage: usages){
            if (pid.equals(usage.getPid())){
                tar =  usage;
            }
        }
        if (tar!=null){usages.remove(tar);}
        return tar;
    }

}
