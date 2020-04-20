package cn.fjut.gmxx.campusclub.utlis;
import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : shenjindui
 * @date : 2020-01-26 20:01
 **/
public class ObjectUtils {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    public static void GenerateNullValuePojo(Class clazz){
        Object obj;
        try {
            obj =  clazz.newInstance();
        }
        catch (Exception e) {
            // 需要在类上添加lombok的@Slf4j注解
            logger.error(String.format("反射生成实体失败, error=%s", e.getMessage()), e);
              throw new RuntimeException("反射生成实体失败");
        }     // 获取实体的所有属性(自身+所有父类)
         List<Field> fieldList = new ArrayList<>();
        do {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class); // 当父类为object时, 不再获取属性
        for (Field field : fieldList) {
            // 私有属性可访问
              field.setAccessible(true);
              try {            // 不对final字段修改
                    if (!Modifier.isFinal(field.getModifiers())) {
                        // 字段值设置为null,需要所有属性为对象类型如integer
                               field.set(obj, null);
                    }
              } catch (IllegalAccessException e) {
                  logger.error("设置属性值为null失败, filedName={}", field.getName());
              }
        }
       // return obj;
    }
}
