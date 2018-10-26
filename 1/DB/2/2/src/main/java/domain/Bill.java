package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 账单日期
     */
    private String date;


    @OneToOne
    private User user;

    /**
     * 消耗电话时长
     */
    private double callUsage;

    /**
     * 消耗的短信量
     */
    private int SMSUsage;

    /**
     * 本地流量的消耗
     */
    private double localDataUsage;

    /**
     * 国内流量的消耗
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
     * 国内流量费用
     */
    private double domesticDataFee;

    /**
     * 总费用
     */
    private double sumFee;

    public Bill() {
    }

    public Bill(String date, User user, double callUsage,
                int SMSUsage, double localDataUsage, double domesticDataUsage) {
        this.date = date;
        this.user = user;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
    }

    public Bill(String date, User user, double callUsage, int SMSUsage,
                double localDataUsage, double domesticDataUsage, double callFee, double SMSFee, double localDataFee, double domesticDataFee) {
        this.date = date;
        this.user = user;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
        this.callFee = callFee;
        this.SMSFee = SMSFee;
        this.localDataFee = localDataFee;
        this.domesticDataFee = domesticDataFee;
    }

    public Bill(String date, User user, double callUsage,
                int SMSUsage, double localDataUsage, double domesticDataUsage,
                double callFee, double SMSFee, double localDataFee,
                double domesticDataFee, double sumFee) {
        this.date = date;
        this.user = user;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
        this.callFee = callFee;
        this.SMSFee = SMSFee;
        this.localDataFee = localDataFee;
        this.domesticDataFee = domesticDataFee;
        this.sumFee = sumFee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public void setSMSUsage(int SMSUsage) {
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

    public double getSumFee() {
        return sumFee;
    }

    public void setSumFee(double sumFee) {
        this.sumFee = sumFee;
    }
}
