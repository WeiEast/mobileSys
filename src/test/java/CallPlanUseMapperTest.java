import entity.CallPlanUsage;
import mapper.CallPlanUseMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;

import java.util.List;

public class CallPlanUseMapperTest {
    SqlSession session = DBUtil.openSqlSession();
    CallPlanUseMapper mapper = session.getMapper(CallPlanUseMapper.class);

    @Test
    public void testInsert(){
        CallPlanUsage  usage = new CallPlanUsage();
        usage.setCfid(1L);
        usage.setFreeTime(15);
        usage.setOverTime(45);
        usage.setPid(2L);
        mapper.insert(usage);
    }
    @Test
    public void testSelect(){
        List<CallPlanUsage> callPlanUsages = mapper.listCallPlanUsages(1L);
        for (CallPlanUsage c: callPlanUsages){
            System.out.println(c.toString());
        }
    }
}
