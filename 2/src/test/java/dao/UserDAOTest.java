package dao;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class UserDAOTest {
    private UserDAO userDAO = new UserDAO();

    @Test
    public void addUser() {
        userDAO.addUser("刘嘉", "123456");
        userDAO.addUser("陈振宇", "123456");
    }

    @Test
    public void findUserByUserName() {
    }

    @Test
    public void topUp() {
        userDAO.topUp("陈振宇", 10000);
        userDAO.topUp("刘嘉", 5000);

    }
}
