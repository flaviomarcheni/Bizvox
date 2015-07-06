package com.fmarcheni.bizvox.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Flavio on 05/07/2015.
 */
public class StringUtils {
    public static String getNotNullString(String value){
        return (value == null)?"":value;
    }


    public static String getFormattedDate(String date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getDefault());
        String formatedDate= "";
        try {
            Date parse = sdf.parse(date);
            formatedDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
            formatedDate = date;
        }
        return formatedDate;
    }
}
