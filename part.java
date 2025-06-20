public class part {
    private String partID;
    private String lifeCycleStatus;
    private String commercialEOL;
    private String orderDate;
    private String receivedDate;
    private double unitCost;
    private int annualUsage;
    private boolean requiresRedesign;
    private int criticalityScore;
    private double failRate;
    private int totalPopulation;
    private int totalSuppliers;
    private boolean isItar;

    public part(String partID, String lifeCycleStatus, String commercialEOL, String orderDate, String receivedDate,
                double unitCost, int annualUsage, boolean requiresRedesign, int criticalityScore,
                double failRate, int totalPopulation, int totalSuppliers, boolean isItar) {
        this.partID = partID;
        this.lifeCycleStatus = lifeCycleStatus;
        this.commercialEOL = commercialEOL;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.unitCost = unitCost;
        this.annualUsage = annualUsage;
        this.requiresRedesign = requiresRedesign;
        this.criticalityScore = criticalityScore;
        this.failRate = failRate;
        this.totalPopulation = totalPopulation;
        this.totalSuppliers = totalSuppliers;
        this.isItar = isItar;
    }

    // Getters
    public String getPartID() {
        return partID;
    }

    public String getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public String getCommercialEOL() {
        return commercialEOL;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public int getAnnualUsage() {
        return annualUsage;
    }

    public boolean isRequiresRedesign() {
        return requiresRedesign;
    }

    public int getCriticalityScore() {
        return criticalityScore;
    }

    public double getFailRate() {
        return failRate;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getTotalSuppliers() {
        return totalSuppliers;
    }

    public boolean isItar() {
        return isItar;
    }

    // Setters
    public void setPartID(String partID) {
        this.partID = partID;
    }

    public void setLifeCycleStatus(String lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public void setCommercialEOL(String commercialEOL) {
        this.commercialEOL = commercialEOL;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public void setAnnualUsage(int annualUsage) {
        this.annualUsage = annualUsage;
    }

    public void setRequiresRedesign(boolean requiresRedesign) {
        this.requiresRedesign = requiresRedesign;
    }

    public void setCriticalityScore(int criticalityScore) {
        this.criticalityScore = criticalityScore;
    }

    public void setFailRate(double failRate) {
        this.failRate = failRate;
    }

    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public void setTotalSuppliers(int totalSuppliers) {
        this.totalSuppliers = totalSuppliers;
    }

    public void setItar(boolean isItar) {
        this.isItar = isItar;
    }
}
