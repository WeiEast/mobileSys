package mapper;

import entity.CallPlanUsage;

import java.util.List;

public interface CallPlanUseMapper {
    void insert(CallPlanUsage usage);
    List<CallPlanUsage> listCallPlanUsages(Long cfid);
}
