package com.weather;

import java.util.ArrayList;
import java.util.Calendar;

public class VisibilityGraph {

    //能见度，每小时更新
    //http://image.nmc.cn/product/2019/09/19/APWF/medium/SEVP_NMC_APWF_SOB_EQ2_ACHN_L89_P9_20190919210000000.jpg
    public static String basicUrla = "http://image.nmc.cn/product/";
    public static String basicUrlb = "/APWF/medium/SEVP_NMC_APWF_SOB_EQ2_ACHN_L89_P9_";
    public static String basicUrlc = "0000000.jpg";
    public static int [] awakeHour = { 0 , 1 , 2 , 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    public static boolean isStoreOk = false;
    public static int nowHour  = -1;


    public static String getHourId( int hour)
    {
        String result = "";
        int re = 0;
        re = hour -1 ;
        if( re < 0)
        {
            re = 23;
        }
        result += (re < 10 ? "0" : "") + re;
        return result;
    }

    public static ArrayList<String> getUrl()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE) ;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if( hour -1 < 0)
        {
            calendar.add(Calendar.DATE,   -1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day  = calendar.get(Calendar.DATE) ;
        }
        String formalMonth = "" + (month < 10 ? '0' : "") + month;
        String formalDay = "" + ( day < 10 ? '0' : "") + day;
        String result1 = basicUrla;
        result1 += year + "/";
        result1 += "" + formalMonth + "/";
        result1 += "" + formalDay;
        result1 += basicUrlb;
        result1 += year;
        result1 += formalMonth;
        result1 += formalDay;
        result1 += "" + getHourId(hour);
        result1 += basicUrlc;
        ArrayList<String> finalResult = new ArrayList<String>();
        finalResult.add(result1);
        return finalResult;

    }

    public static ArrayList<String>  getName()
    {
        ArrayList<String> finalResult = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        String  result1;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        result1 = "com.weather.VisibilityGraph" + year + ( month < 10 ? '0' : "") + month + ( day < 10 ? '0' : "") + day + (hour -1) +".jpg";
        finalResult.add(result1);
        return finalResult;
    }

    public static boolean isTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        boolean result = false;
        boolean flag = false;
        for( int i = 0; i < awakeHour.length; i++)
        {
            if(hour == awakeHour[i])
            {
                flag = true;
                break;
            }
        }
        if( flag )
        {
            if(isStoreOk == false)
                result = true;
            else
                result = false;
        }
        else
        {
            result = false;
        }
        if( nowHour != hour)
        {
            isStoreOk = false;
            nowHour = hour;
        }
        return  result;
    }
}
