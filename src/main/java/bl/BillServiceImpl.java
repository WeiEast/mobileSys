package bl;

import entity.Bill;
import mapper.BillMapper;
import org.apache.ibatis.session.SqlSession;
import service.BillService;
import utils.DBUtil;

public class BillServiceImpl implements BillService {
    @Override
    public Bill getBillByUserid(Long uid, int year, int month) {
        SqlSession session = DBUtil.openSqlSession();
        BillMapper billMapper = session.getMapper(BillMapper.class);
        return billMapper.selectBill(uid, year, month);
    }
}
