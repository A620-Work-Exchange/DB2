package util;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static  LocalDate getFirstDayNextMonth() {
        LocalDate today = LocalDate.now();

        return today.with(TemporalAdjusters.firstDayOfNextMonth());
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
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDateTime getCurrentDaytoSeconds() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public static void main(String[] args) {
        System.out.println(isEfficient(getFirstDayNextMonth(), getCurrentDate()));
        LocalDate endDate = LocalDate.parse("2019-01-01");
        LocalDate currentDate = LocalDate.parse("2020-01-01");
        System.out.println(isEfficient(endDate, currentDate));
        System.out.println(getLastDayOfCurrentMonth());
        System.out.println("*** " + getCurrentDaytoSeconds());
        System.out.println(LocalDate.parse("2018-06"));
    }

}
