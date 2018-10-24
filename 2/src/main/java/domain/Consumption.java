package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Consumption implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 日期
     */
    private LocalDate localDate;

    @OneToOne
    private User user;

    /**
     * 通话消耗
     */
    private double callUsage;

    /**
     * 短信消耗
     */
    private double SMSUsage;

    /**
     * 本地流量消耗
     */
    private double localDataUsage;

    /**
     * 国内流量消耗
     */
    private double domesticDataUsage;

    /**
     * 通话费用
     */
    private double callFee;

    /**
     * 短信费用
     */
    private double SMSFee;

    /**
     * 本地流量费用
     */
    private double localDataFee;

    /**
     * 国内流量非营业
     */
    private double domesticDataFee;

    /**
     * 费用
     */
    private double fee;

    public Consumption() {
    }

    public Consumption(LocalDate localDate, User user, double callUsage,
                       double SMSUsage, double localDataUsage, double domesticDataUsage) {
        this.localDate = localDate;
        this.user = user;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
    }

    public Consumption(LocalDate localDate, User user, double callUsage,
                       double SMSUsage, double localDataUsage, double domesticDataUsage,
                       double callFee, double SMSFee, double localDataFee, double domesticDataFee,
                       double fee) {
        this.localDate = localDate;
        this.user = user;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
        this.callFee = callFee;
        this.SMSFee = SMSFee;
        this.localDataFee = localDataFee;
        this.domesticDataFee = domesticDataFee;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getCallUsage() {
        return callUsage;
    }

    public void setCallUsage(double callUsage) {
        this.callUsage = callUsage;
    }

    public double getSMSUsage() {
        return SMSUsage;
    }

    public void setSMSUsage(double SMSUsage) {
        this.SMSUsage = SMSUsage;
    }

    public double getLocalDataUsage() {
        return localDataUsage;
    }

    public void setLocalDataUsage(double localDataUsage) {
        this.localDataUsage = localDataUsage;
    }

    public double getDomesticDataUsage() {
        return domesticDataUsage;
    }

    public void setDomesticDataUsage(double domesticDataUsage) {
        this.domesticDataUsage = domesticDataUsage;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getCallFee() {
        return callFee;
    }

    public void setCallFee(double callFee) {
        this.callFee = callFee;
    }

    public double getSMSFee() {
        return SMSFee;
    }

    public void setSMSFee(double SMSFee) {
        this.SMSFee = SMSFee;
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
}
