package dao;

import domain.Bundle;
import domain.User;
import domain.enumeration.BundleType;
import util.DateUtil;
import util.HQLUtil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BundleDAO {
    private UserDAO userDAO = new UserDAO();

    public boolean addBundleImmediately(BundleType bundleType, String username, int period) {
        try {
            LocalDate beginDate = DateUtil.getCurrentDate();
            LocalDate endDate = DateUtil.getDateFewMonthLaterFromToday(period);
            Bundle bundle = new Bundle(bundleType, beginDate, period, endDate);
            User user = userDAO.findUserByUserName(username);
            Set<Bundle> bundleSet = new HashSet<>();
            bundleSet.add(bundle);
            user.setBundleList(bundleSet);
            HQLUtil.update(user);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addBundleNextMonth(BundleType bundleType, String username, int period) {
        try {
            LocalDate beginDate = DateUtil.getFirstDayNextMonth();
            LocalDate endDate = DateUtil.getFewMonthLaterFromFirstDayNextMonth(period);
            Bundle bundle = new Bundle(bundleType, beginDate, period, endDate);
            User user = userDAO.findUserByUserName(username);
            Set<Bundle> bundleSet = new HashSet<>();
            bundleSet.add(bundle);
            user.setBundleList(bundleSet);
            HQLUtil.update(user);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }



}
