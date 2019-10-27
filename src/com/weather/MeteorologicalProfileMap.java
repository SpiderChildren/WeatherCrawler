package com.weather;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.*;

//气象廓线图
public class MeteorologicalProfileMap {
    int date = 0;
    String imgUrl = "http://weather.uwyo.edu";
    String baseUrl = "http://weather.uwyo.edu/upperair/images/2019101212.56739.stuve.parc.gif";
    private HashMap<String,String> nameToid;

    public MeteorologicalProfileMap(){
        nameToid = new HashMap<>();
        input();

    }

    private void input(){
        try {
            //1.读取Excel文档对象
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream("./src/weather.xlsx"));
            //2.获取要解析的表格（第一个表格）
            XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            //获得最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {//遍历每一行
                //3.获得要解析的行
                XSSFRow row = sheet.getRow(i);
                sheet.getRow(i).getCell(5).setCellType(CellType.STRING);
                //4.获得每个单元格中的内容（String）

                String name = row.getCell(3).getStringCellValue();
                String id = row.getCell(5).getStringCellValue();
                nameToid.put(name,id);
            }
            nameToid.remove("暂无");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String getUrl(String type, String STNM,String time){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DATE)-1;
        if (month<10&&date<10){
            return "http://weather.uwyo.edu/upperair/images/"+ year + "0" + month + "0 " + date + time + "." + STNM +"."+ type +".parc.gif";
        }else if(date<10){
            return "http://weather.uwyo.edu/upperair/images/"+ year + month + "0 " + date + time + "." + STNM +"."+ type +".parc.gif";
        }else if (month<10){
            return "http://weather.uwyo.edu/upperair/images/"+ year +"0" + month + date + time + "." + STNM +"."+ type +".parc.gif";
        }
        return "http://weather.uwyo.edu/upperair/images/"+ year + month + date + time + "." + STNM +"."+ type +".parc.gif";
    }
    //检查是否到了爬取时间
    private boolean check() {
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DATE);
        if (dates!=date){
            date = dates;
            return true;
        }else {
            return false;
        }
    }

    public void  getResult()  {
        if (check()){
            List<String>  keywords = Arrays.asList("stuve700","skewt","stuve10","stuve");
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int date = calendar.get(Calendar.DATE)-1;
            for (String key : nameToid.keySet()){
                for (String keyword : keywords){
                    String time = "" + year + ( month < 10 ? '0' : "") + month + ( date < 10 ? '0' : "") + date ;
                    boolean downloadResult1  = DownloadPicture.download(getUrl(keyword,nameToid.get(key),"00"),Main.storeUrl,key+"_"+year+"_"+month+"_"+date+"_"+keyword+"_00.gif");
                    VisibilityGraph.isStoreOk = downloadResult1;
                    Main.deelRetry(downloadResult1 ,getUrl(keyword,nameToid.get(key),"00"), key+"_"+year+"_"+month+"_"+date+"_"+keyword+"_00.gif" , key , time + "00"+"0000" , false);
//                    Main.writeCsv("first.csv" , key+"_"+year+"_"+month+"_"+date+"_"+keyword+"_00.gif",getUrl(keyword,nameToid.get(key),"00"));
                    boolean downloadResult2  = DownloadPicture.download(getUrl(keyword,nameToid.get(key),"12"),Main.storeUrl,key+"_"+year+"_"+month+"_"+date+"_"+keyword+"_12.gif");
                    VisibilityGraph.isStoreOk = downloadResult2;
                    Main.deelRetry(downloadResult2 ,getUrl(keyword,nameToid.get(key),"12"), key+"_"+year+"_"+month+"_"+date+"_"+keyword+"_12.gif" , key , time + "12"+"0000" , false);
//                    Main.writeCsv("first.csv" ,key+"_"+year+"_"+month+"_"+date+"_"+keyword+"_12.gif" , getUrl(keyword,nameToid.get(key),"12"));
                }
            }
        }

    }
}
