package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;


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


    public Bill() {
    }

    public Bill(Date date, User user, double callUsage,
                int SMSUsage, double localDataUsage, double domesticDataUsage) {
        this.date = date;
        this.user = user;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
