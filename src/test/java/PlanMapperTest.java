import entity.Plan;
import mapper.PlanMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;

public class PlanMapperTest {
    SqlSession session = DBUtil.openSqlSession();
    PlanMapper mapper = session.getMapper(PlanMapper.class);

    @Test
    public void testInsert(){
        Plan plan = new Plan();
        plan.setName("callPlan");
        plan.setCost(20.0);
        plan.setDescription("call sale");
        plan.setFreeCall(100);
        plan.setOverCallCost(0.5);

        mapper.insertPlan(plan);
    }

    @Test
    public void testSelect(){
        Plan plan = mapper.selectPlanByPlanId(2L);
        System.out.println(plan.toString());
    }
}
