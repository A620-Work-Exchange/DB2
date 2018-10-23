package util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static  LocalDate getFirstDayNextMonth() {
        LocalDate today = LocalDate.now();
        LocalDate resDay = today.with(TemporalAdjusters.firstDayOfNextMonth());
        return resDay;
    }

    public static LocalDate getDateFewMonthLaterFromToday(int monthPeriod) {
        return getCurrentDate().plusMonths(monthPeriod);
    }

    public static LocalDate getFewMonthLaterFromFirstDayNextMonth(int period) {
        return getFirstDayNextMonth().plusMonths(period);
    }

    public static boolean isEfficient(LocalDate endDate, LocalDate currentDate) {
        if(endDate.compareTo(currentDate) > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isEfficient(getFirstDayNextMonth(), getCurrentDate()));
        LocalDate endDate = LocalDate.parse("2019-01-01");
        LocalDate currentDate = LocalDate.parse("2020-01-01");
        System.out.println(isEfficient(endDate, currentDate));
    }

}
