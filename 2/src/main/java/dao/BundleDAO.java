package dao;

import domain.Bundle;
import domain.User;
import domain.enumeration.BundleType;
import util.DateUtil;
import util.HQLUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BundleDAO {
    private UserDAO userDAO = new UserDAO();

    /**
     * 增加一个立即生效的套餐
     * @param bundleType
     * @param username
     * @param period
     * @return
     */
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

    /**
     * 增加一个下月生效的套餐
     * @param bundleType
     * @param username
     * @param period
     * @return
     */
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

    /**
     * 立即退订套餐
     * @param username
     * @param buddleId
     * @return
     */
    public boolean removeBundleImmediately(String username, int buddleId) {
        try {
            User user = userDAO.findUserByUserName(username);
            Set<Bundle> bundleSet = user.getBundleList();
            Bundle bundle = findBundleById(buddleId);
            bundleSet.remove(bundle);
            user.setBundleList(bundleSet);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 如果取消套餐下个月起生效, endDate设为月底
     * @param username
     * @param buddleId
     * @return
     */
    public boolean removeBundleUntilNextMonth(String username, int buddleId) {
        try {
            User user = userDAO.findUserByUserName(username);
            Set<Bundle> bundleSet = user.getBundleList();
            for (Bundle bundle: bundleSet) {
                if(bundle.getId() == buddleId) {
                    Bundle tmpBuddle = bundle;
                    tmpBuddle.setEndDate(DateUtil.getLastDayOfCurrentMonth());
                    bundleSet.remove(bundle);
                    bundleSet.add(tmpBuddle);
                    break;
                }
            }
            return true;

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Bundle findBundleById(int id) {
        try {
            String oprdStr = "from Bundle where id = '" + id + "'";
            ArrayList<Bundle> list = (ArrayList<Bundle>) HQLUtil.find(oprdStr);
            if(list.size() == 0 || list == null) {
                System.out.println("The bundle does not exist...");
                return null;
            }
            return list.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



}
