package entity;

import utils.DateUtil;

import java.util.Date;


public class CallFee {
    private Long cfid;
    private Long uid;
    private Double cost;
    private Date beginDate;
    private Date endDate;

    public String toString(){
        StringBuilder stb = new StringBuilder();
        stb.append("id为"+uid+"的用户于")
                .append(" "+DateUtil.getDateString(beginDate)+" ")
                .append("通话至")
                .append(" "+DateUtil.getDateString(endDate)+" ")
                .append("共花费 "+cost+" 元");
        return stb.toString();
    }

    /***************************************************************************
     *                                                                         *
     * Getters And Setters                                                           *
     *                                                                         *
     **************************************************************************/
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCfid() {
        return cfid;
    }

    public void setCfid(Long cfid) {
        this.cfid = cfid;
    }
}
