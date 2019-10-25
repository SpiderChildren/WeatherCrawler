package com.weather;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadPicture {


    //
    public static boolean upload = false ;

    public static boolean download( String url , String storeUrl , String name )   {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        FileOutputStream out = null;
        try {
            File file0 = new File(storeUrl);
            if (!file0.isDirectory() && !file0.exists()) {
                file0.mkdirs();
            }


            // 建立链接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setReadTimeout(60 *1000);
            conn.setConnectTimeout(30*1000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            int  responseCode  = conn.getResponseCode();
            //获取网络输入流
            if( responseCode != HttpURLConnection.HTTP_OK)
            {
                throw  new PictureNotExitException( "Picture don't exist!" , url , name );
            }
            inputStream = conn.getInputStream();

            if(upload)
            {
                String endpoint = "http://oss-cn-beijing.aliyuncs.com";
                String accessKeyId = "LTAIKbvLeRIj2HLt";
                String accessKeySecret = "7VlL3BgOfUZEZ35Wea88RNV7FFIMAB";
                String bucketName = "ebd120-data-collection";
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                ossClient.putObject( bucketName , storeUrl+ name, inputStream);

            }
            else {
                bis = new BufferedInputStream(inputStream);
                byte b[] = new byte[1024];
                int len = 0;
                out = new FileOutputStream(file0 + "\\" + name);
//            out = new FileOutputStream(file0 + "/" +name );
                while ((len = bis.read(b)) != -1) {
                    out.write(b, 0, len);
                }
            }
            System.out.println("下载完成: " + name);
            return true;
        }
        catch ( PictureNotExitException p)
        {
                System.out.println(p.toString());
                System.out.println("失败" +"name: " + p.getName() + "url: " + p.getUrl());

            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败" +"name: " + name + "url: " + url);
            return  false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


}
