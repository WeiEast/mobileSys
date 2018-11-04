package utils;

import entity.Plan;
import mapper.PlanMapper;
import org.apache.ibatis.session.SqlSession;

public class DefaultPlanUtil {
    private static final Long DEFAULT_PID = 1L;
    private static Plan defaultPlan;

    private DefaultPlanUtil(){}

    private static void init(){
        SqlSession session = DBUtil.openSqlSession();
        PlanMapper mapper = session.getMapper(PlanMapper.class);
        Plan plan = mapper.selectPlanByPlanId(DEFAULT_PID);
        defaultPlan = plan;
    }

    public static Plan getDefaultPlan(){
        if (defaultPlan==null){init();}
        return defaultPlan;
    }

    public static double getDefaultOverCallCost(){
        Plan plan = getDefaultPlan();
        return plan.getOverCallCost();
    }

    public static double getDefaultOverMessageCost(){
        Plan plan = getDefaultPlan();
        return plan.getOverMessageCost();
    }

    public static double getDefaultOverNationalFlowCost(){
        Plan plan = getDefaultPlan();
        return plan.getOverNationalCost();
    }

    public static double getDefaultOverLocalFlowCost(){
        Plan plan = getDefaultPlan();
        return plan.getOverLocalCost();
    }
}
