package util;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     *
     * @return
     */
    public static  LocalDate getFirstDayNextMonth() {
        return getCurrentDate().with(TemporalAdjusters.firstDayOfNextMonth());
    }

    public static LocalDate getDateFewMonthLaterFromToday(int monthPeriod) {
        return getCurrentDate().plusMonths(monthPeriod);
    }

    public static LocalDate getFewMonthLaterFromFirstDayNextMonth(int period) {
        return getFirstDayNextMonth().plusMonths(period);
    }

    public static boolean isEfficient(LocalDate endDate, LocalDate currentDate) {
        return endDate.compareTo(currentDate) > 0;
    }

    public static LocalDate getLastDayOfCurrentMonth() {
        return getCurrentDate().with(TemporalAdjusters.lastDayOfMonth());
    }



    public static String getFirstDayOfMonth(String yearMonthStr) {
        return yearMonthStr + "-01";
    }
    /**
     * 得到某年某月的最后一天，如2018-09即2018年9月的最后一天
     * @param yearMonthStr
     * @return
     */
    public static String getLastDayOfMonth(String yearMonthStr) {
        String fullStr = yearMonthStr + "-01";
        LocalDate date = LocalDate.parse(fullStr);
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.toString();
    }

    public static int getDaysBetweenMonths() {
        Period period = Period.between(DateUtil.getCurrentDate(), DateUtil.getFirstDayNextMonth());
        return period.getDays();
    }


    public static void main(String[] args) {
//        System.out.println(isEfficient(getFirstDayNextMonth(), getCurrentDate()));
//        LocalDate endDate = LocalDate.parse("2019-01-01");
//        LocalDate currentDate = LocalDate.parse("2020-01-01");
//        System.out.println(isEfficient(endDate, currentDate));
//        System.out.println(getLastDayOfCurrentMonth());
//        System.out.println("*** " + getCurrentDaytoSeconds());
//
        System.out.println(getDaysBetweenMonths());
    }

}
