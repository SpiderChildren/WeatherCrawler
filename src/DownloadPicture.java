import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadPicture {



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
            bis = new BufferedInputStream(inputStream);
            byte b[] = new byte[1024];
            int len = 0;
            out = new FileOutputStream(file0 + "\\" +name );
//            out = new FileOutputStream(file0 + "/" +name );
            while ((len = bis.read(b)) != -1) {
                out.write(b, 0, len);
            }
            System.out.println("下载完成: " + name);
            return true;
        }
        catch ( PictureNotExitException p)
        {
                System.out.println(p.toString());
                System.out.println("name: " + p.getName());
                System.out.println("url: " + p.getUrl());

            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
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
