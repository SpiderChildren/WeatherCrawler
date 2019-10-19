package com.chenshuyu.reptile;

import java.util.ArrayList;

public class GetAllChinaDM {

    private static ArrayList<ChinaDM> chinaDMS = new ArrayList<>();


    static {
        // 三个类型，7个层次
        ChinaDM bg_chart = new ChinaDM(Kind.weatherchart, Level.ground);
        ChinaDM bg_cloud = new ChinaDM(Kind.cloud, Level.ground);
        ChinaDM bg_radar = new ChinaDM(Kind.radar, Level.ground);

        ChinaDM nine_chart = new ChinaDM(Kind.weatherchart, Level.ninetwofive);
        ChinaDM nine_cloud = new ChinaDM(Kind.cloud, Level.ninetwofive);
        ChinaDM nine_radar = new ChinaDM(Kind.radar, Level.ninetwofive);

        ChinaDM eight_chart = new ChinaDM(Kind.weatherchart, Level.eightfivezero);
        ChinaDM eight_cloud = new ChinaDM(Kind.cloud, Level.eightfivezero);
        ChinaDM eight_radar = new ChinaDM(Kind.radar, Level.eightfivezero);

        ChinaDM seven_chart = new ChinaDM(Kind.weatherchart, Level.sevenzerozero);
        ChinaDM seven_cloud = new ChinaDM(Kind.cloud, Level.sevenzerozero);
        ChinaDM seven_radar = new ChinaDM(Kind.radar, Level.sevenzerozero);

        ChinaDM five_chart = new ChinaDM(Kind.weatherchart, Level.fivezerozero);
        ChinaDM five_cloud = new ChinaDM(Kind.cloud, Level.fivezerozero);
        ChinaDM five_radar = new ChinaDM(Kind.radar, Level.fivezerozero);

        ChinaDM two_chart = new ChinaDM(Kind.weatherchart, Level.twozerozero);
        ChinaDM two_cloud = new ChinaDM(Kind.cloud, Level.twozerozero);
        ChinaDM two_radar = new ChinaDM(Kind.radar, Level.twozerozero);

        ChinaDM one_chart = new ChinaDM(Kind.weatherchart, Level.onezerozero);
        ChinaDM one_cloud = new ChinaDM(Kind.cloud, Level.onezerozero);
        ChinaDM one_radar = new ChinaDM(Kind.radar, Level.onezerozero);

        chinaDMS.add(bg_chart);
        chinaDMS.add(bg_cloud);
        chinaDMS.add(bg_radar);

        chinaDMS.add(nine_chart);
        chinaDMS.add(nine_cloud);
        chinaDMS.add(nine_radar);

        chinaDMS.add(eight_chart);
        chinaDMS.add(eight_cloud);
        chinaDMS.add(eight_radar);

        chinaDMS.add(seven_chart);
        chinaDMS.add(seven_cloud);
        chinaDMS.add(seven_radar);

        chinaDMS.add(five_chart);
        chinaDMS.add(five_cloud);
        chinaDMS.add(five_radar);

        chinaDMS.add(two_chart);
        chinaDMS.add(two_cloud);
        chinaDMS.add(two_radar);

        chinaDMS.add(one_chart);
        chinaDMS.add(one_cloud);
        chinaDMS.add(one_radar);
    }

    public static void getAll() {
        for (ChinaDM chinaDM : chinaDMS) {
            chinaDM.getPic();
        }
    }
}
