package cn.fjut.gmxx.campusclub.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenjindui
 * @description  定义一个方法级别的@SysOperationLog注解
 *  @date : 2020-03-15 10:43
 * 系统操作日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysOperationLog {
    String value() default "";
}
