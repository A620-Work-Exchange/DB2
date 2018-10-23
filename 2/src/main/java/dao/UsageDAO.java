package dao;

import domain.Usage;
import domain.User;
import util.DateUtil;
import util.HQLUtil;

import java.time.LocalDate;

public class UsageDAO {
    public boolean addCallUsage(User user, double callUsage) {
        try {
            LocalDate localDate;
            double callTimeRemain = user.getCallRemain();
            int remain = (int) Math.ceil(callTimeRemain - callUsage);
            user.setCallRemain(remain);
            double balance = user.getBalance();
            if ( remain < 0 ) {
                System.out.println("套餐电话时长已用完...");
                balance -= (-0.5) * remain;
                user.setBalance(balance);
                user.setCallRemain(0);
            }
            HQLUtil.update(user);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addSMSUsage(User user, int SMSAmount) {
        try {
            int SMSRemain = user.getSMSRemain();
            int remain = SMSRemain - SMSAmount;
            user.setSMSRemain(remain);
            double balance = user.getBalance();
            if(remain < 0) {
                System.out.println("套餐短信量已用完...");
                balance -= (-0.1) * remain;
                user.setBalance(balance);
                user.setSMSRemain(0);
            }
            HQLUtil.update(user);
            return true;

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addLocalDataUsage(User user, double localDataUsage) {
        try {
            double balance = user.getBalance();
            double localDataRemain = user.getLocalDataRemain();
            double remain = localDataRemain - localDataUsage;
            user.setLocalDataRemain(remain);

            if(remain < 0) {
                System.out.println("套餐本地流量已用完");
                balance -= (-3) * remain;
                user.setBalance(balance);
                user.setLocalDataRemain(0);
            }
            HQLUtil.update(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addDomesticDataUsage(User user, double domesticDataUsage) {
        try {
            double domesticDataRemain = user.getDomesticDataRemain();
            double balance = user.getBalance();
            double remain = domesticDataRemain - domesticDataUsage;
            user.setDomesticDataRemain(remain);

            if ( remain < 0 ) {
                System.out.println("国内流量套餐用完");
                balance -= (-3) * remain;
                user.setBalance(balance);
                user.setDomesticDataRemain(0);
            }
            HQLUtil.update(user);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
