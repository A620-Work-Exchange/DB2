package dao;

import domain.Consumption;

import java.time.LocalDate;
import java.util.List;

public class BillDAO {
    private ConsumptionDAO consumptionDAO = new ConsumptionDAO();

    /**
     * 按月生成账单, 制定日期格式输入为yyyy-mm如2018-06
     * @param date
     * @return
     */
    public boolean addBill(String date) {
        try {
            List<Consumption> consumptionList = consumptionDAO.listConsumptionByMonth(date);
            double callUsage;
            int SMSUsage;
            double localDataUsage;
            double domesticDataUsage;
            double callFee;
            double SMSFee;
            double localDataFee;
            double domesticDataFee;
            double sumFee;
            for(Consumption consumption: consumptionList) {

            }
            return true;

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
