package dao;

import domain.Bundle;
import domain.Consumption;
import domain.User;
import domain.enumeration.BundleType;
import util.DateUtil;
import util.HQLUtil;

import java.util.List;
import java.util.Set;


public class ConsumptionDAO {
    private UserDAO userDAO = new UserDAO();
    private BundleDAO bundleDAO = new BundleDAO();
    private static final String FREE_NOTIF = "本次套餐减免花费0元，套餐还剩";

    public boolean addCallUsage(String username, double callUsage) {
        try {
            User user = userDAO.findUserByUserName(username);
            Consumption consumption = new Consumption();
            consumption.setLocalDate(DateUtil.getCurrentDate());
            consumption.setUser(user);
            double callTimeRemain = user.getCallRemain();
            consumption.setCallUsage(callUsage);


            int remain = (int) Math.ceil(callTimeRemain - callUsage);
            user.setCallRemain(remain);
            double balance = user.getBalance();
            if (remain < 0 ) {
                System.out.println("套餐电话时长已用完...");
                balance -= (-0.5) * remain;
                user.setBalance(balance);
                user.setCallRemain(0);
                consumption.setFee(-0.5 * remain);
                System.out.println("本次通话耗时 " + callUsage + " 分钟，" + "花费 " +
                -0.5*remain + "元");
            }else {
                consumption.setFee(0);
                System.out.println(FREE_NOTIF + remain + " 分钟");
            }
            HQLUtil.add(consumption);
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

            user.setSMSRemain(remain);
            double balance = user.getBalance();
            if(remain < 0) {
                System.out.println("套餐短信量已用完...");
                balance -= (-0.1) * remain;
                user.setBalance(balance);
                user.setSMSRemain(0);
                consumption.setFee(-0.1 * remain);
                System.out.println("本次短信发送 " + SMSAmount + " 条，" +
                        "花费 " + -0.1 * remain + " 元");
            } else {
                consumption.setFee(0);
                System.out.println(FREE_NOTIF + remain + "条");
            }
            HQLUtil.update(user);
            HQLUtil.add(consumption);
            return true;

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addLocalDataUsage(String username, double localDataUsage) {
        try {
            User user = userDAO.findUserByUserName(username);
            boolean isOrdered = bundleDAO.isOrdered(user, BundleType.Local); // 是否订购过套餐
            double p = isOrdered? 3: 2;

            double balance = user.getBalance();
            double localDataRemain = user.getLocalDataRemain();
            double remain = localDataRemain - localDataUsage;
            user.setLocalDataRemain(remain);

            Consumption consumption = new Consumption();
            consumption.setLocalDate(DateUtil.getCurrentDate());
            consumption.setLocalDataUsage(localDataUsage);
            consumption.setUser(user);

            if(remain < 0) {
                System.out.println("套餐本地流量已用完");
                balance -= (-p) * remain;
                user.setBalance(balance);
                user.setLocalDataRemain(0);
                consumption.setFee(-p * remain);
                System.out.println("本次本地流量花费 " + localDataUsage + "M, 花费" +
                -p * remain + " 元");
            } else {
                consumption.setFee(0);
                System.out.println(FREE_NOTIF + remain + "M");

            }

            HQLUtil.update(user);
            HQLUtil.add(consumption);
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

            double domesticDataRemain = user.getDomesticDataRemain();
            double balance = user.getBalance();
            double remain = domesticDataRemain - domesticDataUsage;
            user.setDomesticDataRemain(remain);

            if (remain < 0 ) {
                System.out.println("国内流量套餐用完");
                balance -= (-3) * remain;
                user.setBalance(balance);
                user.setDomesticDataRemain(0);
                consumption.setFee(-3 * remain);
                System.out.println("本次国内流量花费 " + domesticDataRemain + "M, 花费" +
                        -3 * remain + " 元");
            } else {
                consumption.setFee(0);
                System.out.println(FREE_NOTIF + remain + "M");
            }
            HQLUtil.update(user);
            HQLUtil.add(consumption);
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
