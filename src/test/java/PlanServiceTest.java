import bl.PlanServiceImpl;
import entity.PlanOperation;
import enums.ValidDateEnum;
import org.junit.Test;
import service.PlanService;

import java.util.List;

public class PlanServiceTest {
    PlanService service = new PlanServiceImpl();

    @Test
    public void testList(){
        List<PlanOperation> ops = service.listHistoryPlanOperationByUserId(1L);
        for (PlanOperation op: ops){
            System.out.println(op.toString());
        }
    }

    /**
     * 后置状态: manage_plan中加入操作  user_plan中加入套餐
     * */
    @Test
    public void testPurchase(){
        boolean suc = service.purchasePlan(3L, 3L, ValidDateEnum.NEXT);
        System.out.println(suc);
    }


    @Test
    public void testDelete(){
        boolean suc = service.deletePlan(3L, 3L, ValidDateEnum.NOW);
        System.out.println(suc);
    }
}
