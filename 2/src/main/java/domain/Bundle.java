package domain;

public class Bundle {

    /**
     * 通话费用/分钟
     */
    private double callFee;

    /**
     * 短信费用/条
     */
    private double SMSFee;

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
     * 超出套餐费用/分钟
     */
    private double exceedCallFee;

    /**
     * 短信免费条数
     */
    private double freeSMSLimit;

    /**
     * 超出短信费用/条
     */
    private double exceedSMSFee;

    /**
     * 本地流量免费量
     */
    private double freeLocalDataLimit;

    /**
     * 超出本地流量费用/M
     */
    private double exceedLocalDataFee;

    /**
     * 国内流量免费量
     */
    private double freeDomesticDataLimit;

    /**
     * 超出国内流量费用/M
     */
    private double exceedDomesticDataLimit;

    public Bundle() {
        this.callFee = 0.5;
        this.SMSFee = 0.1;
        this.localDataFee = 2;
        this.domesticDataFee = 5;
        this.functionFee = 0;
        this.freeCallLimit = 0;
        this.exceedCallFee = 0.5;
        this.freeSMSLimit = 0;
        this.exceedSMSFee = 0.1;
        this.freeLocalDataLimit = 2000;
        this.exceedLocalDataFee = 2;
        this.freeDomesticDataLimit = 2000;
        this.freeDomesticDataLimit = 5;
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

    public double getExceedCallFee() {
        return exceedCallFee;
    }

    public void setExceedCallFee(double exceedCallFee) {
        this.exceedCallFee = exceedCallFee;
    }

    public double getExceedSMSFee() {
        return exceedSMSFee;
    }

    public void setExceedSMSFee(double exceedSMSFee) {
        this.exceedSMSFee = exceedSMSFee;
    }

    public double getExceedLocalDataFee() {
        return exceedLocalDataFee;
    }

    public void setExceedLocalDataFee(double exceedLocalDataFee) {
        this.exceedLocalDataFee = exceedLocalDataFee;
    }

    public double getExceedDomesticDataLimit() {
        return exceedDomesticDataLimit;
    }

    public void setExceedDomesticDataLimit(double exceedDomesticDataLimit) {
        this.exceedDomesticDataLimit = exceedDomesticDataLimit;
    }
}
