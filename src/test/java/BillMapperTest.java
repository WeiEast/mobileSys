import entity.Bill;
import mapper.BillMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;

public class BillMapperTest {
    @Test
    public void testSelect(){
        SqlSession session = DBUtil.openSqlSession();
        BillMapper billMapper = session.getMapper(BillMapper.class);
        Bill bill = billMapper.selectBill(1L, 2018, 10);
        System.out.println(bill.toString());
    }
}
