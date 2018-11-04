package service;

import entity.PlanOperation;
import enums.ValidDateEnum;

import java.util.List;

public interface PlanService {
    /**
     * 查询历史套餐操作信息(包括订购,退订等操作)
     * */
    List<PlanOperation> listHistoryPlanOperationByUserId(Long uid);

    boolean purchasePlan(Long uid, Long pid, ValidDateEnum type);

    boolean deletePlan(Long uid, Long pid, ValidDateEnum type);
}
