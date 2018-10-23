package util;

import domain.Bundle;
import domain.User;
import domain.enumeration.BundleType;

import java.time.LocalDate;
import java.util.Set;

/**
 * 每个月初都会初始化一次用户的套餐服务
 */
public class FeeCalculator {

    /**
     * 计算通话费用，叠加n个通话套餐，sumFee = 20 * n if not succeed, else += (exceedTime) * 0.5
     * @param user
     * @param currentDate
     * @return
     */
    public static double getCallFee(User user, LocalDate currentDate) {
        double sumFee = .0;
        double callLimitTime = .0;
        int SMSLimit = 0;
        Set<Bundle> bundleList = user.getBundleList();


        for(Bundle bundle: bundleList) {
            if(bundle.getBundleType().equals(BundleType.Call) &&
                    DateUtil.isEfficient(bundle.getEndDate(), currentDate)) {
                sumFee += 20;
                callLimitTime += 100;
            }
            if(bundle.getBundleType().equals(BundleType.SMS)) {
                sumFee += 10;
                SMSLimit += 200;
            }
            double exceedUsage = user.getCallRemain() - callLimitTime;
            double exceedSMS = user.getSMSRemain() - SMSLimit;
            if(exceedUsage > 0) {
                sumFee += 0.5 * (-1 * Math.ceil(exceedUsage));
            }
            if(exceedSMS > 0) {
                sumFee += 0.1 * (-1 * exceedSMS);
            }
        }
        return sumFee;
    }

    public static double getDataFee(User user, LocalDate currentDate) {
        double sumFee = .0;
        Set<Bundle> bundleSet = user.getBundleList();
        double localDataLimit = .0, domesticDataLimit = .0;

        for(Bundle bundle: bundleSet) {
            if(bundle.getBundleType().equals(BundleType.Local) &&
                    DateUtil.isEfficient(bundle.getEndDate(), currentDate)){
                localDataLimit += 2000;
                sumFee += 20;
            }
            if(bundle.getBundleType().equals(BundleType.Domestic) &&
                    DateUtil.isEfficient(bundle.getEndDate(), currentDate)) {
                domesticDataLimit += 2000;
                sumFee += 30;
            }
        }
        // 分别计算超出量
        double exceedLocalDataUsage = localDataLimit - user.getLocalDataRemain();
        double exceedDomesticDataUsage = domesticDataLimit - user.getDomesticDataRemain();

        if(exceedDomesticDataUsage < 0) {
            sumFee += Math.ceil(-1 * exceedDomesticDataUsage) * 3;
        }
        if(exceedLocalDataUsage < 0) {
            sumFee += Math.ceil(-1 * exceedLocalDataUsage);
        }
        return sumFee;

    }



}
