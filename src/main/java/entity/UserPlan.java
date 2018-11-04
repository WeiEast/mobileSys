package entity;

import java.util.Date;

public class UserPlan {
    private Long uid;
    private Long pid;
    private Double leftNFlow;
    private Double leftLFlow;
    private Integer leftMessage;
    private Integer leftCall;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Double getLeftNFlow() {
        return leftNFlow;
    }

    public void setLeftNFlow(Double leftNFlow) {
        this.leftNFlow = leftNFlow;
    }

    public Double getLeftLFlow() {
        return leftLFlow;
    }

    public void setLeftLFlow(Double leftLFlow) {
        this.leftLFlow = leftLFlow;
    }

    public Integer getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(Integer leftMessage) {
        this.leftMessage = leftMessage;
    }

    public Integer getLeftCall() {
        return leftCall;
    }

    public void setLeftCall(Integer leftCall) {
        this.leftCall = leftCall;
    }

}
