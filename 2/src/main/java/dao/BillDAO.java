package dao;

import domain.Bill;
import domain.Consumption;
import domain.User;
import util.HQLUtil;

import java.time.LocalDate;
import java.util.List;

public class BillDAO {
    private ConsumptionDAO consumptionDAO = new ConsumptionDAO();
    private UserDAO userDAO = new UserDAO();
    /**
     * 按月生成账单, 制定日期格式输入为yyyy-mm如2018-06
     * @param date
     * @return
     */
    public boolean addBill(String date, String username) {
        try {
            long start = System.currentTimeMillis();
            List<Consumption> consumptionList = consumptionDAO.listConsumptionByMonth(date, username);
            int SMSUsage = 0;
            double callUsage =.0, localDataUsage = .0, domesticDataUsage = .0,
                    callFee = .0, SMSFee = .0, localDataFee = .0,
                    domesticDataFee = .0, sumFee = .0;
            for(Consumption consumption: consumptionList) {
                callUsage += consumption.getCallUsage();
                SMSUsage += consumption.getSMSUsage();
                localDataUsage += consumption.getLocalDataUsage();
                domesticDataUsage += consumption.getDomesticDataUsage();
                callFee += consumption.getCallFee();
                SMSFee += consumption.getSMSFee();
                localDataFee += consumption.getLocalDataFee();
                domesticDataFee += consumption.getDomesticDataFee();
                sumFee += consumption.getFee();
            }
            User user = userDAO.findUserByUserName(username);

            Bill bill = new Bill(date, user, callUsage, SMSUsage, localDataUsage,
                    domesticDataUsage, callFee, SMSFee, localDataFee, domesticDataFee,
                    sumFee);
            HQLUtil.add(bill);
            long end = System.currentTimeMillis();


            System.out.println("           " + date + "账单");
            System.out.println("用户名: " + username);
            System.out.println("--------------套餐统计---------------");
            System.out.println("通话时间: " + callUsage + "分钟 短信条数: " + SMSUsage
            + "条 本地流量: " + localDataUsage + "M 国内流量: " + domesticDataUsage + "M");
            System.out.println("----------------费用-----------------");
            System.out.println("通话费用: " + callFee + "元 短信费用: " + SMSFee
                    + "元 本地流量费用: " + localDataFee + "元 国内流量费用: " + domesticDataFee + "元");
            System.out.println("-----------合计---------");
            System.out.println("总话费: "+sumFee + " 余额: " + user.getBalance());
            System.out.println("----------套餐剩余----------");
            System.out.println("话费套餐: " + user.getCallRemain() + "分钟 " +
                    "  短信套餐: " + user.getSMSRemain() + "条 ");
            System.out.println("本地流量套餐: " + user.getLocalDataRemain() + "M " +
                    "国际流量套餐: " + user.getDomesticDataRemain() + "M");

            System.out.println("本次查询订单耗时" + (end - start) + "ms");
            return true;

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
