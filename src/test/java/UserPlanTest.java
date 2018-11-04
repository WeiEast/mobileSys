import entity.UserPlan;
import mapper.UserPlanMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;

import java.util.List;

public class UserPlanTest {
    SqlSession session = DBUtil.openSqlSession();
    UserPlanMapper mapper = session.getMapper(UserPlanMapper.class);

    @Test
    public void testInsert(){
        UserPlan plan = new UserPlan();
        plan.setUid(1L);
        plan.setPid(3L);
        plan.setLeftCall(20);
        plan.setLeftLFlow(20.0);
        plan.setLeftMessage(10);
        plan.setLeftNFlow(10.0);
        mapper.insertUserPlan(plan);
    }

    @Test
    public void testDelete(){
        mapper.deleteUserPlan(1L, 2L);
    }

    @Test
    public void testUpdate(){
        UserPlan plan = new UserPlan();
        plan.setUid(1L);
        plan.setPid(2L);
        plan.setLeftCall(2000000);
        plan.setLeftLFlow(10.0);
        plan.setLeftMessage(10);
        plan.setLeftNFlow(10.0);
        mapper.updateUserPlan(plan);
    }

    @Test
    public void testSelect(){
        List<UserPlan> plans = mapper.listUserPlan(1L);
        for (UserPlan plan: plans){
            System.out.println(plan.getUid()+" "+plan.getPid()+" "+plan.getLeftCall());
        }
    }
    @Test
    public void testSelectCost(){
//        Double s = mapper.selectNowConsumption(1L);
//        System.out.println(s);
    }
}
