package cn.fjut.gmxx.campusclub.utlis;/**
 * Created by admin on 2020/3/22.
 */

/**
 * @author : shenjindui
 * @date : 2020-03-22 14:59
 **/

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

public class BeanUtils {

    /**
     * 方法说明：将List<Map>转换为List<Bean>
     *
     * @param mapList
     * @param cls
     * @return
     * @throws Exception
     */
    public static List<Object> mapListToBeanList(
            List<Map<String, Object>> mapList, Class<?> cls) throws Exception {
        if (mapList == null || mapList.size() == 0) {
            return null;
        }
        List<Object> beanList = new ArrayList<Object>();
        Object bean = null;
        for (Map<String, Object> map : mapList) {
            bean = mapToBean(map, cls);
            if (bean == null) {
                continue;
            }
            beanList.add(bean);
        }
        return beanList;
    }

    /**
     * 设置bean 属性值,没有下划线的
     *
     * @param map
     * @param cls
     * @return
     * @throws Exception
     */
    public static Object mapToBeanNL(Map<String, Object> map, Class<?> cls) throws Exception {
        if (map == null || map.size() == 0) {
            return null;
        }
        Object obj = cls.newInstance();
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            // 判断字段是否存在
            String fieldName = key;
            Field field = getBeanField(cls, fieldName);
            if (field == null) {
                continue;
            }
            // 判断字段的set方法是否存在
            String setMethodName = StringUtils.pareSetName(fieldName);
            Method method = getBeanMethod(cls, setMethodName, field.getType());
            if (method == null) {
                continue;
            }

            String fieldType = field.getType().getSimpleName();
            if ("String".equals(fieldType)) {
                method.invoke(obj, value.toString());
            } else if ("long".equals(fieldType) || "Long".equals(fieldType)) {
                method.invoke(obj, Long.valueOf(value.toString()));
            } else if ("int".equals(fieldType) || "Integer".equals(fieldType)) {
                method.invoke(obj, value.toString());
            } else if ("double".equals(fieldType) || "Double".equals(fieldType)) {
                method.invoke(obj, Double.valueOf(value.toString()));
            } else if ("float".equals(fieldType) || "Float".equals(fieldType)) {
                method.invoke(obj, Float.valueOf(value.toString()));
            } else if ("boolean".equals(fieldType) || "Boolean".equals(fieldType)) {
                if (value.getClass().equals(Boolean.class)) {
                    method.invoke(obj, (Boolean) value);
                } else {
                    method.invoke(obj, Boolean.valueOf(value.toString()));
                }
            } else if ("Date".equals(fieldType)) {
                if (value != null) {
                    if (value.getClass().equals(Date.class)) {
                        method.invoke(obj, (Date) value);
                    } else {
                        method.invoke(obj, DateUtils.strToDate(value.toString()));
                    }
                }
            }
        }
        return obj;
    }

    /**
     * 设置bean 属性值
     *
     * @param map
     * @param cls
     * @return
     * @throws Exception
     */
    public static Object mapToBean(Map<String, Object> map, Class<?> cls)
            throws Exception {
        if (map == null || map.size() == 0) {
            return null;
        }
        Object obj = cls.newInstance();
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            // 判断字段是否存在
            String fieldName = StringUtils.toUnderLine(key.toLowerCase());
            Field field = getBeanField(cls, fieldName);
            if (field == null) {
                continue;
            }
            // 判断字段的set方法是否存在
            String setMethodName = StringUtils.pareSetName(fieldName);
            Method method = getBeanMethod(cls, setMethodName, field.getType());
            if (method == null) {
                continue;
            }

            String fieldType = field.getType().getSimpleName();
            if ("String".equals(fieldType)) {
                method.invoke(obj, value.toString());
            } else if ("long".equals(fieldType) || "Long".equals(fieldType)) {
                method.invoke(obj, Long.valueOf(value.toString()));
            } else if ("int".equals(fieldType) || "Integer".equals(fieldType)) {
                method.invoke(obj, value.toString());
            } else if ("double".equals(fieldType) || "Double".equals(fieldType)) {
                method.invoke(obj, Double.valueOf(value.toString()));
            } else if ("float".equals(fieldType) || "Float".equals(fieldType)) {
                method.invoke(obj, Float.valueOf(value.toString()));
            } else if ("boolean".equals(fieldType)
                    || "Boolean".equals(fieldType)) {
                if (value.getClass().equals(Boolean.class)) {
                    method.invoke(obj, (Boolean) value);
                } else {
                    method.invoke(obj, Boolean.valueOf(value.toString()));
                }
            } else if ("Date".equals(fieldType)) {
                if (value != null) {
                    if (value.getClass().equals(Date.class)) {
                        method.invoke(obj, (Date) value);
                    } else {
                        method.invoke(obj, DateUtils
                                .strToDate(value.toString()));
                    }
                }
            }
        }
        return obj;
    }

    /**
     * 方法说明：将List<Bean>转换为List<Map>
     *
     * @param beanList
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> beanListToMapList(
            List<Map<String, Object>> beanList) throws Exception {
        if (beanList == null || beanList.size() == 0) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (Object bean : beanList) {
            map = beanToMap(bean);
            if (map == null || map.size() == 0) {
                continue;
            }
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 设置bean 属性值
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object bean) throws Exception {
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> cls = bean.getClass();
        Field fields[] = cls.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            boolean isBooleanType = false;
            if (fieldType.equals("boolean") || fieldType.equals("Boolean")) {
                isBooleanType = true;
            }
            String getMethodName = StringUtils.pareGetName(fieldName,
                    isBooleanType);
            // 判断字段的无参get方法是否存在
            Method method = getBeanMethod(cls, getMethodName, new Class[]{});
            if (method == null) {
                continue;
            }
            Object fieldValue = method.invoke(bean, new Object[]{});
            map.put(StringUtils.toUnderLine(field.getName()).toUpperCase(),
                    fieldValue);
        }
        return map;
    }

    /**
     * 判断该方法是否存在
     *
     * @param methods
     * @param met
     * @return
     */
    public static boolean checkMethod(Method methods[], String met) {
        if (null != methods) {
            for (Method method : methods) {
                if (met.equals(method.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法说明：获取bean的指定方法
     * <p>
     * <p>
     * Author： zhenqiangs Create Date： 2016-4-30 下午01:07:12 History: 2016-4-30
     * 下午01:07:12 zhenqiangs Created.
     *
     * @param cls
     * @param methodName
     * @param paramTypes
     * @return
     */
    private static Method getBeanMethod(Class<?> cls, String methodName,
                                        Class<?>... paramTypes) {
        if (cls == null) {
            return null;
        }
        Method setMethod = null;
        try {
            setMethod = cls.getMethod(methodName, paramTypes);
        } catch (Exception e) {

        }
        return setMethod;
    }

    /**
     * 方法说明：获取bean的指定属性
     *
     * @param cls
     * @param fieldName
     * @return
     */
    public static Field getBeanField(Class<?> cls, String fieldName) {
        if (cls == null) {
            return null;
        }
        Field field = null;
        try {
            field = cls.getDeclaredField(fieldName);
        } catch (Exception e) {

        }
        return field;
    }

    /**
     * 设置对应值
     *
     * @param fieldName
     */
    public static void setBeanField(Class<?> cls, Object obj, String fieldName, Object value) throws Exception {

        // 判断字段是否存在
        Field field = getBeanField(cls, fieldName);
        if (field == null) {
            return;
        }
        // 判断字段的set方法是否存在
        String setMethodName = StringUtils.pareSetName(fieldName);
        Method method = getBeanMethod(cls, setMethodName, field.getType());
        if (method == null) {
            return;
        }
        //为空不设置
        if (value == null) {
            return;
        }
        String fieldType = field.getType().getSimpleName();
        if ("String".equals(fieldType)) {
            method.invoke(obj, value.toString());
        } else if ("long".equals(fieldType) || "Long".equals(fieldType)) {
            method.invoke(obj, Long.valueOf(value.toString()));
        } else if ("int".equals(fieldType) || "Integer".equals(fieldType)) {
            method.invoke(obj, value.toString());
        } else if ("double".equals(fieldType) || "Double".equals(fieldType)) {
            method.invoke(obj, Double.valueOf(value.toString()));
        } else if ("float".equals(fieldType) || "Float".equals(fieldType)) {
            method.invoke(obj, Float.valueOf(value.toString()));
        } else if ("boolean".equals(fieldType) || "Boolean".equals(fieldType)) {
            if (value.getClass().equals(Boolean.class)) {
                method.invoke(obj, (Boolean) value);
            } else {
                method.invoke(obj, Boolean.valueOf(value.toString()));
            }
        } else if ("Date".equals(fieldType)) {
            if (value.getClass().equals(Date.class)) {
                method.invoke(obj, (Date) value);
            } else {
                method.invoke(obj, DateUtils.strToDate(value.toString()));
            }
        }
    }

    /**
     * 设置对应值
     *
     * @param fieldName
     */
    public static Object getBeanFieldValue(Class<?> cls, Object obj, String fieldName) throws Exception {

        // 判断字段是否存在
        Field field = getBeanField(cls, fieldName);
        // 判断字段的set方法是否存在
        String getMethodName = StringUtils.pareGetName(fieldName, false);
        // 判断字段的无参get方法是否存在
        Method method = getBeanMethod(cls, getMethodName, new Class[]{});
        Object fieldValue = method.invoke(obj, new Object[]{});
        return fieldValue;
    }

}
