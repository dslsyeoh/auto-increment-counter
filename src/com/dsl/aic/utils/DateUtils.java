/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateUtils
{
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy",Locale.ENGLISH);

    private DateUtils() {}

    public static LocalDate toLocalDate(Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date toDate(LocalDate localDate)
    {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static int daysDiff(LocalDate start, LocalDate end)
    {
        return Math.abs(Period.between(start, end).getDays());
    }

    public static Date toDate(String date)
    {
        try
        {
            return simpleDateFormat.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
