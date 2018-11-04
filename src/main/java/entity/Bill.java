package entity;


import java.util.Date;

public class Bill {
    private Integer uid;
    private Double callCost;
    private Double messageCost;
    private Double localFlowCost;
    private Double nationalFlowCost;
    private Integer freeCall;
    private Integer freeMessage;
    private Integer freeLocalFlow ;
    private Double freeNationalFlow;
    private Date date;

    public String toString(){
        StringBuilder stb = new StringBuilder();
        stb.append("用户id为").append(uid).append("的账单如下").append("\n")
                .append("   电话消费:").append(callCost).append("元\n")
                .append("   使用套餐内时长:").append(freeCall).append("分钟\n")
                .append("   信息消费:").append(callCost).append("元\n")
                .append("   使用套餐条数:").append(freeCall).append("条\n")
                .append("   本地流量消费:").append(callCost).append("元\n")
                .append("   使用套餐内部分:").append(freeCall).append("M\n")
                .append("   全国流量:").append(callCost).append("元\n")
                .append("   使用套餐内部分:").append(freeCall).append("M\n")
                .append("   总消费：").append(callCost+messageCost+localFlowCost+nationalFlowCost);
        return stb.toString();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getCallCost() {
        return callCost;
    }

    public void setCallCost(Double callCost) {
        this.callCost = callCost;
    }

    public Double getMessageCost() {
        return messageCost;
    }

    public void setMessageCost(Double messageCost) {
        this.messageCost = messageCost;
    }

    public Double getLocalFlowCost() {
        return localFlowCost;
    }

    public void setLocalFlowCost(Double localFlowCost) {
        this.localFlowCost = localFlowCost;
    }

    public Double getNationalFlowCost() {
        return nationalFlowCost;
    }

    public void setNationalFlowCost(Double nationalFlowCost) {
        this.nationalFlowCost = nationalFlowCost;
    }

    public Integer getFreeCall() {
        return freeCall;
    }

    public void setFreeCall(Integer freeCall) {
        this.freeCall = freeCall;
    }

    public Integer getFreeMessage() {
        return freeMessage;
    }

    public void setFreeMessage(Integer freeMessage) {
        this.freeMessage = freeMessage;
    }


    public Double getFreeNationalFlow() {
        return freeNationalFlow;
    }

    public void setFreeNationalFlow(Double freeNationalFlow) {
        this.freeNationalFlow = freeNationalFlow;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFreeLocalFlow() {
        return freeLocalFlow;
    }

    public void setFreeLocalFlow(Integer freeLocalFlow) {
        this.freeLocalFlow = freeLocalFlow;
    }
}
