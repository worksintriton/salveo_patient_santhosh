package com.salveo.mysalveo.appUtils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ConvertUtil {
    private static final String TAG = "ConvertUtil";
    public static String convertToStandardTime(String dateAndTime) {


        if (dateAndTime == null) {
            return "";
        }

        SimpleDateFormat oldFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        oldFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        String dueDateAsNormal = "";
        try {
            value = oldFormatter.parse(dateAndTime);
            SimpleDateFormat newFormatter = new SimpleDateFormat("dd-MM-yyyy / hh:mm a");

            newFormatter.setTimeZone(TimeZone.getDefault());
            dueDateAsNormal = newFormatter.format(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dueDateAsNormal;


    }
    public static String setOnlyTime(String dateAndTime) {

        Log.d(TAG, "setOnlyTime: "+ dateAndTime);
        if (dateAndTime == null) {
            return "";
        }

        SimpleDateFormat oldFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        oldFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        String dueDateAsNormal = "";
        try {
            value = oldFormatter.parse(dateAndTime);
            SimpleDateFormat newFormatter = new SimpleDateFormat("hh:mm a");

            newFormatter.setTimeZone(TimeZone.getDefault());
            dueDateAsNormal = newFormatter.format(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "setOnlyTime: set to "+ dueDateAsNormal);

        return dueDateAsNormal;


    }


    public static String convertToStandardTime(String dateAndTime , String readingFormat, String writingFormat) {


        String formattedDate = dateAndTime;

        DateFormat readFormat = new SimpleDateFormat(readingFormat, Locale.getDefault());
        DateFormat writeFormat = new SimpleDateFormat(writingFormat, Locale.getDefault());

        Date date = null;
        if (dateAndTime != null) {

            try {
                date = readFormat.parse(dateAndTime);
            } catch (ParseException e) {
                Log.d(TAG, "convertToStandardTime: " + e.getMessage());
            }

            if (date != null) {
                formattedDate = writeFormat.format(date);
            }

            return formattedDate;
        } else {
            return "";
        }
    }




}
