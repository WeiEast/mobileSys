import entity.CallFee;
import mapper.CallFeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;
import utils.DateUtil;

import java.util.List;

public class CallFeeMapperTest {
    SqlSession session = DBUtil.openSqlSession();
    CallFeeMapper mapper = session.getMapper(CallFeeMapper.class);

    @Test
    public void testInsert(){
        CallFee callFee = new CallFee();
        callFee.setCfid(1L);
        callFee.setUid(1L);
        callFee.setCost(1.5);
        callFee.setBeginDate(DateUtil.createDate(2018, 1, 2, 11, 30, 0));
        callFee.setEndDate(DateUtil.createDate(2018, 1, 2, 11, 50, 0));
        mapper.insertCallFee(callFee);
    }

    @Test
    public void testSelect(){
        CallFee callFee = mapper.selectCallFeeByUserId(1L);
        System.out.println(callFee.toString());
    }

    @Test
    public void testList(){
        List<CallFee> callFees = mapper.listConcreteCallFeeByUserId(1L, 10);
        for (CallFee c: callFees){
            System.out.println(c.toString());
        }
    }
}
