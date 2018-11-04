package mapper;

import entity.Plan;

public interface PlanMapper {
    void insertPlan(Plan plan);
    Plan selectPlanByPlanId(Long pid);
}
