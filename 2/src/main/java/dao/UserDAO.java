package dao;

import domain.User;
import util.HQLUtil;

public class UserDAO {
    public boolean addUser(String name, String password) {
        User user = new User(name, password);
        try {
            HQLUtil.add(user);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
