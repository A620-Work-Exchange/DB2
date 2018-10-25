package dao;

import domain.Bundle;
import domain.User;
import domain.enumeration.BundleType;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class BundleDAOTest {
    private BundleDAO bundleDAO = new BundleDAO();
    private UserDAO userDAO = new UserDAO();

    @Test
    public void addBundleImmediately() {
        bundleDAO.addBundleImmediately(BundleType.Call, "陈振宇", 2);
        bundleDAO.addBundleImmediately(BundleType.Domestic, "陈振宇", 3);
        User user = userDAO.findUserByUserName("陈振宇");
        List<Bundle> bundleSet = user.getBundleList();
        for(Bundle bundle: bundleSet) {
            System.out.println("id: " + bundle.getId()+" 开始日期: " + bundle.getBeginDate()+" 套餐类型: " + bundle.getBundleType()
            + " 结束日期: " + bundle.getEndDate());
        }

    }

    @Test
    public void addBundleNextMonth() {
    }

    @Test
    public void removeBundleImmediately() {
        for(int i = 6; i <= 8; i ++) {
            bundleDAO.removeBundleImmediately("陈振宇", i);
        }


    }

    @Test
    public void removeBundleUntilNextMonth() {
    }

    @Test
    public void findBundleById() {
    }

    @Test
    public void listBundleByUsername() {
        List<Bundle> list = bundleDAO.listBundleByUsername("陈振宇");
        if(list == null || list.size() == 0) {
            System.out.println("对不起，您未订购任何套餐...");
            return;
        }
        for(Bundle bundle: list) {
            System.out.println("id: " + bundle.getId()+" 开始日期: " + bundle.getBeginDate()+" 套餐类型: " + bundle.getBundleType()
                    + " 结束日期: " + bundle.getEndDate());
        }
    }

    @Test
    public void isEfficient() {
        User user = userDAO.findUserByUserName("陈振宇");
        System.out.println(bundleDAO.isOrdered(user, BundleType.Call));
        System.out.println(bundleDAO.isOrdered(user, BundleType.SMS));
        System.out.println(bundleDAO.isOrdered(user, BundleType.Domestic));
        System.out.println(bundleDAO.isOrdered(user, BundleType.Local));
    }
}
