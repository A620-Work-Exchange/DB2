package domain;

public class Bundle {
    /**
     * 短信费用/条
     */
    private double SMSFee;

    /**
     * 通话费用/分钟
     */
    private double callFee;

    /**
     * 本地流量费用/G
     */
    private double localDataFee;

    /**
     * 国内流量费用/G
     */
    private double domesticDataFee;

    /**
     * 套餐功能费用
     */
    private double functionFee;

    /**
     * 电话免费时长
     */
    private double freeCallLimit;

    /**
     * 短信免费条数
     */
    private double freeSMSLimit;

    /**
     * 本地流量免费量
     */
    private double freeLocalDataLimit;

    /**
     * 国内流量免费量
     */
    private double freeDomesticDataLimit;

    public Bundle() {
    }

    public Bundle(double SMSFee, double callFee, double localDataFee, double domesticDataFee) {
        this.SMSFee = SMSFee;
        this.callFee = callFee;
        this.localDataFee = localDataFee;
        this.domesticDataFee = domesticDataFee;
    }

    public Bundle(double SMSFee, double callFee, double localDataFee,
                  double domesticDataFee, double functionFee, double freeCallLimit,
                  double freeSMSLimit, double freeLocalDataLimit, double freeDomesticDataLimit) {
        this.SMSFee = SMSFee;
        this.callFee = callFee;
        this.localDataFee = localDataFee;
        this.domesticDataFee = domesticDataFee;
        this.functionFee = functionFee;
        this.freeCallLimit = freeCallLimit;
        this.freeSMSLimit = freeSMSLimit;
        this.freeLocalDataLimit = freeLocalDataLimit;
        this.freeDomesticDataLimit = freeDomesticDataLimit;
    }

    public double getSMSFee() {
        return SMSFee;
    }

    public void setSMSFee(double SMSFee) {
        this.SMSFee = SMSFee;
    }

    public double getCallFee() {
        return callFee;
    }

    public void setCallFee(double callFee) {
        this.callFee = callFee;
    }

    public double getLocalDataFee() {
        return localDataFee;
    }

    public void setLocalDataFee(double localDataFee) {
        this.localDataFee = localDataFee;
    }

    public double getDomesticDataFee() {
        return domesticDataFee;
    }

    public void setDomesticDataFee(double domesticDataFee) {
        this.domesticDataFee = domesticDataFee;
    }

    public double getFunctionFee() {
        return functionFee;
    }

    public void setFunctionFee(double functionFee) {
        this.functionFee = functionFee;
    }

    public double getFreeCallLimit() {
        return freeCallLimit;
    }

    public void setFreeCallLimit(double freeCallLimit) {
        this.freeCallLimit = freeCallLimit;
    }

    public double getFreeSMSLimit() {
        return freeSMSLimit;
    }

    public void setFreeSMSLimit(double freeSMSLimit) {
        this.freeSMSLimit = freeSMSLimit;
    }

    public double getFreeLocalDataLimit() {
        return freeLocalDataLimit;
    }

    public void setFreeLocalDataLimit(double freeLocalDataLimit) {
        this.freeLocalDataLimit = freeLocalDataLimit;
    }

    public double getFreeDomesticDataLimit() {
        return freeDomesticDataLimit;
    }

    public void setFreeDomesticDataLimit(double freeDomesticDataLimit) {
        this.freeDomesticDataLimit = freeDomesticDataLimit;
    }
}
