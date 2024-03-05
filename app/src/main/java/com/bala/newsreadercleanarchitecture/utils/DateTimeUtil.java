package com.bala.newsreadercleanarchitecture.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

    public static String parseEventDate(String time) {
        String outputPattern = "dd MMM yyyy hh:mm:ss a";
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());
        Date date;
        String str = null;
        try {
            date = inputFormat.parse(time);
            if (date != null) {
                str = outputFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
