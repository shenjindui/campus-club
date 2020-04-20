package cn.fjut.gmxx.campusclub.utlis;/**
 * Created by admin on 2020/1/13.
 */

import java.security.MessageDigest;

/**
 * @author : shenjindui
 * @date : 2020-01-13 15:00
 **/
public class MD5Utils {
    /***
     * MD5加码 生成32位md5码
     */
    public static String stringToMD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");  //此句是核心
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法[可逆] 执行一次加密，执行两次解密
     */
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    // 测试主函数
    public static void main(String args[]) {
        String s = new String("1");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + stringToMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));

    }
}
