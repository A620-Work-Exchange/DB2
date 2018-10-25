package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Bundle> bundleList = new ArrayList<>();

    /**
     * 通话剩余时间
     */
    private int callRemain;

    /**
     * 短信剩余量
     */
    private int SMSRemain;

    /**
     * 本地流量剩余量
     */
    private double localDataRemain;

    /**
     * 国内流量剩余量
     */
    private double domesticDataRemain;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public User(String username, String password, List<Bundle> bundleList) {
        this.username = username;
        this.password = password;
        this.bundleList = bundleList;
    }

    public User(String username, String password,
                double balance, List<Bundle> bundleList) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.bundleList = bundleList;
    }

    public User(String username, String password, double balance,
                List<Bundle> bundleList, int callRemain, int SMSRemain,
                double localDataRemain, double domesticDataRemain) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.bundleList = bundleList;
        this.callRemain = callRemain;
        this.SMSRemain = SMSRemain;
        this.localDataRemain = localDataRemain;
        this.domesticDataRemain = domesticDataRemain;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public List<Bundle> getBundleList() {
        return bundleList;
    }

    public void setBundleList(List<Bundle> bundleList) {
        this.bundleList = bundleList;
    }

    public int getCallRemain() {
        return callRemain;
    }

    public void setCallRemain(int callRemain) {
        this.callRemain = callRemain;
    }

    public int getSMSRemain() {
        return SMSRemain;
    }

    public void setSMSRemain(int SMSRemain) {
        this.SMSRemain = SMSRemain;
    }

    public double getLocalDataRemain() {
        return localDataRemain;
    }

    public void setLocalDataRemain(double localDataRemain) {
        this.localDataRemain = localDataRemain;
    }

    public double getDomesticDataRemain() {
        return domesticDataRemain;
    }

    public void setDomesticDataRemain(double domesticDataRemain) {
        this.domesticDataRemain = domesticDataRemain;
    }
}
