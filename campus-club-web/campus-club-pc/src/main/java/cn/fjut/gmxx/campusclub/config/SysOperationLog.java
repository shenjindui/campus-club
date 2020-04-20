package cn.fjut.gmxx.campusclub.config;

/**
 * Created by admin on 2020/3/15.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Promise
 * @description  定义一个方法级别的@log注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysOperationLog {
    String value() default "";
}
