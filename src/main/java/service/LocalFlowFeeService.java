package service;

import java.util.Date;
import java.util.List;

public interface LocalFlowFeeService {

    /**
     *
     * */
    void createLocalFlowFee(Long uid, Double volume, Integer time);

    /**
     *
     * */
    void createLocalFlowFee(Long uid, Double volume, Date begin, Integer time);

    /**
     * 查询上次通话资费
     * @param uid 用户id
     * */
    String getLocalFlowFeeByUserId(Long uid);

    /**
     * 查询近times次通话资费
     * @param uid 用户id
     * @param times 查询的通话资费使用情况次数
     * */
    List<String> listConcreteLocalFlowFeeByUserId(Long uid, int times);
}
