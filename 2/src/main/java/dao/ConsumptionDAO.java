package dao;

import domain.Consumption;
import domain.User;
import util.DateUtil;
import util.HQLUtil;

import java.util.List;


public class ConsumptionDAO {
    private UserDAO userDAO = new UserDAO();

    public boolean addCallUsage(String username, double callUsage) {
        try {
            User user = userDAO.findUserByUserName(username);
            Consumption consumption = new Consumption();
            consumption.setLocalDate(DateUtil.getCurrentDate());
            consumption.setUser(user);
            double callTimeRemain = user.getCallRemain();
            consumption.setCallUsage(callUsage);
            HQLUtil.add(consumption);

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

    public boolean addSMSUsage(String username, int SMSAmount) {
        try {
            User user = userDAO.findUserByUserName(username);
            int SMSRemain = user.getSMSRemain();
            int remain = SMSRemain - SMSAmount;
            Consumption consumption = new Consumption();
            consumption.setLocalDate(DateUtil.getCurrentDate());
            consumption.setSMSUsage(SMSAmount);
            consumption.setUser(user);
            HQLUtil.add(consumption);

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

    public boolean addLocalDataUsage(String username, double localDataUsage) {
        try {
            User user = userDAO.findUserByUserName(username);
            double balance = user.getBalance();
            double localDataRemain = user.getLocalDataRemain();
            double remain = localDataRemain - localDataUsage;
            user.setLocalDataRemain(remain);

            Consumption consumption = new Consumption();
            consumption.setLocalDate(DateUtil.getCurrentDate());
            consumption.setLocalDataUsage(localDataUsage);
            consumption.setUser(user);
            HQLUtil.add(consumption);

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

    public boolean addDomesticDataUsage(String username, double domesticDataUsage) {
        try {
            User user = userDAO.findUserByUserName(username);
            Consumption consumption = new Consumption();
            consumption.setLocalDate(DateUtil.getCurrentDate());
            consumption.setLocalDataUsage(domesticDataUsage);
            consumption.setUser(user);
            HQLUtil.add(consumption);

            double domesticDataRemain = user.getDomesticDataRemain();
            double balance = user.getBalance();
            double remain = domesticDataRemain - domesticDataUsage;
            user.setDomesticDataRemain(remain);

            if (remain < 0 ) {
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

    /**
     * 按照对应月查找所有消耗
     * @param month
     * @return
     */
    public List<Consumption> listConsumptionByMonth(String month) {
        try {
            String oprdStr = "from Consumption where date";
            return (List<Consumption>) HQLUtil.find(oprdStr);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }    }
}