import java.util.ArrayList;
import java.util.Calendar;

public class GlobalSatellite {

    //全球卫星图，每天更新
    //http://www.temis.nl/protocols/o3col/data/omi/o3doas_yesterday.gif

    public static String basicUrl = "http://www.temis.nl/protocols/o3col/data/omi/o3doas_yesterday.gif";
    public static boolean isStoreOk = false;
    public static int awakeHour = 14; // need to change

    public static ArrayList<String> getUrl()
    {

        String result1 = basicUrl;
        ArrayList<String>  finalResult = new ArrayList<String>();
        finalResult.add(result1);
        return finalResult;

    }
    public static boolean isTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        boolean result = false;
        if (hour == awakeHour && isStoreOk == false) {
            result = true;
        } else if (hour != awakeHour) {
            isStoreOk = false;
            result = false;
        } else {
            result = false;
        }
        return result;
    }

    public static ArrayList<String>  getName()
    {
        ArrayList<String> finalResult = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        String  result1;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day  = calendar.get(Calendar.DATE);
        result1 = "GlobalSatellite" + year + ( month < 10 ? '0' : "") + month + ( day < 10 ? '0' : "") + day + ".gif";
        finalResult.add(result1);
        return finalResult;
    }


}
