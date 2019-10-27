package com.chenshuyu.reptile;

import com.weather.DownloadPicture;
import com.weather.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChinaDM {

    // 类型 -- 基本天气分析，叠加卫星云图，叠加雷达拼图
    private Kind kind = Kind.weatherchart;

    // 七个层次 -- 地面、925hPa、850hPa、700hPa、500hPa、200hPa、100hPa
    private Level level = Level.ground;

    // 网址
    private String dmurl = "http://www.nmc.cn/publish/observations/china/dm/"
            + kind.toString() + "-h" + level.getNumber() + ".htm";

    // 私有无参构造函数
    private ChinaDM() {
    }

    // 根据层次和类型构造函数
    public ChinaDM(Kind kind, Level level) {
        this.kind = kind;
        this.level = level;
        this.dmurl = "http://www.nmc.cn/publish/observations/china/dm/"
                + kind.toString() + "-h" + level.getNumber() + ".htm";
    }

    /**
     * 获得某个层次某个类型对应的图片
     *
     * @return
     */
    public void getPic() {
        Map<String, String> picurls = new LinkedHashMap<>();
        try {
            // 利用jsoup和网页建立链接，获得HTML文档
            Document document = Jsoup.connect(dmurl).get();
            // 先获得图片块
            Elements eles = document.getElementsByClass("jcarousel-skin-tango");
            // 存所有图片的网址
            for (Element ele : eles) {
                // 获得带有img标签
                Elements pics = ele.getElementsByTag("img");
                Elements names = ele.getElementsByClass("time");
                if (pics.size() != names.size()) {
                    // 异常
                    return;
                }
                for (int i = 0; i < names.size(); i++) {
                    //  字符串切割找出其中图片的源url
                    picurls.put(names.get(i).toString().split("class=\"time\">")[1].split("</p>")[0],
                            pics.get(i).toString().split("data-original=\"")[1].split("\" width=")[0]);

                }
            }
            putResult(picurls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putResult(Map<String, String> result) {
        System.out.println(kind.getDescribe() + " " + level.getDescribe());
        for (Map.Entry<String, String> entry : result.entrySet()) {
            String mapKey = entry.getKey();
            String time = mapKey.substring(0 , 8 ) + mapKey.substring( 9 , 11)+"0000";
            mapKey =kind.getDescribe() + level.getDescribe() + mapKey.substring(0 , 8 ) + mapKey.substring( 9 , 11) +".jpg";
            String mapValue = entry.getValue();
            System.out.println(mapKey + ": " + mapValue);
            Boolean downloadRe = DownloadPicture.download( mapValue , Main.storeUrl , mapKey);
            Main.deelRetry(downloadRe ,mapValue , mapKey ,Main.china ,time, false);
        }
    }

    public Kind getKind() {
        return kind;
    }

    public Level getLevel() {
        return level;
    }

    public String getDmurl() {
        return dmurl;
    }

}
