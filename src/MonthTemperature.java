import java.util.ArrayList;
import java.util.Calendar;

public class MonthTemperature {

    //http://image.nmc.cn/product/2019/09/23/GISP/medium/SEVP_NMC_GISP_S99_ETH30_ACHN_L88_PB_20190923000000000.jpg
    //月最高气温,每天更新,只能看到每天的
    public static String basicUrla = "http://image.nmc.cn/product/";
    public static String basicUrlb = "/GISP/medium/SEVP_NMC_GISP_S99_ETH30_ACHN_L88_PB_";
    public static String basicUrlc = "000000000.jpg";
    public static boolean isStoreOk = false;

    public static int awakeHour = 23; // need to change

    public static ArrayList<String> getUrl()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE) ;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String formalMonth = "" + (month < 10 ? '0' : "") + month;
        String formalDay = "" + ( day < 10 ? '0' : "") + day;
        String result1 = basicUrla;
        result1 += year + "/";
        result1 += "" + formalMonth + "/";
        result1 += formalDay;
        result1 += basicUrlb;
        result1 += year;
        result1 += formalMonth;
        result1 += formalDay;
        result1 += basicUrlc;
        ArrayList<String>  finalResult = new ArrayList<String>();
        finalResult.add(result1);
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
        String  result1;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE);
        result1 = "MonthTemperature" + year + ( month < 10 ? '0' : "") + month + ( day < 10 ? '0' : "") + day + ".jpg";
        finalResult.add(result1);
        return finalResult;
    }

}
