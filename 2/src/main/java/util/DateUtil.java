package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date getCurrentDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            System.out.println(date);
            String dateStr = dateFormat.format(date);
            System.out.println(dateStr);
            return dateFormat.parse(dateStr);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Date();
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDate());
    }

}
