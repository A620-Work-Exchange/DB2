package dao;

import domain.Bundle;
import domain.User;
import domain.enumeration.BundleType;
import util.DateUtil;
import util.HQLUtil;
import util.TypeStringConverter;

import java.time.LocalDate;
import java.util.*;

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
            long start = System.currentTimeMillis();
            LocalDate beginDate = DateUtil.getCurrentDate();
            LocalDate endDate = DateUtil.getDateFewMonthLaterFromToday(period);
            Bundle bundle = new Bundle(bundleType, beginDate, period, endDate);
            User user = userDAO.findUserByUserName(username);

            List<Bundle> bundleList = user.getBundleList();

            bundleList.add(bundle);

            user.setBundleList(bundleList);
            updateBalance(user, bundle);
            long end = System.currentTimeMillis();
            System.out.println("您已成功订购套餐(立即生效)...");
            System.out.println("订购套餐(立即生效)花费时间" + (end - start) + "ms");
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
            long start = System.currentTimeMillis();
            LocalDate beginDate = DateUtil.getFirstDayNextMonth();
            LocalDate endDate = DateUtil.getFewMonthLaterFromFirstDayNextMonth(period);
            Bundle bundle = new Bundle(bundleType, beginDate, period, endDate);
            User user = userDAO.findUserByUserName(username);
            List<Bundle> bundleSet = user.getBundleList();
            bundleSet.add(bundle);
            user.setBundleList(bundleSet);
          //  此时只需将套餐加入用户套餐列表而不需做任何处理!
            HQLUtil.update(user);

            long end = System.currentTimeMillis();
            System.out.println("您已成功订购套餐(次月生效)...");
            System.out.println("订购套餐(次月生效)花费时间" + (end - start) + "ms");

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
            long start = System.currentTimeMillis();
            User user = userDAO.findUserByUserName(username);
            List<Bundle> bundleSet = user.getBundleList();
            Bundle bundle = findBundleById(buddleId);
            bundleSet.remove(bundle);
            user.setBundleList(bundleSet);
            if(bundleSet.size() == 0) {
                System.out.println("您已退订所有套餐...");
            }
            HQLUtil.update(user);
            HQLUtil.delete(bundle);
            long end = System.currentTimeMillis();
            System.out.println("取消套餐(立即)");
            System.out.println("取消套餐(立即)花费时间" + (end - start) + "ms");
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
            long start = System.currentTimeMillis();
            User user = userDAO.findUserByUserName(username);
            List<Bundle> bundleSet = user.getBundleList();
            for (Bundle bundle: bundleSet) {
                if(bundle.getId() == buddleId) {
                    Bundle bundle1 = bundle;
                    bundle1.setEndDate(DateUtil.getLastDayOfCurrentMonth());
                    bundleSet.remove(bundle);
                    bundleSet.add(bundle1);
                    break;
                }
            }
            user.setBundleList(bundleSet);
            HQLUtil.update(user);
            long end = System.currentTimeMillis();
            System.out.println("取消套餐(次月生效)");
            System.out.println("取消套餐(次月生效)花费时间" + (end - start) + "ms");
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

    public List<Bundle> listBundleByUsername(String username) {
        try {
            long start = System.currentTimeMillis();
            User user = userDAO.findUserByUserName(username);
            List<Bundle> list = new ArrayList<>(user.getBundleList());
            if(list == null || list.size() == 0) {
                System.out.println("无套餐");
            }
            for(Bundle bundle: list) {
                System.out.println("套餐id: " + bundle.getId()+" 开始日期: " + bundle.getBeginDate()+" 套餐类型: " +
                        TypeStringConverter.convertTypeToString(bundle.getBundleType())
                        + " 结束日期: " + bundle.getEndDate());
            }
            long end = System.currentTimeMillis();
            System.out.println("查询套餐时间" + (end - start) + "ms...");
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isOrdered(User user, BundleType bundleType) {
        List<Bundle> bundleSet = user.getBundleList();
        for(Bundle bundle: bundleSet) {
            if(isEfficient(bundle) && bundle.getBundleType().equals(bundleType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEfficient(Bundle bundle) {
        return DateUtil.isEfficient(bundle.getEndDate(), LocalDate.now());
    }

    public void updateBalance(User user, Bundle bundle) {
        double balance = user.getBalance();
        double baseFee = .0;
        int callRemain = user.getCallRemain();
        int SMSRemain = user.getSMSRemain();
        double localDataRemain = user.getLocalDataRemain();
        double domesticDataRemain = user.getDomesticDataRemain();

        switch (bundle.getBundleType()) {
            case Call:
                baseFee = 20;
                user.setCallRemain(callRemain + 100);
                break;
            case SMS:
                baseFee = 10;
                user.setSMSRemain(SMSRemain + 200);
                break;
            case Local:
                baseFee = 20;
                user.setLocalDataRemain(localDataRemain + 2000);
                break;
            case Domestic:
                baseFee = 30;
                user.setDomesticDataRemain(domesticDataRemain + 2000);
                break;
        }

        balance -= baseFee;
        user.setBalance(balance);
        HQLUtil.update(user);
    }

    /**
     * 每个月月初初始化用户信息
     */
    public void initUserInfoAtFirstDayOfMonth(LocalDate localDate) {
        ArrayList<User> users = (ArrayList)HQLUtil.find("from User");
        // System.out.println(users.size());
        for(User user: users) {
            List<Bundle> bundleList = user.getBundleList();
            if (bundleList != null ) {
                for(Bundle bundle: bundleList) {
                    updateBalance(user, bundle);
                }
            }
        }

        HQLUtil.update(users);

    }



}
