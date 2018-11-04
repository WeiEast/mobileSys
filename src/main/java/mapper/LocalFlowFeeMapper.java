package mapper;

import entity.LocalFlowFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocalFlowFeeMapper {
    void insertLocalFlowFee(LocalFlowFee localFlowFee);
    /**
     * 查询上次通话资费
     * @param uid 用户id
     * */
    LocalFlowFee selectLocalFlowFeeByUserId(Long uid);

    /**
     * 查询近times次通话资费
     * @param uid 用户id
     * @param times 查询的通话资费使用情况次数
     * */
    List<LocalFlowFee> listConcreteLocalFlowFeeByUserId(@Param("uid") Long uid, @Param("times") int times);
}
