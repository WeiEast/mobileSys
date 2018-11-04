package mapper;

import entity.NationalFlowPlanUsage;

import java.util.List;

public interface NationalFlowPlanUseMapper {
    void insert(NationalFlowPlanUsage usage);
    List<NationalFlowPlanUsage> listNationalFlowPlanUsages(Long nffid);
}
