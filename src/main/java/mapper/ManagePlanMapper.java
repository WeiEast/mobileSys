package mapper;

import entity.PlanOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagePlanMapper {
    List<PlanOperation> listPlanOpsByUserId(Long uid);
    void insertPlanOp(PlanOperation op);
    PlanOperation selectLatestPlanOp(@Param("uid") Long uid, @Param("pid") Long pid);
    Double selectCancelConsumption(@Param("uid")Long uid, @Param("year") int year, @Param("month")int month);
}
