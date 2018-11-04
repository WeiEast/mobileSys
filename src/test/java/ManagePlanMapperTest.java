import entity.PlanOperation;
import enums.ValidDateEnum;
import mapper.ManagePlanMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.DBUtil;

import java.util.Date;
import java.util.List;

public class ManagePlanMapperTest {
    SqlSession session = DBUtil.openSqlSession();
    ManagePlanMapper mapper = session.getMapper(ManagePlanMapper.class);

    @Test
    public void testInsert(){
        PlanOperation op = new PlanOperation();
        op.setUid(1L);
        op.setPid(1L);
        op.setPname("");
        op.setOptype(false);
        op.setValidDate(ValidDateEnum.NEXT);
        op.setDate(new Date(2018-1900, 4-1, 13, 10, 12, 14));
        mapper.insertPlanOp(op);
    }

    @Test
    public void testList(){
        List<PlanOperation> ops = mapper.listPlanOpsByUserId(1L);
        for (PlanOperation op: ops){
            System.out.println(op.toString());
        }
    }

    @Test
    public void testSelectLatest(){
        PlanOperation op = mapper.selectLatestPlanOp(1L, 2L);
        System.out.println(op.toString());
    }

    @Test
    public void testSelectCancel(){
        double cost = mapper.selectCancelConsumption(1L, 2018, 1);
        System.out.println(cost);
    }
}
