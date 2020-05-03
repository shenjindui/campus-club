package cn.fjut.gmxx.campusclub.utlis;

import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-02-25 16:45
 **/
public class UrlUtils {
    public  static String baseUrl="http://localhost:8889/images";
    public static  List<Map<String,Object>> getTrueUrl(List<Map<String,Object>> dataUrl){
        //数据库文件格式如下：D:/profile/upload/2020/02/07/69a5feb7eaf79d7bc69fc43ec1b14fed.png
        for (Map<String,Object> map:dataUrl) {
            String result=baseUrl+MapUtils.getString(map,"fileRte").split(":")[1];
            map.put("fileRte",result);
        }
        return  dataUrl;
    }
    public static  String getTrueUrlByString(String dataUrl){
        //数据库文件格式如下：D:/profile/upload/2020/02/07/69a5feb7eaf79d7bc69fc43ec1b14fed.png
            String result=baseUrl+dataUrl.split(":")[1];
            return  result;
    }
    public static String changeUrlType(String url){
        String[] aray=url.split("\\\\");
        url="";
        for(int i=0;i<aray.length;i++){
            url+=aray[i]+"/";
        }
        return url;
    }

    public static void main(String[] args) {
        UrlUtils.changeUrlType("D:\\profile\\upload\\");
    }

}
