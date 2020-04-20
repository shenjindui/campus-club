package cn.fjut.gmxx.campusclub.sysmenu.api.impl;

import cn.fjut.gmxx.campusclub.manager.AbstractCampusClubApi;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.ISysMenuApi;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import cn.fjut.gmxx.campusclub.sysmenu.service.ISysMenuService;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysMenuApi")
public class SysMenuApiImpl extends AbstractCampusClubApi implements ISysMenuApi  {

	@Autowired
	private ISysMenuService sysMenuService;
	@Autowired
	RedisUtils redisUtils;

	@Override
	public PageInfo<Map<String, Object>> findSysMenuPage(Map<String, Object> params) {
		QueryTimeParseUtils.parseQueryTime(params);
		PageInfo<Map<String, Object>> page = sysMenuService.findSysMenuPage(params);
		page.setTotal(sysMenuService.findSysMenuNoPage(params).size());
		return page;
	}

	@Override
	public List<Map<String, Object>> findSysMenuAll(Map<String, Object> params) {
		return sysMenuService.findSysMenuNoPage(params);
	}

	@Override
	public Map<String, Object> getSysMenuMap(Map<String, Object> params) {
	    super.validateNull(params);
		Map<String, Object> sysMenuMap = sysMenuService.getSysMenuMap(params);
		return sysMenuMap;
	}

	@Override
	public Map<String, Object> saveSysMenuTrans(Map<String, Object> params) {
        super.validateNull(params);
        String uuid = MapUtils.getString(params,SysMenuApiConstants.uuid);
		//新增
		if (null == uuid) {
			return sysMenuService.saveSysMenu(params);
		} else {
			//修改
            return sysMenuService.updateSysMenu(params);
		}
	}

	@Override
	public Map<String, Object> deleteSysMenuTrans(Map<String, Object> params) {
		SysMenuEntity sysMenuEntity=sysMenuService.deleteSysMenu(params);
		params.clear();
		params.put("result",sysMenuEntity);
		return params;
	}

	@Override
	public Map<String, Object> SysMenuToRoleTrans(Map<String, Object> params) {
		return sysMenuService.SysMenuToRole(params);
	}

}

