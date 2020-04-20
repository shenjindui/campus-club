package cn.fjut.gmxx.campusclub.sysmenu.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;


/**
* @类名称 ISysMenuService
* @类描述 <pre>请填写</pre>
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
public interface ISysMenuService {

	/**
	 * 分页查询菜单列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysMenuPage(Map<String, Object> params);
	/**
	 * 条件查询数据
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysMenuMap(Map<String, Object> params);
	/**
	 * 保存菜单
	 * @param params
	 * @return
	 */
	Map<String,Object> saveSysMenu(Map<String, Object> params);
	/**
	 * 修改菜单
	 * @param params
	 * @return
	 */
	Map<String,Object> updateSysMenu(Map<String, Object> params);
	/**
	 * 删除菜单
	 * @param params
	 * @return
	 */
	SysMenuEntity deleteSysMenu(Map<String, Object> params);
	/**
	 * 菜单分配角色
	 * @param params
	 * @return
	 */
	Map<String,Object> SysMenuToRole(Map<String, Object> params);
	/**
	 * 查询总数量
	 * @param params
	 * @return
	 */
	long findSysMenuCount(Map<String, Object> params);
	/**
	 * 查询菜单列表,无分页
	 * @param params
	 * @return
	 */
    List<Map<String, Object>> findSysMenuNoPage(Map<String, Object> params);

}

