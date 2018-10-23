package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    /**
     * 用户名
     */
    @Id
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 余额
     */
    private double balance;

    /**
     * 订购的套餐
     */
    @OneToMany
    private Set<Bundle> bundleList = new HashSet<>();

    /**
     * 通话时间
     */
    private int callUsage;

    /**
     * 短信量
     */
    private double SMSUsage;

    /**
     * 本地流量使用量
     */
    private double localDataUsage;

    /**
     * 国内流量使用量
     */
    private double domesticDataUsage;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public User(String username, String password, Set<Bundle> bundleList) {
        this.username = username;
        this.password = password;
        this.bundleList = bundleList;
    }

    public User(String username, String password,
                double balance, Set<Bundle> bundleList) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.bundleList = bundleList;
    }

    public User(String username, String password, double balance,
                Set<Bundle> bundleList, int callUsage, double SMSUsage,
                double localDataUsage, double domesticDataUsage) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.bundleList = bundleList;
        this.callUsage = callUsage;
        this.SMSUsage = SMSUsage;
        this.localDataUsage = localDataUsage;
        this.domesticDataUsage = domesticDataUsage;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Bundle> getBundleList() {
        return bundleList;
    }

    public void setBundleList(Set<Bundle> bundleList) {
        this.bundleList = bundleList;
    }

    public int getCallUsage() {
        return callUsage;
    }

    public void setCallUsage(int callUsage) {
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
