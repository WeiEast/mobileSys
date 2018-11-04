import bl.*;
import entity.Bill;
import entity.PlanOperation;
import enums.ValidDateEnum;
import org.omg.CORBA.MARSHAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import service.*;

import java.util.List;

public class Main {
    PlanService planService = new PlanServiceImpl();
    CallFeeService callFeeService = new CallFeeServiceImpl();
    LocalFlowFeeService localFlowFeeService= new LocalFlowFeeServiceImpl();
    NationalFlowFeeService nationalFlowFeeService = new NationalFlowFeeServiceImpl();
    BillService billService = new BillServiceImpl();

    /**
     * 查询用户套餐订购和退订的历史记录
     * 可使用uid: 1L
     * */
    public void queryHistoryPlanOperations(Long uid){
        List<PlanOperation> list = planService.listHistoryPlanOperationByUserId(uid);
        if (list==null){
            System.out.println("没有订过套餐");
            return;
        }
        for (PlanOperation planOperation: list){
            System.out.println(planOperation);
        }
    }

    /**
     * 订购账单
     * 可使用uid: 1L
     * @param type 表示当前生效还是下月生效
     *             ValidDateEnum.NOW 当前生效
     *             ValidDateEnum.NEXT 下月生效
     * */
    public void purchasePlan(Long uid, Long pid, ValidDateEnum type){
       boolean success = planService.purchasePlan(uid, pid, type);
       if (success){System.out.println("订购成功");}
       else {System.out.println("已存在该套餐,不能重复订购");}
    }

    /**
     * 退订订单
     * 可使用uid: 1L
     * @param type 表示当前生效还是下月生效
     *             ValidDateEnum.NOW 当前生效
     *             ValidDateEnum.NEXT 下月生效
     * */
    public void deletePlan(Long uid, Long pid, ValidDateEnum type){
        boolean success = planService.deletePlan(uid, pid, type);
        if (success){System.out.println("退订成功");}
        else {System.out.println("不能重复退订");}
    }

    /**
     * 生成本地流量资费
     * @param uid 用户id
     * @param minutes 通话时长
     * */
    public void call(Long uid, int minutes){
        callFeeService.createCallFee(uid,minutes);
        String callFee = callFeeService.getCallFeeByUserId(uid);
        System.out.println(callFee);
    }

    /**
     * 生成本地流量资费
     * @param uid 用户id
     * @param minutes 通话时长
     * @param volume 使用流量
     * */
    public void useLocalFlow(Long uid, int minutes, double volume){
        localFlowFeeService.createLocalFlowFee(uid, volume, minutes);
        String s = localFlowFeeService.getLocalFlowFeeByUserId(uid);
        System.out.println(s);
    }

    /**
     * 生成国际记流量资费
     * @param uid 用户id
     * @param minutes 通话时长
     * @param volume 使用流量
     * */
    public void useNationalFlow(Long uid, int minutes, double volume){
//        nationalFlowFeeService.createNationalFlowFee(uid, volume, minutes);
        String s = nationalFlowFeeService.getNationalFlowFeeByUserId(uid);
        System.out.println(s);
    }

    /**
     * 生成月账单
     * @param uid 用户id
     * */
    public void calcMonthBill(Long uid, int year, int month){
        Bill bill = billService.getBillByUserid(uid, year, month);
        if (bill==null){
            System.out.println("不存在该月账单");
            return;
        }
        System.out.println(bill.toString());
    }

    public static void main(String[] args){
        Main main = new Main();

        long startTime = System.currentTimeMillis();

        //查询历史记录
//        main.queryHistoryPlanOperations(1L);

        //订购套餐
//        main.purchasePlan(1L, 2L, ValidDateEnum.NOW);
        //重复订
//        main.purchasePlan(1L, 2L, ValidDateEnum.NOW);
//        main.purchasePlan(1L, 3L, ValidDateEnum.NEXT);

        //删除套餐
//        main.deletePlan(1L, 2L, ValidDateEnum.NOW);
        //重复删除
//        main.deletePlan(1L, 2L, ValidDateEnum.NOW);

        //生成电话资费
//        main.call(3L, 50);

        //生成本地流量资费
//        main.useLocalFlow(3L, 50, 200);

        //生成国际流量资费
        main.useNationalFlow(3L, 50, 200);

        //计算月账单
        main.calcMonthBill(1L, 2018, 10);

        long endTime = System.currentTimeMillis();
        System.out.println("运行时间: "+(endTime-startTime)+"ms");
    }
}
