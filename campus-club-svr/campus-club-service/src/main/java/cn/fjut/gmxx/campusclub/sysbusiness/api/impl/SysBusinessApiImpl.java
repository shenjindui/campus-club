package cn.fjut.gmxx.campusclub.sysbusiness.api.impl;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.api.ISysBusinessApi;
import cn.fjut.gmxx.campusclub.sysbusiness.api.SysBusinessApiConstants;
import cn.fjut.gmxx.campusclub.sysbusiness.service.ISysBusinessService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sysBusinessApi")
public class SysBusinessApiImpl implements ISysBusinessApi {

	@Autowired
	private ISysBusinessService sysBusinessService;

	@Override
	public PageInfo<Map<String, Object>> findSysBusinessPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = sysBusinessService.findSysBusinessPage(params);
		return page;
	}

	@Override
	public Map<String, Object> getSysBusinessMap(Map<String, Object> params) {
		Map<String, Object> sysBusinessMap = sysBusinessService.getSysBusinessMap(params);
		return sysBusinessMap;
	}

	@Override
	public Map<String, Object> saveSysBusinessTrans(Map<String, Object> params) {
		Integer id = MapUtils.getInteger(params, SysBusinessApiConstants.ID);

		//新增
		if (null == id) {
			return sysBusinessService.saveSysBusiness(params);
		} else {
			//修改
			sysBusinessService.updateSysBusiness(params);
		}
		return null;
	}

	@Override
	public void deleteSysBusinessTrans(Map<String, Object> params) {
		sysBusinessService.deleteSysBusiness(params);
	}

}

