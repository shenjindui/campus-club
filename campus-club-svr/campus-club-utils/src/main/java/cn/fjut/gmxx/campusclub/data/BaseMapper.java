package cn.fjut.gmxx.campusclub.data;
import org.apache.ibatis.annotations.*;

/**
 * @author : shenjindui
 * @date : 2020-03-28 13:26
 **/
public interface BaseMapper<T> {

    /**
     * 新增一条数据
     * @param bean
     * @return
     */
    @InsertProvider(method = "add",type=BaseSqlProvider.class)
    @Options(useGeneratedKeys=true)
    public int add(T bean);

    /**
     *  根据主键删除一条数据
     * @param bean
     * @return
     */
    @DeleteProvider(method = "delete",type=BaseSqlProvider.class)
    public int delete(T bean);

    /**
     * 根据主键获取一条数据
     * @param bean
     * @return
     */
    @SelectProvider(method = "get",type=BaseSqlProvider.class)
    public T get(T bean);

    /**
     * 根据主键获取一条数据
     * @param bean
     * @return
     */
    @SelectProvider(method = "get",type=BaseSqlProvider.class)
    public T selectById(T bean);

    /**
     * 修改一条数据
     * @param bean
     * @return
     */
    @UpdateProvider(method = "update",type=BaseSqlProvider.class)
    public int update(T bean);
}
