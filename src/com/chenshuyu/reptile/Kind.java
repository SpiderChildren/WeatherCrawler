package com.chenshuyu.reptile;

public enum Kind {
    weatherchart("基本天气分析"), // 基本天气分析
    radar("叠加卫星云图"), // 叠加卫星云图
    cloud("叠加雷达拼图"); // 叠加雷达拼图

    private final String describe;

    Kind(String describe){
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
