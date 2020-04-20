package cn.fjut.gmxx.campusclub.exception;/**
 * Created by admin on 2020/1/10.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : shenjindui
 * @date : 2020-01-10 13:41
 **/
public class ExceptionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionFactory.class);
    public static String exceptionType;
    public static String dubboException = "dubboException";

    public ExceptionFactory() {
    }

    public static RuntimeException getBizException() {
        if(dubboException.equals(exceptionType)) {
            RpcException ex = new RpcException();
            logger.error(ex.toString());
            return ex;
        } else {
            BizException ex = new BizException();
            logger.error(ex.toString());
            return ex;
        }
    }

    public static RuntimeException getBizException(String errorCode, String... args) {
        if(dubboException.equals(exceptionType)) {
            RpcException ex = new RpcException(ExceptionMessageUtils.getCodeMessage(errorCode, args));
            logger.error(ex.toString());
            return ex;
        } else {
            BizException ex = new BizException(errorCode, args);
            logger.error(ex.toString());
            return ex;
        }
    }

    public static RuntimeException getBizException(String errorCode, Throwable cause, String... args) {
        if(dubboException.equals(exceptionType)) {
            RpcException ex = new RpcException(ExceptionMessageUtils.getCodeMessage(errorCode, args), cause);
            logger.error(ex.toString());
            return ex;
        } else {
            BizException ex = new BizException(errorCode, cause, args);
            logger.error(ex.toString());
            return ex;
        }
    }

    public static RuntimeException getSysException() {
        if(dubboException.equals(exceptionType)) {
            RpcException ex = new RpcException();
            logger.error(ex.toString());
            return ex;
        } else {
            SysException ex = new SysException();
            logger.error(ex.toString());
            return ex;
        }
    }

    public static RuntimeException getSysException(String errorCode, String... args) {
        if(dubboException.equals(exceptionType)) {
            RpcException ex = new RpcException(ExceptionMessageUtils.getCodeMessage(errorCode, args));
            logger.error(ex.toString());
            return ex;
        } else {
            SysException ex = new SysException(errorCode, args);
            logger.error(ex.toString());
            return ex;
        }
    }

    public static RuntimeException getSysException(String errorCode, Throwable cause, String... args) {
        if(dubboException.equals(exceptionType)) {
            RpcException ex = new RpcException(ExceptionMessageUtils.getCodeMessage(errorCode, args), cause);
            logger.error(ex.toString());
            return ex;
        } else {
            SysException ex = new SysException(errorCode, cause, args);
            logger.error(ex.toString());
            return ex;
        }
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        exceptionType = exceptionType;
    }
}