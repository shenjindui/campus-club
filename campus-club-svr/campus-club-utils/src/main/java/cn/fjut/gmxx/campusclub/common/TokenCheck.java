package cn.fjut.gmxx.campusclub.common;

import java.lang.annotation.*;

/**
 * Created by admin on 2020/1/14.
 * token 验证注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
    public @interface TokenCheck {
}