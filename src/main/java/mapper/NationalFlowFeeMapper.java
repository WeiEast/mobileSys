package mapper;

import entity.NationalFlowFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationalFlowFeeMapper {
    void insertNationalFlowFee(NationalFlowFee nationalFlowFee);
    /**
     * 查询上次通话资费
     * @param uid 用户id
     * */
    NationalFlowFee selectNationalFlowFeeByUserId(Long uid);

    /**
     * 查询近times次通话资费
     * @param uid 用户id
     * @param times 查询的通话资费使用情况次数
     * */
    List<NationalFlowFee> listConcreteNationalFlowFeeByUserId(@Param("uid") Long uid, @Param("times") int times);
}
