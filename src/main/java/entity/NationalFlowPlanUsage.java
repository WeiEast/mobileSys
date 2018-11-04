package entity;

public class NationalFlowPlanUsage {
    private Long nffid;
    private Long pid;
    private String pname;
    private Double freeVolume;
    private Double overVolume;
    private Double cost;

    public String toString(){
        if (cost==null) {cost=5.0;}
        StringBuilder stb = new StringBuilder();
        if (freeVolume!=null){stb.append("\""+pname+"\"  ").append("使用了免费全国流量:").append(freeVolume+"M  ");}
        if (overVolume!=null){
            stb.append("超出:").append(overVolume)
                    .append("M   超时花费:").append(cost).append("元/M")
                    .append("   总花费:").append(cost*overVolume);
        }
        return stb.toString();
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getFreeVolume() {
        return freeVolume;
    }
    public void setFreeVolume(Double freeVolume) {
        this.freeVolume = freeVolume;
    }
    public Double getOverVolume() {
        return overVolume;
    }
    public void setOverVolume(Double overVolume) {
        this.overVolume = overVolume;
    }

    public Long getNffid() {
        return nffid;
    }

    public void setNffid(Long nffid) {
        this.nffid = nffid;
    }
}
