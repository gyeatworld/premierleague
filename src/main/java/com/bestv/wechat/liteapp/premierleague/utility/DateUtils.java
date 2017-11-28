package com.bestv.wechat.liteapp.premierleague.utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static Date praseGMTStringToDate(String strGMT){
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
        Date dDate = null;
        try {
            dDate = sdf.parse(strGMT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dDate;
    }

    public static Timestamp praseDateToTimestamp(Date dDate){
        return new Timestamp(dDate.getTime());
    }
}
