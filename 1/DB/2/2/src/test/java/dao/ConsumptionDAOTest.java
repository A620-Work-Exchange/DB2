package dao;


import domain.Consumption;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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

    @Test
    public void listConsumptionByMonth() {
        List<Consumption> list = consumptionDAO.listConsumptionByMonth("2018-10", "陈振宇");
        for(Consumption consumption: list) {
            System.out.println("id: " + consumption.getId() + "费用" + consumption.getFee()
            + "用户" + consumption.getUser().getUsername());
        }
    }
}
