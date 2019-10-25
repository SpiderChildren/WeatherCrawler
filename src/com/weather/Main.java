package com.weather;

import JDBC.ConnentToMySQL;
import com.chenshuyu.reptile.GetAllChinaDM;
import com.csvreader.CsvWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {


//    public static String storeUrl = "D:\\weatherPicture2\\";
    public  static  String storeUrl = "image/";
//    public static String storeUrl = "/home/tank/weather";


    public static  ArrayList< ArrayList<String>>  RetryList = new  ArrayList< ArrayList <String>>();   //失败的请求进行存储，继续尝试
    public static HashMap< String , Integer > timeMap = new HashMap<String , Integer>();
    public static  int tick = 0;
    public  static  int storeTime = 6;
    public static ConnentToMySQL connenter = new ConnentToMySQL();
    public static String tableName = "testpicture";
    public static  void  crawl()
    {

        downloadSatelliteMap();
        downloadUVGraph();
        downloadWindFiled();
        downloadVisibilityGraph();
        downloadGlobalSatellite();
        downloadPrecipitation();
        downloadWindFieldForecast();
        downloadMonthTemperature();
        downloadHourTemperature();
        retry();
    }

    public  static void main(String argus [])
    {

        Calendar calendar = Calendar.getInstance();
        tick = calendar.get(Calendar.HOUR_OF_DAY);
        MeteorologicalProfileMap meteorologicalProfileMap = new MeteorologicalProfileMap();
        while( true)
        {
            crawl();
            meteorologicalProfileMap.getResult();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if( hour == 8 || hour == 20) {
                GetAllChinaDM.getAll();
            }
        }

    }

    public static  void retry()
    {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE) ;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour != tick)
        {
            tick = hour;
            flag = true;
        }
        for( int i = 0; i < RetryList.size()  ; )
        {
            ArrayList<String> urlList = RetryList.get(i);
            String url = urlList.get(0);
            String name = urlList.get(1);
            if (flag)
            {
                int t  = timeMap.get(name);
                timeMap.put(name , t + 1);
                if(t + 1 > storeTime )
                {
                    deelRetry( true , url , name);
                    writeCsv("errorPicture" + year+month+day+hour+".csv" , name , url );
                    continue;
                }
            }
            boolean downloadResult  = DownloadPicture.download( url, storeUrl, name );
            deelRetry(downloadResult , url , name);
            if( downloadResult == false)
            {i++;}

        }

    }

    public static void deelRetry(boolean  downloadResult , String url , String name )
    {
        if(downloadResult == false)
        {
            //Add item to RetryQueue
            ArrayList<String> urlInformation = new ArrayList<String>();
            urlInformation.add( url);
            urlInformation.add( name);
            if(RetryList.contains( urlInformation) == false) {
                timeMap.put(name, 0);
                RetryList.add(urlInformation);
            }
        }
        else
        {
            //Delete the item in RetryQueue if it does exit.
            ArrayList<String> urlInformation = new ArrayList<String>();
            urlInformation.add( url);
            urlInformation.add( name);
            if(RetryList.contains(urlInformation))
            {
                RetryList.remove(urlInformation);
            }
            timeMap.remove( name);
//            connenter.insert(tableName , storeUrl + name , name);

        }

    }

    public static void downloadSatelliteMap()
    {
        // satellite map
        if(SatelliteMap.isTime())
        {
            ArrayList<String> temp =  SatelliteMap.getUrl();
            ArrayList<String> name = SatelliteMap.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String SatelliteMapUrl = temp.get(i);
                String SatelliteMapName = name.get(i);
                boolean downloadResult  = DownloadPicture.download(SatelliteMapUrl, storeUrl, SatelliteMapName );
                SatelliteMap.isStoreOk = downloadResult;
                deelRetry( downloadResult , SatelliteMapUrl , SatelliteMapName);

            }
        }
    }

    public static void downloadUVGraph()
    {
        //UV graph
        if( UVGraph.isTime())
        {
            ArrayList<String> temp =  UVGraph.getUrl();
            ArrayList<String> name = UVGraph.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String UVGraphUrl = temp.get(i);
                String UVName = name.get(i);
                boolean downloadResult  = DownloadPicture.download( UVGraphUrl, storeUrl, UVName );
                UVGraph.isStoreOk = downloadResult;
                deelRetry(downloadResult , UVGraphUrl , UVName);


            }
        }
    }

    public static void downloadWindFiled()
    {
        //wind filed map
        if( WindField.isTime())
        {
            ArrayList<String> temp =  WindField.getUrl();
            ArrayList<String> name = WindField.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String WindFieldMapUrl = temp.get(i);
                String WindName = name.get(i);
                boolean downloadResult  = DownloadPicture.download( WindFieldMapUrl, storeUrl, WindName );
                WindField.isStoreOk = downloadResult;
                deelRetry( downloadResult , WindFieldMapUrl , WindName);
            }
        }
    }

    public static void downloadVisibilityGraph()
    {

        //com.weather.VisibilityGraph
        if(VisibilityGraph.isTime())
        {
            ArrayList<String> temp =  VisibilityGraph.getUrl();
            ArrayList<String> name = VisibilityGraph.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String VisibilityGraphUrl = temp.get(i);
                String VisiName = name.get(i);
                boolean downloadResult  = DownloadPicture.download( VisibilityGraphUrl, storeUrl, VisiName );
                VisibilityGraph.isStoreOk = downloadResult;
                deelRetry(downloadResult , VisibilityGraphUrl , VisiName);

            }
        }
    }

    public static  void downloadGlobalSatellite()
    {
        if( GlobalSatellite.isTime())
        {
            ArrayList<String> temp =  GlobalSatellite.getUrl();
            ArrayList<String> name = GlobalSatellite.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String GlobalSatelliteUrl = temp.get(i);
                String GlobalName = name.get(i);
                boolean downloadResult  = DownloadPicture.download( GlobalSatelliteUrl, storeUrl, GlobalName );
                GlobalSatellite.isStoreOk = downloadResult;
                deelRetry(downloadResult, GlobalSatelliteUrl , GlobalName);
            }
        }
    }

    public static void downloadPrecipitation()
    {
        if(  Precipitation.isTime())
        {
            ArrayList<String> temp =  Precipitation.getUrl();
            ArrayList<String> name =  Precipitation.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String  PrecipitationUrl = temp.get(i);
                String PrecipitationName = name.get(i);
                boolean downloadResult  = DownloadPicture.download( PrecipitationUrl , storeUrl, PrecipitationName );
                Precipitation.isStoreOk = downloadResult;
                deelRetry( downloadResult , PrecipitationUrl , PrecipitationName);

            }
        }
    }

    public  static void downloadWindFieldForecast()
    {
        if(   WindFieldForecast.isTime())
        {
            ArrayList<String> temp =   WindFieldForecast.getUrl();
            ArrayList<String> name =  WindFieldForecast.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String  WFFUrl = temp.get(i);
                String WFFName = name.get(i);
                boolean  downloadResult  = DownloadPicture.download( WFFUrl , storeUrl,name.get(i)  );
                WindFieldForecast.isStoreOk = downloadResult;
                deelRetry( downloadResult , WFFUrl , WFFName);

            }
        }
    }

    public static void downloadMonthTemperature()
    {
        if(   MonthTemperature.isTime())
        {
            ArrayList<String> temp =   MonthTemperature.getUrl();
            ArrayList<String> name =  MonthTemperature.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String  MTUrl = temp.get(i);
                String MTName = name.get(i);
                boolean  downloadResult  = DownloadPicture.download(  MTUrl , storeUrl, MTName );
                MonthTemperature.isStoreOk = downloadResult;
                deelRetry( downloadResult , MTUrl , MTName);

            }
        }
    }

    public static void downloadHourTemperature()
    {
        if(HourTemperature.isTime())
        {
            ArrayList<String> temp =   HourTemperature.getUrl();
            ArrayList<String> name =  HourTemperature.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String  HUrl = temp.get(i);
                String HName = name.get(i);
                boolean  downloadResult  = DownloadPicture.download(  HUrl , storeUrl, HName );
                HourTemperature.isStoreOk = downloadResult;
                deelRetry( downloadResult, HUrl , HName);
            }

        }
    }

    public static void writeCsv(String f , String name , String url )
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            CsvWriter csvWriter = new CsvWriter( writer ,',' );
            String[] writeLine= { name , url };
            System.out.println(writeLine);
            csvWriter.writeRecord(writeLine);
            csvWriter.close();

        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
    }

}
