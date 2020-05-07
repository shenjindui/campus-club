package cn.fjut.gmxx.campusclub.sysuserrolerel.api;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysUserRoleRelApi
* @类描述 <pre></pre>
* @作者 shenjindui 2
* @创建时间 2020-01-11
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-11 新建
* ----------------------------------------------
*
*/
public interface ISysUserRoleRelApi {

	/**
	 * 分页查询用户关联角色信息
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
	Map<String, Object> saveSysUserRoleRelTrans(Map<String, Object> params);

	/**
	 * 删除用户关联角色信息
	 * @param params
	 */
	void deleteSysUserRoleRelTrans(Map<String, Object> params);
}