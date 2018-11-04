package service;

import entity.LocalFlowFee;

import java.util.List;

public interface MessageService {
    /**
     * 查询最近一次持续上网资费使用情况
     * @param uid 用户id
     * */
    LocalFlowFee getMessageFeeByUserId(Long uid);

    /**
     * 查询最近times次发信息资费使用情况
     * @param uid 用户id
     * @param times 查询次数
     * */
    List<LocalFlowFee> listMessageFeeByUserId(Long uid, int times);
}
