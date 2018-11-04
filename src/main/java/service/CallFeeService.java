package service;

import java.util.Date;
import java.util.List;

public interface CallFeeService {

    /**
     *
     * */
    void createCallFee(Long uid, Integer time);

    /**
     *
     * */
    void createCallFee(Long uid, Integer time, Date begin);

    /**
     * 查询上次通话资费
     * @param uid 用户id
     * */
    String getCallFeeByUserId(Long uid);

    /**
     * 查询近times次通话资费
     * @param uid 用户id
     * @param times 查询的通话资费使用情况次数
     * */
    List<String> listConcreteCallFeeByUserId(Long uid, int times);
}
