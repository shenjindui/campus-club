package cn.fjut.gmxx.campusclub.sysmenu.api;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysMenuApi
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
public interface ISysMenuApi {

	PageInfo<Map<String, Object>> findSysMenuPage(Map<String, Object> params);

	Map<String, Object> getSysMenuMap(Map<String, Object> params);

	Map<String, Object> saveSysMenuTrans(Map<String, Object> params);

	Map<String, Object> deleteSysMenuTrans(Map<String, Object> params);

	Map<String, Object> SysMenuToRoleTrans(Map<String, Object> params); //菜单分配角色
}