package service;

import entity.Bill;

public interface BillService {
    /**
     * 查询用户某月账单
     * @param uid 用户id
     * @param month 所属月份
     * */
    Bill getBillByUserid(Long uid, int year, int month);

}
