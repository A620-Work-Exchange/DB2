package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Consumption implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate localDate;

    @OneToOne
    private User user;

    private double callUsage;

    private double SMSUsage;

    private double localDataUsage;

    private double domesticDataUsage;

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
}
