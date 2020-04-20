package cn.fjut.gmxx.campusclub.sysrole.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by shenjindui on 2020/1/11.
 */
public interface ISysRoleService {

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
     * 保存角色信息
     * @param params
     * @return
     */
    Map<String,Object> saveSysRole(Map<String, Object> params);

    /**
     * 修改角色信息
     * @param params
     * @return
     */
    Map<String,Object> updateSysRole(Map<String, Object> params);

    /**
     * 删除角色信息
     * @param params
     * @return
     */
    SysRoleEntity deleteSysRole(Map<String, Object> params);

    /**
     * 查询角色列表 无分页
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysRoleNoPage(Map<String, Object> params);

}
