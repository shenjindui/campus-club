package cn.fjut.gmxx.campusclub.utlis;
/**
 * @author : shenjindui
 * @date : 2020-03-22 15:00
 **/
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luojie
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 合并字符串数组为一个串
     *
     * @param src       String[] 字符串数组
     * @param delimiter 隔开字符
     * @return String
     */
    public static String merge(String[] src, String delimiter) {
        StringBuffer newSrc = new StringBuffer();
        for (int i = 0; i < src.length; i++) {
            if (i < src.length - 1) {
                newSrc.append(src[i]).append(delimiter);
            } else {
                newSrc.append(src[i]);
            }
        }
        return newSrc.toString();
    }

    /**
     * 分解字符串
     *
     * @param str  String
     * @param sect int 分解的段数
     * @param len  int 每段的字符长度
     * @return String[]
     * @throws Exception
     */
    static public String[] split(String str, int sect, int len)
            throws Exception {
        String[] result = new String[sect];
        int j = 0;
        for (j = 0; j < sect; j++) {
            result[j] = "";
        }
        for (j = 0; j < sect; j++) {
            if (str.length() < len * j) {
                break;
            } else if (str.length() < len * (j + 1)) {
                result[j] = str.substring(len * j, str.length());
            } else {
                result[j] = str.substring(len * j, len * (j + 1));
            }
        }
        return result;
    }

    /**
     * 对整个url进行编码转换
     *
     * @param srcStr   url串
     * @param encoding 编码
     * @return String
     */
    public static String URLEncode(String srcStr, String encoding) {
        String[] arrayUrl = srcStr.split("\\?");
        if (arrayUrl.length <= 1) {
            return srcStr;
        }
        String qryStr = arrayUrl[1];
        String[] arrayQryStr = qryStr.split("&");
        StringBuffer newQryStr = new StringBuffer(120);
        StringBuffer tmp = new StringBuffer(20);
        String param;
        for (int i = 0; i < arrayQryStr.length; i++) {
            param = arrayQryStr[i];
            String[] arrayParam = param.split("=");
            if (arrayParam.length > 1) {
                try {
                    arrayParam[1] = URLEncoder.encode(arrayParam[1], encoding);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tmp.append(arrayParam[0]).append("=").append(arrayParam[1]);
                arrayQryStr[i] = tmp.toString();
            } else {
                tmp.append(arrayParam[0]).append("=");
                arrayQryStr[i] = tmp.toString();
            }
            newQryStr.append(arrayQryStr[i]).append("&");
        }
        tmp = new StringBuffer(150);
        tmp.append(arrayUrl[0]).append("?").append(newQryStr.toString());
        return tmp.toString();
    }

    /**
     * 用 newString 替换 line 中的所有的 OldString。 不支持正则表达式
     *
     * @param line      原字符串
     * @param oldString 被替换的字符串
     * @param newString 新的要替换oldString的字符串
     * @return 返回所有oldString都被newString替换的字符串
     */
    public static final String replace(String line, String oldString,
                                       String newString) {
        // 如果line是null，直接返回
        if (line == null) {
            return null;
        }
        int i = 0;
        // 如果在line中确实存在oldString那么将进行以下的替换
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        // 如果在line中没有oldString 返回line
        return line;
    }

    /**
     * 用 newString 替换 line 中的所有的 OldString count返回被替换的数目
     *
     * @param line      原字符串
     * @param oldString 被替换的字符串
     * @param newString 新的要替换oldString的字符串
     * @return 返回所有oldString都被newString替换的字符串
     */
    public static final String replace(String line, String oldString,
                                       String newString, int[] count) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            int counter = 0;
            counter++;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                counter++;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * 做不区分大小写的模式匹配，并用newString 来替换 oldString
     *
     * @param line      原字符串
     * @param oldString 被替换的字符串
     * @param newString 新的要替换oldString的字符串
     * @return 返回所有oldString都被newString替换的字符串
     */
    public static final String replaceIgnoreCase(String line, String oldString,
                                                 String newString) {
        // 如果line是null，直接返回
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * 从数组中得到字符串的位置
     *
     * @param s    要查询的字符串
     * @param args 待查的数组
     * @return s的位置，没有找到就是-1
     */
    public static int getStrIndex(String s, String args[]) {
        int length = args.length;
        for (int i = 0; i < length; i++) {
            if (args[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断 如果输入的字符为null或者'null',输出空字符串""
     *
     * @param src
     * @return
     */
    public static String nullToEmpty(String src) {
        if (src == null || src.equalsIgnoreCase("NULL")) {

            return "";
        }
        return src;
    }

    /**
     * 将下划线连接的String替换为驼峰风格
     *
     * @param s
     * @return
     */
    public static String toCamelCasing(String s) {
        if (s == null) {
            return s;
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (ch != '_') {
                buffer.append(ch);
            } else {
                char nextChar = s.charAt(i + 1);
                if (nextChar != '_') {
//             if (buffer.toString().length() < 2) {
//                buffer.append(Character.toLowerCase(nextChar));
//             } else {
                    buffer.append(Character.toUpperCase(nextChar));
//             }
                    i++;
                }
            }
        }
        char lastChar = s.charAt(s.length() - 1);
        if (lastChar != '_') {
            buffer.append(lastChar);
        }

        return buffer.toString();
    }

    /**
     * 将驼峰结构的字符串转换为下划线连接的形式
     *
     * @param s
     * @return
     */
    public static String toUnderLine(String s) {
        if (s == null) {
            return s;
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length() - 1; i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            char nextChar = s.charAt(i + 1);
            if (Character.isUpperCase(nextChar)) {
                buffer.append(ch).append('_');
            } else {
                buffer.append(ch);
            }
        }
        char lastChar = s.charAt(s.length() - 1);
        buffer.append(Character.toLowerCase(lastChar));

        return buffer.toString();
    }

    /**
     * 将首字母小写
     *
     * @param s
     * @return
     */
    public static String lowerFirstChar(String s) {
        if (s == null || s.trim().equals("")) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(Character.toLowerCase(s.charAt(0)))
                .append(s.substring(1));
        return buffer.toString();
    }

    /**
     * 将首字母大写
     *
     * @param s
     * @return
     */
    public static String upperFirstChar(String s) {
        if (s == null || s.trim().equals("")) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(Character.toUpperCase(s.charAt(0)))
                .append(s.substring(1));
        return buffer.toString();
    }

    /**
     * 拼接某属性get 方法
     *
     * @param isBooleanType 属性是否为boolean型
     * @return
     */
    public static String pareGetName(String fieldName, Boolean isBooleanType) {
        String str = StringUtils.upperFirstChar(fieldName);
        if (str == null) {
            return null;
        }
        if (isBooleanType) {
            return "is" + str;
        } else {
            return "get" + str;
        }
    }

    /**
     * 拼接某属性set 方法
     *
     * @return
     */
    public static String pareSetName(String fieldName) {
        String str = StringUtils.upperFirstChar(fieldName);
        if (str == null) {
            return null;
        }
        String pro = "set" + str;
        return pro;
    }

    /**
     * 方法说明：判断字符串是否为空
     *
     * @param paramString
     * @return
     */
    public static boolean nullOrBlank(String paramString) {
        return (paramString == null) || (paramString.trim().equals(""));
    }

    /**
     * 方法说明：判断字符串是否为空
     * List
     *
     * @return
     */
    public static boolean notNullOrBlankList(List list) {

        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 方法说明：字符串为空时给默认值
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static String strNvl(String str, String defaultValue) {
        if (nullOrBlank(str)) {
            return defaultValue;
        }
        return str;
    }

    /**
     * 方法说明：字符串为空时给默认值
     *
     * @param strObj
     * @param defaultValue
     * @return
     */
    public static String strObjNvl(Object strObj, String defaultValue) {
        String str = null;
        if (strObj != null) {
            str = strObj.toString();
        }
        return strNvl(str, defaultValue);
    }

    /**
     * 方法说明：获取第N个a字符后面的所有字符
     * <p>
     * <p>
     * Author： 高宝阳 Create Date： May 5, 2016 6:04:10 PM History: May 5, 2016
     * 6:04:10 PM Administrator Created.
     *
     * @param sChar
     * @param n
     * @return
     */
    public static String getNCharAfter(String sInput, String sChar, int n) {
        // 这里是获取sChar符号的位置
        String str = null;
        int m = 1;
        int i = 0;
        while (sInput.indexOf(sChar, i) >= 0) {
            i = sInput.indexOf(sChar, i) + 1;
            if (n == m) {
                str = sInput.substring(i, sInput.length());
                break;
            }
            m = m + 1;
        }
        return str;
    }

    /**
     * 根据传入的字符串判断将来的数组长度，用于系统参数的解析
     *
     * @param sInput 字符串
     * @return
     * @throws Exception
     */
    public static int getArrLen(String sInput) throws Exception {
        int len = 0;
        int i = 0;
        while (sInput.indexOf("$", i) > 0) {
            i = sInput.indexOf("$", i) + 1;
            len = len + 1;
        }
        return len;
    }

    /**
     * 字符串解释成数组
     *
     * @param sInput 字符串格式：例如：w$m$n$o$p$ w: 位数；m、n、o、p分别为字符串；如实际的例子：4$122$+$01$1$
     * @return
     * @throws Exception
     */
    public static String[] stringParse(String sInput) throws Exception {
        String[] sRet = null;
        try {
            int len = getArrLen(sInput);
            int m = -1;
            sRet = new String[len];
            for (int j = 0; j < len; j++) { // 数组
                sRet[j] = sInput.substring(m + 1, sInput.indexOf("$", m + 1));
                m = sInput.indexOf("$", m + 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return sRet;
    }

    /**
     * replaceBlank
     *
     * @param in
     * @return
     * @description replaceBlank
     */
    public static String replaceBlank(String in) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(in);
        String after = m.replaceAll("");
        return after;
    }

    /**
     * 删除起始字符
     *
     * @param str
     * @return
     * @author xxj 2017年4月27日
     */
    public static String trimStart(String str, String trim) {
        if (str == null) {

            return null;
        }
        return str.replaceAll("^(" + trim + ")+", "");
    }

    /**
     * 删除末尾字符
     *
     * @param str
     * @return
     * @author xxj 2017年4月27日
     */
    public static String trimEnd(String str, String trim) {
        if (str == null)
            return null;
        return str.replaceAll("(" + trim + ")+$", "");


    }

    public static Object nullToDefault(Object obj, Object def) {
        if (obj == null) {
            return def;
        }
        return obj;
    }

    private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
    private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
    private static final double MAX_VALUE = 9999999999999.99D;

    public static String changeMoney(double v) {
        if (v < 0 || v > MAX_VALUE) {
            return "参数非法!";
        }
        long l = Math.round(v * 100);
        if (l == 0) {
            return "零元整";
        }
        String strValue = l + "";
        // i用来控制数
        int i = 0;
        // j用来控制单位
        int j = UNIT.length() - strValue.length();
        String rs = "";
        boolean isZero = false;
        for (; i < strValue.length(); i++, j++) {
            char ch = strValue.charAt(i);
            if (ch == '0') {
                isZero = true;
                if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {
                    rs = rs + UNIT.charAt(j);
                    isZero = false;
                }
            } else {
                if (isZero) {
                    rs = rs + "零";
                    isZero = false;
                }
                rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
            }
        }
        if (!rs.endsWith("分")) {
            rs = rs + "整";
        }
        rs = rs.replaceAll("亿万", "亿");
        return rs;
    }

    public static boolean isBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotBlank(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static String isGoodPat(String patNo) {
        patNo = patNo.replaceFirst("[a-zA-Z]*", "").replace(".", "").replace(" ", "");
        if (!patNo.substring(0, patNo.length() - 1).matches("[0-9]*$") || (patNo.length() != 13 && patNo.length() != 7)) {
            return null;
        }
        return patNo;
    }

    /**
     * 更具专利号判断专利类型
     *  一定要是中国13位专利
     * @param patNo
     * @return
     */
    public static String getPatType(String patNo) {

        if (StringUtils.isEmpty(patNo) || patNo.length() <= 5)
            return null;

        String patType = patNo.substring(4, 5);

        if ("8".equals(patType))
            patType = "1";

        if ("9".equals(patType))
            patType = "2";


        return patType;

    }

    public static void main(String args[]) {
        System.out.print(isGoodPat("20171141704x"));
    }
}
