package dao;


import domain.User;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class ConsumptionDAOTest {
    private UserDAO userDAO = new UserDAO();
    private ConsumptionDAO consumptionDAO = new ConsumptionDAO();
    User user = userDAO.findUserByUserName("陈振宇");

    @org.junit.Test
    public void addCallUsage() {
        consumptionDAO.addCallUsage("陈振宇", 10);


    }

    @org.junit.Test
    public void addSMSUsage() {
        consumptionDAO.addSMSUsage("陈振宇", 100);
    }

    @org.junit.Test
    public void addLocalDataUsage() {
        consumptionDAO.addLocalDataUsage("陈振宇",100);
    }

    @org.junit.Test
    public void addDomesticDataUsage() {
        consumptionDAO.addDomesticDataUsage("陈振宇", 100);
    }
}
