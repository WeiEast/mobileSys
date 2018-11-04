package bl;

import entity.CallFee;
import entity.CallPlanUsage;
import entity.Plan;
import entity.UserPlan;
import mapper.CallFeeMapper;
import mapper.CallPlanUseMapper;
import mapper.PlanMapper;
import mapper.UserPlanMapper;
import org.apache.ibatis.session.SqlSession;
import service.CallFeeService;
import utils.DBUtil;
import utils.DateUtil;
import utils.DefaultPlanUtil;

import java.util.*;

public class CallFeeServiceImpl implements CallFeeService {
    public void createCallFee(Long uid, Integer time){
        createCallFee(uid,time , new Date());
    }

    public void createCallFee(Long uid, Integer time, Date begin) {
        SqlSession session = DBUtil.openSqlSession();

        CallFee callFee = new CallFee();
        List<CallPlanUsage> callPlanUsages = new ArrayList<>();

        Plan plan = null;
        int overTime = 0;

        //轮询套餐,生成消费情况
        UserPlanMapper userPlanMapper = session.getMapper(UserPlanMapper.class);
        List<UserPlan> userPlans = userPlanMapper.listUserPlan(uid);
        if (userPlans != null){
            //消耗免费时间
            overTime = consumeFree(userPlans, time, userPlanMapper, callPlanUsages);
            //若time还有剩余 就收费
            if (overTime > 0){
                plan = findPlanWithMinOverCost(userPlans);
            }

        }

        //如果没有订购任何套餐,使用默认套餐
        if (plan==null){plan = DefaultPlanUtil.getDefaultPlan();}
        CallPlanUsage usage = findAndRemoveUsage(callPlanUsages, plan.getPid());
        double cost = overTime*plan.getOverCallCost();
        if (usage==null){usage = new CallPlanUsage();}
        usage.setPid(plan.getPid());
        usage.setPname(plan.getName());
        usage.setOverTime(overTime);
        usage.setCost(cost);
        callPlanUsages.add(usage);


        //构造通话资费信息
        callFee.setBeginDate(begin);
        callFee.setUid(uid);
        callFee.setEndDate(DateUtil.addMin(begin, time));
        callFee.setCost(cost);
        CallFeeMapper callFeeMapper = session.getMapper(CallFeeMapper.class);
        callFeeMapper.insertCallFee(callFee);
        Long cfid = callFee.getCfid();

        CallPlanUseMapper callPlanUseMapper = session.getMapper(CallPlanUseMapper.class);
        callPlanUsages.forEach(x->{
            x.setCfid(cfid);
            callPlanUseMapper.insert(x);
        });
    }

    public String getCallFeeByUserId(Long uid) {
        String result = "";

        SqlSession session = DBUtil.openSqlSession();
        CallFeeMapper callFeeMapper = session.getMapper(CallFeeMapper.class);
        CallPlanUseMapper callPlanUseMapper = session.getMapper(CallPlanUseMapper.class);

        CallFee callFee = callFeeMapper.selectCallFeeByUserId(uid);
        List<CallPlanUsage> callPlanUsages = callPlanUseMapper.listCallPlanUsages(callFee.getCfid());
        result += callFee.toString()+"\n";

        if (callPlanUsages != null){
            for (CallPlanUsage cp: callPlanUsages){
                result += cp.toString()+ "\n";
            }
        }
        else {result += "未使用套餐";}

        return result;
    }

    public List<String> listConcreteCallFeeByUserId(Long uid, int times) {
//        SqlSession session = DBUtil.openSqlSession();
//        CallFeeMapper mapper = session.getMapper(CallFeeMapper.class);
//        List<> mapper.listConcreteCallFeeByUserId(uid, times);
        return null;
    }

    private Integer consumeFree(List<UserPlan> userPlans, Integer time,
                                UserPlanMapper userPlanMapper,
                                List<CallPlanUsage> callPlanUsages){
        //消耗免费时间
        for (UserPlan plan: userPlans){
            Integer leftCall = plan.getLeftCall();
            if (leftCall != null && leftCall>0){
                Integer consumeVal = Math.min(leftCall, time);
                leftCall -= consumeVal;
                time -= consumeVal;
                //更新套餐用量
                plan.setLeftCall(leftCall);
                userPlanMapper.updateUserPlan(plan);

                CallPlanUsage usage = new CallPlanUsage();
                usage.setPid(plan.getPid());
                usage.setFreeTime(consumeVal);
                callPlanUsages.add(usage);

                //若time已经消耗完,则退出
                if (time==0){return time;}
            }
        }
        return time;
    }

    private Plan findPlanWithMinOverCost(List<UserPlan> userPlans){
        SqlSession session = DBUtil.openSqlSession();
        PlanMapper planMapper = session.getMapper(PlanMapper.class);
        for(UserPlan userPlan: userPlans){

        }
        Optional<Plan> opMinVal = userPlans.stream()
                .map(userPlan->planMapper.selectPlanByPlanId(userPlan.getPid()))
                .filter(x->x.getOverCallCost()!=null)
                .min((x,y)->(int)(x.getOverCallCost()*10-y.getOverCallCost()*10));

        if (opMinVal.isPresent()){
            return opMinVal.get();
        }
        return null;
    }

    private CallPlanUsage findAndRemoveUsage(List<CallPlanUsage> usages, Long pid){
        CallPlanUsage tar = null;
        for (CallPlanUsage usage: usages){
            if (pid.equals(usage.getPid())){
                tar =  usage;
            }
        }
        if (tar!=null){usages.remove(tar);}
        return tar;
    }

}
