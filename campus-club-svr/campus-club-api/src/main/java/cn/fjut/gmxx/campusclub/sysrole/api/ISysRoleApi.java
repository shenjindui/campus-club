package cn.fjut.gmxx.campusclub.sysrole.api;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

public interface ISysRoleApi {
    /**
     * 查询角色列表 带分页
     * @param params
     * @return
     */
    PageInfo<Map<String, Object>> findSysRolePage(Map<String, Object> params);

    /**
     * 根据条件获取角色详情
     * @param params
     * @return
     */
    Map<String, Object> getSysRoleMap(Map<String, Object> params);

    /**
     * 保存/修改角色信息
     * @param params
     * @return
     */
    Map<String, Object> saveSysRoleTrans(Map<String, Object> params);

    /**
     * 删除角色信息
     * @param params
     * @return
     */
    Map<String, Object> deleteSysRoleTrans(Map<String, Object> params);

    /**
     * 查询角色列表 无分页
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysRoleNoPage(Map<String, Object> params);
}
