package com.yts.tsdiet.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    public final static String DATE_FORMAT = "yyyy-MM-dd (E)";

    public final static String CALENDAR_LIST_HEADER_FORMAT = "yyyy년";
    public final static String CALENDAR_HEADER_FORMAT = "yyyy년 MM월";

    public final static String DAY_FORMAT = "d";

    public final static String FULL_FORMAT = "yyyy-MM-dd (E) a hh:mm";

    public final static String WRITE_RECEIVE_FORMAT = "yyyy  MM.dd E";

    public final static String CHART_X_FORMAT = "MM/dd";
    public final static String CHART_XOLD_FORMAT = "yyyy/MM/dd";


    public static String getDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        Date d = new Date(date);
        return formatter.format(d).toUpperCase();
    }

    public static String getDate(long date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
            Date d = new Date(date);
            return formatter.format(d).toUpperCase();
        } catch (Exception e) {
            return " ";
        }
    }

    public static String getXData(long date) {
        Calendar current = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTimeInMillis(date);

        String pattern = CHART_XOLD_FORMAT;
        if (current.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)) {
            pattern = CHART_X_FORMAT;
        }
        return getDate(date, pattern);

    }

    public static Calendar getCalendar(long date) {
        if (date == 0) {
            date = System.currentTimeMillis();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public static int getWeek(long date) {
        return getCalendar(date).get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isBlindView(long date) {
        return System.currentTimeMillis() <= date;
    }


}
