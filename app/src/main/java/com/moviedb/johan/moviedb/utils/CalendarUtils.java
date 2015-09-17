package com.moviedb.johan.moviedb.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Johan on 16/09/15.
 */
public class CalendarUtils {

    public static String calendarToString(Calendar cal){
        if(cal == null) return "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return format.format(cal.getTime());
    }

}
