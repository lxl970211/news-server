package utils;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;


/**
 * Created by lxl97 on 2016/7/26.
 */
public class GetNews {
	
	/**
     * 得到新闻json数据
     * @param url
     * @return
     */
    public String getJson(String urlstr){
        String jsonData = null;
        try {
        	URL url = new URL(urlstr);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //请求类型
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            //设置连接超时时间
            conn.setConnectTimeout(6 * 1000);
            conn.setRequestProperty("Content-type", "text/html");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.connect();

            if (conn.getResponseCode() == 200 ){
                byte[] bytes = new byte[1024];
                InputStream is = conn.getInputStream();
                
                int a = 0;

                while ((a = is.read(bytes, 0, bytes.length)) >0){
                    bos.write(bytes, 0, a);
                }
               
                byte[] bytes1 = bos.toByteArray();
                //新闻json数据
                jsonData = new String(bytes1);
                
            }else {
                System.out.println("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonData;

    }
	
	public String newsUrl(String newsType){
		
		return "http://v.juhe.cn/toutiao/index?type="+newsType+"&key=36e3354e519719700563b16e67cf04cd";
	}
	

}
