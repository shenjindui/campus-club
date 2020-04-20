package cn.fjut.gmxx.campusclub.utlis;

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

/**
 * @author shenjindui
 * @version v1.0
 * @date 2020/02/20
 * @Description
 * 自定义校验工具类
 */
 @Component
public class FieldUtils {

     public static Boolean validateObjectisNotNull(Object vali) {
         boolean result=false;
         if(ObjectUtil.isNotNull(vali)){
             result=true;
             return result;
         }
         return result;
     }
     public static void validateNull(Object vali, String message) {
         if(ObjectUtil.isNotNull(vali)){
             throw ExceptionFactory.getBizException(message);
         }
     }
    /**
     * 字符串非空校验
     * @param filed 要校验的字段
     * @param message message
     */
    public static void stringIsNullCheck(String filed,String message){
        if (StrUtil.isBlank(filed)){
            throw ExceptionFactory.getBizException(message);
        }
    }

    /**
     * 数字校验
     * @param filed 要校验的字段
     * @param message message
     */
    public static void isNumberCheck(String filed,String message){
        if (!NumberUtil.isNumber(filed)){
            throw ExceptionFactory.getBizException(message);
        }
    }

    /**
     * 整数校验
     * @param filed 要校验的字段
     * @param message message
     */
    public static void isIntCheck(String filed,String message){
        if (!NumberUtil.isInteger(filed)){
            throw ExceptionFactory.getBizException(message);
        }
    }

    /**
     * 邮件校验
     * @param filed 要校验的字段
     * @param message message
     */
    public static void isEmailCheck(String filed,String message){
        if (!Validator.isEmail(filed)){
            throw ExceptionFactory.getBizException(message);
        }
    }

    /**
     * 手机号校验
     * @param filed 要校验的字段
     * @param message message
     */
    public static void isMobileCheck(String filed,String message){
        if (!Validator.isMobile(filed)){
            throw ExceptionFactory.getBizException(message);
        }
    }
}