package dao;

import domain.Bundle;
import domain.User;
import util.DateUtil;
import util.HQLUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean addUser(String name, String password) {
        long start = System.currentTimeMillis();
        User user = new User(name, password);
        try {
            HQLUtil.add(user);
            long end = System.currentTimeMillis();
            System.out.println("增加用户耗时 " + (end -start) + "ms");
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User findUserByUserName(String username) {
        String oprdStr = "from User where username = '" + username + "'";
        ArrayList<User> list = (ArrayList<User>) HQLUtil.find(oprdStr);
        if(list.size() == 0 || list == null) {
            System.out.println("用户不存在");
           return new User();
        }
        return list.get(0);
    }

   public boolean topUp(String username, double amount) {
       try {
           User user = findUserByUserName(username);
           double balance = user.getBalance();
           user.setBalance(balance + amount);
           HQLUtil.update(user);
           return true;
       } catch (Exception ex) {
           ex.printStackTrace();
           return false;
       }
   }

   public void topup(String username, double amount) {
       User user = findUserByUserName(username);
       if(user == null) {
           System.out.println("用户不存在...");
           return;
       }
       double balance = user.getBalance();
       user.setBalance(balance + amount);
       HQLUtil.update(user);
   }

   public double checkBalance(String username) {
        return findUserByUserName(username).getBalance();
   }



   private List<Bundle> efficientBundle(LocalDate date, User user) {
        List<Bundle> list = new ArrayList<>();
        for(Bundle bundle: user.getBundleList()) {
            if(DateUtil.isEfficient(bundle.getEndDate(), date)) {
                list.add(bundle);
            }
        }
         return list;
   }
}
