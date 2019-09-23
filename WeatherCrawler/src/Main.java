import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Main {


    public static String storeUrl = "D:\\weatherPicture\\";
//    public static String storeUrl = "/home/tank/weather";
//    public static String storeUrl = "/home/tank/weather2";


    public static  void  crawl()
    {

//        downloadSatelliteMap();
//        downloadUVGraph();
//        downloadWindFiled();
//        downloadVisibilityGraph();
//        downloadGlobalSatellite();
//        downloadPrecipitation();
        downloadWindFieldForecast();
    }

    public  static void main(String argus [])
    {

        while( true)
        {
            crawl();
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
                boolean downloadResult  = DownloadPicture.download(SatelliteMapUrl, storeUrl, name.get(i) );
                SatelliteMap.isStoreOk = downloadResult;

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
                boolean downloadResult  = DownloadPicture.download( UVGraphUrl, storeUrl, name.get(i) );
                UVGraph.isStoreOk = downloadResult;

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
                boolean downloadResult  = DownloadPicture.download( WindFieldMapUrl, storeUrl, name.get(i) );
                WindField.isStoreOk = downloadResult;
            }
        }
    }

    public static void downloadVisibilityGraph()
    {
        //VisibilityGraph
        if(VisibilityGraph.isTime())
        {
            ArrayList<String> temp =  VisibilityGraph.getUrl();
            ArrayList<String> name = VisibilityGraph.getName();

            for( int i = 0; i<temp.size() ; i++) {
                String VisibilityGraphUrl = temp.get(i);
                boolean downloadResult  = DownloadPicture.download( VisibilityGraphUrl, storeUrl, name.get(i) );
                VisibilityGraph.isStoreOk = downloadResult;
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
                boolean downloadResult  = DownloadPicture.download( GlobalSatelliteUrl, storeUrl, name.get(i) );
                GlobalSatellite.isStoreOk = downloadResult;

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
                boolean downloadResult  = DownloadPicture.download( PrecipitationUrl , storeUrl, name.get(i) );
                Precipitation.isStoreOk = downloadResult;

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
                boolean  downloadResult  = DownloadPicture.download( WFFUrl , storeUrl, name.get(i) );
                WindFieldForecast.isStoreOk = downloadResult;

            }
        }
    }

}
