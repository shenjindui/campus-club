package cn.fjut.gmxx.campusclub.common;/**
 * Created by admin on 2019/12/30.
 */

/**
 * @author : shenjindui
 * @date : 2019-12-30 20:37
 **/

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestObjectParam {
}