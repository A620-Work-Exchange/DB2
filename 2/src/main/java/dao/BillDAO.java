package dao;

import java.time.LocalDate;

public class BillDAO {
    /**
     * 按月生成账单
     * @param date
     * @return
     */
    public boolean addBill(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            double callUsage = .0;
            return true;

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
