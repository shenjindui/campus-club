package cn.fjut.gmxx.campusclub.sysuserrolerel.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysUserRoleRelService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 2
* @创建时间 2020-01-11
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-11 新建
* ----------------------------------------------
*
*/
public interface ISysUserRoleRelService {

	/**
	 * 分页查询用户关联角色列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysUserRoleRelPage(Map<String, Object> params);

	/**
	 * 根据参数获取用户关联角色信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysUserRoleRelMap(Map<String, Object> params);

	/**
	 * 保存用户关联角色信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveSysUserRoleRel(Map<String, Object> params);

	/**
	 * 更新用户关联角色信息
	 * @param params
	 */
	void updateSysUserRoleRel(Map<String, Object> params);

	/**
	 * 删除用户关联角色信息
	 * @param params
	 */
	void deleteSysUserRoleRel(Map<String, Object> params);
}

