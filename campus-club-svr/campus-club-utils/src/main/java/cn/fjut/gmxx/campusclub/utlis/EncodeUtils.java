package cn.fjut.gmxx.campusclub.utlis;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : shenjindui
 * @date : 2020-01-10 10:15
 **/
public class EncodeUtils {
    /**
     * 编号的前缀+当前最大的号码
     * @param prefix
     * @param nowNum
     * @return
     */
    //最大值为99999
    public static String getConteactNo(String prefix,int nowNum ) {
        StringBuilder builder = new StringBuilder();
        StringBuilder num = new StringBuilder();
        AtomicInteger count = new AtomicInteger(nowNum+1);
        // 4位数字的采取编号处理。99999的情况下从001开始采取。
        if (count.get() > 99999) {
            count = new AtomicInteger(1);
        }

        // 采用4位数的数字进行序号处理。
        if (count.get() < 10) {
            num.append("0000").append(count.getAndIncrement());
        } else if (count.get() >= 10&&count.get()<100) {
            num.append("000").append(count.getAndIncrement());
        } else if (count.get() >= 100&&count.get()<1000) {
            num.append("00").append(count.getAndIncrement());
        } else if (count.get() >= 1000&&count.get()<10000) {
            num.append("0").append(count.getAndIncrement());
        } else {
            num.append(count.getAndIncrement());
        }

        // 组合。
        builder.append(prefix);
        builder.append(num);

        return builder.toString();
    }

    public static  String getNowCode(String typePrefixes,String maxCode){
        String nowCode=null;
        if(maxCode==null){
            nowCode=typePrefixes+"-00001";
        }else{
            nowCode= EncodeUtils.getConteactNo(typePrefixes+"-",Integer.parseInt(maxCode.split("-")[1]));
        }
        return nowCode;
    }

   //测试主函数
    public static void main(String args[]){
        String resultCode=EncodeUtils.getConteactNo("ddd-",99998);
        System.out.print(resultCode);

    }
}
