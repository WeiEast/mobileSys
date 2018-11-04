package entity;

public class Plan {
    private Long pid;
    private String name;
    private String description;
    private Double cost;
    private Integer freeCall;
    private Double overCallCost;
    private Integer freeMessage;
    private Double overMessageCost;
    private Double localFlow;
    private Double overLocalCost;
    private Double nationalFlow;
    private Double overNationalCost;


    public String toString(){
        StringBuilder strbd = new StringBuilder();
        strbd.append("名称: ").append(name).append("\t")
                .append("描述: ").append(description).append("\t")
                .append("套餐费").append(cost).append("\t");
        if (freeCall != null){strbd.append("免费通话时长: ").append(freeCall).append("\t");}
        if (overCallCost != null){strbd.append("超出计费: ").append(overCallCost).append("\t");}
        if (freeCall != null){strbd.append("免费通话时长: ").append(freeCall).append("\t");}
        if (freeCall != null){strbd.append("免费通话时长: ").append(freeCall).append("\t");}
        if (freeCall != null){strbd.append("免费通话时长: ").append(freeCall).append("\t");}
        if (freeCall != null){strbd.append("免费通话时长: ").append(freeCall).append("\t");}
        strbd.append("\n");
        return strbd.toString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getFreeCall() {
        return freeCall;
    }

    public void setFreeCall(Integer freeCall) {
        this.freeCall = freeCall;
    }

    public Double getOverCallCost() {
        return overCallCost;
    }

    public void setOverCallCost(Double overCallCost) {
        this.overCallCost = overCallCost;
    }

    public Integer getFreeMessage() {
        return freeMessage;
    }

    public void setFreeMessage(Integer freeMessage) {
        this.freeMessage = freeMessage;
    }

    public Double getOverMessageCost() {
        return overMessageCost;
    }

    public void setOverMessageCost(Double overMessageCost) {
        this.overMessageCost = overMessageCost;
    }

    public Double getLocalFlow() {
        return localFlow;
    }

    public void setLocalFlow(Double localFlow) {
        this.localFlow = localFlow;
    }

    public Double getOverLocalCost() {
        return overLocalCost;
    }

    public void setOverLocalCost(Double overLocalCost) {
        this.overLocalCost = overLocalCost;
    }

    public Double getNationalFlow() {
        return nationalFlow;
    }

    public void setNationalFlow(Double nationalFlow) {
        this.nationalFlow = nationalFlow;
    }

    public Double getOverNationalCost() {
        return overNationalCost;
    }

    public void setOverNationalCost(Double overNationalCost) {
        this.overNationalCost = overNationalCost;
    }
}
