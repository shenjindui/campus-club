package cn.fjut.gmxx.campusclub.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author : shenjindui
 * @date : 2020-01-10 13:42
 **/
public class ExceptionMessageUtils {
    private static Logger logger = LoggerFactory.getLogger(ExceptionMessageUtils.class);
   @Autowired
   private ExcetionMsg excetionMsg;
    public ExceptionMessageUtils() {
    }
    public static String getCodeMessage(String errorCode, String... args) {
        String valueForPlace = errorCode;
        if(errorCode != null && !"".equals(errorCode) && valueForPlace != null && !"".equals(valueForPlace) && args != null && args.length > 0) {
            for(int i = 0; i < args.length; ++i) {
                if(args[i] != null) {
                    valueForPlace = valueForPlace.replaceAll("\\{" + i + "\\}", args[i]);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append("异常信息【");
        sb.append(valueForPlace);
        sb.append("】");
        return sb.toString();
    }
}
