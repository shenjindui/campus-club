package cn.fjut.gmxx.campusclub.common;/**
 * Created by admin on 2020/3/27.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : shenjindui
 * @date : 2020-03-27 13:36
 * 分别用于字义主键的排除
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
    String value() default "";

}
