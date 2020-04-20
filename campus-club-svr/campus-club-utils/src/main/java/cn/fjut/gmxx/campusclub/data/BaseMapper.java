package cn.fjut.gmxx.campusclub.data;/**
 * Created by admin on 2020/3/28.
 */

import org.apache.ibatis.annotations.*;

/**
 * @author : shenjindui
 * @date : 2020-03-28 13:26
 **/
public interface BaseMapper<T> {

    //新增一条数据
    @InsertProvider(method = "add",type=BaseSqlProvider.class)
    @Options(useGeneratedKeys=true)
    public int add(T bean);

    //根据主键删除一条数据
    @DeleteProvider(method = "delete",type=BaseSqlProvider.class)
    public int delete(T bean);

    //根据主键获取一条数据
    @SelectProvider(method = "get",type=BaseSqlProvider.class)
    public T get(T bean);

    //根据主键获取一条数据
    @SelectProvider(method = "get",type=BaseSqlProvider.class)
    public T selectById(T bean);

    //修改一条数据
    @UpdateProvider(method = "update",type=BaseSqlProvider.class)
    public int update(T bean);

}
