package mapper;

import entity.CallFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public  interface CallFeeMapper {
    void insertCallFee(CallFee callFee);
    /**
     * 查询上次通话资费
     * @param uid 用户id
     * */
    CallFee selectCallFeeByUserId(Long uid);

    /**
     * 查询近times次通话资费
     * @param uid 用户id
     * @param times 查询的通话资费使用情况次数
     * */
    List<CallFee> listConcreteCallFeeByUserId(@Param("uid") Long uid, @Param("times") int times);

    CallFee selectCallCost(@Param("uid") Long uid, @Param("year") int year, @Param("month") int month);
}
