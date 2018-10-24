package dao;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class BillDAOTest {
    private BillDAO billDAO = new BillDAO();

    @Test
    public void addBill() {
        billDAO.addBill("2018-10", "陈振宇");
    }
}
