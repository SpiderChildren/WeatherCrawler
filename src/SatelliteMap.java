import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class SatelliteMap {

    //卫星图，每天更新
    //http://www.temis.nl/airpollution/no2col/data/gome2b/2019/08/world_no2total20190822_sm.png";
    //http://www.temis.nl/airpollution/no2col/data/gome2b/2019/08/world_no2trop20190822_sm.png
    public static String basicUrla = "http://www.temis.nl/airpollution/no2col/data/gome2b/";
    public static String basicUrlb = "/world_no2total";
    public static String basicUrlc = "/world_no2trop";
    public static String basicUrld = "_sm.png";
    public static boolean isStoreOk = false;

    public static int awakeHour = 8; // need to change

    public static ArrayList<String> getUrl()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,   -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE) ;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String formalMonth = "" + (month < 10 ? '0' : "") + month;
        String formalDay = "" + ( day < 10 ? '0' : "") + day;
        String result1 = basicUrla;
        String result2 = basicUrla;
        result1 += year + "/";
        result2 += year + "/";
        result1 += "" + formalMonth;
        result2 += "" + formalMonth;
        result1 += basicUrlb;
        result2 += basicUrlc;
        result1 += year;
        result2 += year;
        result1 += formalMonth;
        result2 += formalMonth;
        result1 += formalDay;
        result2 += formalDay;
        result1 += basicUrld;
        result2 += basicUrld;
        ArrayList<String>  finalResult = new ArrayList<String>();
        finalResult.add(result1);
        finalResult.add(result2);
        return finalResult;

    }

    public static boolean isTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,   -1);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        boolean result = false;
        if( hour  == awakeHour  && isStoreOk == false )
        {
            result = true;
        }
        else if( hour != awakeHour)
        {
            isStoreOk = false;
            result = false;
        }
        else
        {
            result = false;
        }
        return  result;
    }

    public static ArrayList<String>  getName()
    {
        ArrayList<String> finalResult = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,   -1);
        String  result1;
        String result2;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE);
        result1 = "SatelliteTotal" + year + ( month < 10 ? '0' : "") + month + ( day < 10 ? '0' : "") + day + ".png";
        result2 = "SatelliteTrop" + year + ( month < 10 ? '0' : "") + month + ( day < 10 ? '0' : "") + day + ".png";
        finalResult.add(result1);
        finalResult.add(result2);
        return finalResult;
    }

//    public static void main( String argus [])
//    {
//        getName();
//    }


}
