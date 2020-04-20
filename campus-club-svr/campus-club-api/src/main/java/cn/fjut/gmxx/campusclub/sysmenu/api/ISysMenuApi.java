package cn.fjut.gmxx.campusclub.sysmenu.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysMenuApi
* @类描述 <pre>ISysMenuApi</pre>
* @作者 shenjindui
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
public interface ISysMenuApi {

	/**
	 * 分页查询菜单列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysMenuPage(Map<String, Object> params);

	/**
	 * 查询菜单列表 所有的 无分页
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findSysMenuAll(Map<String, Object> params);

	/**
	 * 根据map条件查询菜单记录
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysMenuMap(Map<String, Object> params);

	/**
	 * 保存/修改菜单 根据uuid是否为空来判断
	 * @param params
	 * @return
	 */
	Map<String, Object> saveSysMenuTrans(Map<String, Object> params);

	/**
	 * 删除菜单
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteSysMenuTrans(Map<String, Object> params);

	/**
	 * 菜单分配给角色
	 * @param params
	 * @return
	 */
	Map<String, Object> SysMenuToRoleTrans(Map<String, Object> params);
}