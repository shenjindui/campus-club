package cn.fjut.gmxx.campusclub.sysuserrolerel.api.impl;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuserrolerel.api.ISysUserRoleRelApi;
import cn.fjut.gmxx.campusclub.sysuserrolerel.api.SysUserRoleRelApiConstants;
import cn.fjut.gmxx.campusclub.sysuserrolerel.service.ISysUserRoleRelService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sysUserRoleRelApi")
public class SysUserRoleRelApiImpl implements ISysUserRoleRelApi {

	@Autowired
	private ISysUserRoleRelService sysUserRoleRelService;

	@Override
	public PageInfo<Map<String, Object>> findSysUserRoleRelPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = sysUserRoleRelService.findSysUserRoleRelPage(params);
		return page;
	}

	@Override
	public Map<String, Object> getSysUserRoleRelMap(Map<String, Object> params) {
		Map<String, Object> sysUserRoleRelMap = sysUserRoleRelService.getSysUserRoleRelMap(params);
		return sysUserRoleRelMap;
	}

	@Override
	public Map<String, Object> saveSysUserRoleRelTrans(Map<String, Object> params) {
		String id = MapUtils.getString(params, SysUserRoleRelApiConstants.uuid);
		if (null == id) {
			return sysUserRoleRelService.saveSysUserRoleRel(params);
		} else {
			sysUserRoleRelService.updateSysUserRoleRel(params);
		}
		return null;
	}

	@Override
	public void deleteSysUserRoleRelTrans(Map<String, Object> params) {
		sysUserRoleRelService.deleteSysUserRoleRel(params);
	}
}

