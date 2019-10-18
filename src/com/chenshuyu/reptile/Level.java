package com.chenshuyu.reptile;

public enum Level {
    // 层次枚举
    ground("000", "地面"),
    ninetwofive("925", "925hPa"),
    eightfivezero("850", "850hPa"),
    sevenzerozero("700", "700hPa"),
    fivezerozero("500", "500hPa"),
    twozerozero("200", "200hPa"),
    onezerozero("100", "100hPa");
    private final String number;
    private final String describe;

    Level(String number, String describe) {
        this.number = number;
        this.describe = describe;
    }

    public String getNumber() {
        return number;
    }

    public String getDescribe() {
        return describe;
    }
}
