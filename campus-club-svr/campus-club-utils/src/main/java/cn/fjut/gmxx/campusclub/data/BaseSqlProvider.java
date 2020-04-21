package cn.fjut.gmxx.campusclub.data;
import cn.fjut.gmxx.campusclub.common.Exclude;
import cn.fjut.gmxx.campusclub.common.PrimaryKey;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.utlis.NamingUtils;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : shenjindui
 * @date : 2020-03-27 13:37
 **/
public class BaseSqlProvider<T> {
    @Options
    public String add(T bean) {
        SQL sql = new SQL();
        Class clazz = bean.getClass();
        String tableName = clazz.getSimpleName();
        String realTableName = NamingUtils.humpToLine(tableName).replaceAll("_entity", "").substring(1);
        sql.INSERT_INTO(realTableName);
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);
            String column = field.getName();
            sql.VALUES(NamingUtils.humpToLine(column), String.format("#{" + column + ",jdbcType=VARCHAR}"));
        }
        return sql.toString();
    }

    public String delete(T bean) {
        SQL sql = new SQL();
        Class clazz = bean.getClass();
        String tableName = clazz.getSimpleName();
        String realTableName = NamingUtils.humpToLine(tableName).replaceAll("_entity", "").substring(1);
        sql.DELETE_FROM(realTableName);
        List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
        if (!primaryKeyField.isEmpty()) {
            for (Field pkField : primaryKeyField) {
                pkField.setAccessible(true);
                sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
            }
        } else {
            sql.WHERE(" 1= 2");
            throw ExceptionFactory.getBizException("对象中未包含PrimaryKey属性");
        }
        return sql.toString();
    }

    private List<Field> getPrimarkKeyFields(Class clazz) {
        List<Field> primaryKeyField = new ArrayList<>();
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);
            PrimaryKey key = field.getAnnotation(PrimaryKey.class);
            if (key != null) {
                primaryKeyField.add(field);
            }
        }
        return primaryKeyField;
    }

    private List<Field> getFields(Class clazz) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Exclude key = field.getAnnotation(Exclude.class);
            if (key == null) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    public String get(T bean) {
        SQL sql = new SQL();
        Class clazz = bean.getClass();
        String tableName = clazz.getSimpleName();
        String realTableName = NamingUtils.humpToLine(tableName).replaceAll("_entity", "").substring(1);
        sql.SELECT("*").FROM(realTableName);
        List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
        if (!primaryKeyField.isEmpty()) {
            for (Field pkField : primaryKeyField) {
                pkField.setAccessible(true);
                sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
            }
        } else {
            sql.WHERE(" 1= 2");
            throw ExceptionFactory.getBizException("对象中未包含PrimaryKey属性");
        }
       System.out.println("getSql:"+sql.toString());
        return sql.toString();
    }

    public String update(T bean) {
        SQL sql = new SQL();
        Class clazz = bean.getClass();
        String tableName = clazz.getSimpleName();
        String realTableName = NamingUtils.humpToLine(tableName).replaceAll("_entity", "").substring(1);
        sql.UPDATE(realTableName);
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);
            String column = field.getName();
            if (column.equals("id")) {
                continue;
            }
            //System.out.println(NamingUtils.humpToLine(column));
            sql.SET(NamingUtils.humpToLine(column) + "=" + String.format("#{" + column + ",jdbcType=VARCHAR}"));
        }

        List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
        if (!primaryKeyField.isEmpty()) {
            for (Field pkField : primaryKeyField) {
                pkField.setAccessible(true);
                sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
            }
        } else {
            sql.WHERE(" 1= 2");
            throw ExceptionFactory.getBizException("对象中未包含PrimaryKey属性");
        }
        return sql.toString();
    }

}
