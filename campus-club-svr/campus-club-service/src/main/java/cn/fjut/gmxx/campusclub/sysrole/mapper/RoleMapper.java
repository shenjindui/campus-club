package cn.fjut.gmxx.campusclub.sysrole.mapper;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ushenjindui on 2020/1/7.
 */
@Repository
public interface RoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 分页查询数据
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysRoleList(Map<String, Object> params);

    /**
     * 查询数据 无分页
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysRoleListAll(Map<String, Object> params);

    /**
     * 根据Uuid删除对象
     * @param uuid
     * @return
     */
    int deleteSysRoleByUuid(String uuid);

    /**
     * 根据Uuid修改对象
     * @param uuid
     * @return
     */
    int updateSysRoleByUuid(String uuid);

}
