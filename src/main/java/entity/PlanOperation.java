package entity;

import enums.ValidDateEnum;
import utils.DateUtil;

import java.util.Date;


public class PlanOperation {
    private Long pid;
    private String pname;
    private Long uid;
    private Date date;
    private Boolean optype;
    private ValidDateEnum validDate;

    public String toString(){
        StringBuilder strbd = new StringBuilder();
        strbd.append("id为"+uid+"的用户于")
                .append(" "+DateUtil.getDateString(date)+" ")
                .append(parseOptype())
                .append(" \""+pname+"\"(")
                .append(parseValidDate()+")");
        return strbd.toString();
    }

    private String parseOptype(){
        if (optype){return "订购";}
        return "取消";
    }

    private String parseValidDate(){
        if (validDate==ValidDateEnum.NEXT){return "下月生效";}
        if (validDate==ValidDateEnum.NOW){return "立即生效";}
        return null;
    }

    /***************************************************************************
     *                                                                         *
     * Getters And Setters                                                           *
     *                                                                         *
     **************************************************************************/
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getOptype() {
        return optype;
    }

    public void setOptype(Boolean optype) {
        this.optype = optype;
    }

    public ValidDateEnum getValidDate() {
        return validDate;
    }

    public void setValidDate(ValidDateEnum validDate) {
        this.validDate = validDate;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
