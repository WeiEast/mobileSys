package entity;


public class CallPlanUsage {
    private Long cfid;
    private Long pid;
    private String pname;
    private Integer freeTime;
    private Integer overTime;
    private Double cost;

    public String toString(){
        if (cost==null) {cost=0.5;}
        StringBuilder stb = new StringBuilder();
        if (freeTime!=null){stb.append("\""+pname+"\"  ").append("使用了免费时长:").append(freeTime+"  ");}
        if (overTime!=null){
            stb.append("超时:").append(overTime)
                .append("   超时花费:").append(cost).append("元/分钟")
                .append("   总花费:").append(cost*overTime);
        }
        return stb.toString();
    }

    /***************************************************************************
     *                                                                         *
     * Getters And Setters                                                           *
     *                                                                         *
     **************************************************************************/
    public Long getCfid() {
        return cfid;
    }

    public void setCfid(Long cfid) {
        this.cfid = cfid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public Integer getOverTime() {
        return overTime;
    }

    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
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
}
