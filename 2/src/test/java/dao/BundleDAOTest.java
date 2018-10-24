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
        User user = userDAO.findUserByUserName("陈振宇");
        Set<Bundle> bundleSet = user.getBundleList();
        for(Bundle bundle: bundleSet) {
            System.out.println("开始日期: " + bundle.getBeginDate()+" 套餐类型: " + bundle.getBundleType()
            + " 结束日期: " + bundle.getEndDate());
        }

    }

    @Test
    public void addBundleNextMonth() {
    }

    @Test
    public void removeBundleImmediately() {
    }

    @Test
    public void removeBundleUntilNextMonth() {
    }

    @Test
    public void findBundleById() {
    }
}
