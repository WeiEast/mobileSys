package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private DateUtil(){};

    private static final long MILLI_SEC = 60000;
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

    public static String getDateString(Date date){
        return DATE_FORMAT.format(date);
    }

    public static Date createDate(int year, int month, int date, int hrs, int min, int sec){
        return new Date(year-1900, month-1, date, hrs, min, sec);
    }

    public static Date addMin(Date origin, int min){
        return new Date(origin.getTime()+min*MILLI_SEC);
    }

    public static Date nextMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
