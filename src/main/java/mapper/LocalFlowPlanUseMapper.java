package mapper;

import entity.LocalFlowPlanUsage;

import java.util.List;

public interface LocalFlowPlanUseMapper {
    void insert(LocalFlowPlanUsage usage);
    List<LocalFlowPlanUsage> listLocalFlowPlanUsages(Long lffid);
}
