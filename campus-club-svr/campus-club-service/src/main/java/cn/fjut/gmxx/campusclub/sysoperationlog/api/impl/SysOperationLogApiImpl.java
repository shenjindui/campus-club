package cn.fjut.gmxx.campusclub.sysoperationlog.api.impl;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysoperationlog.api.ISysOperationLogApi;
import cn.fjut.gmxx.campusclub.sysoperationlog.api.SysOperationLogApiConstants;
import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;
import cn.fjut.gmxx.campusclub.sysoperationlog.service.ISysOperationLogService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOperationLogApi")
public class SysOperationLogApiImpl implements ISysOperationLogApi {

	@Autowired
	private ISysOperationLogService sysOperationLogService;

	@Override
	public PageInfo<Map<String, Object>> findSysOperationLogPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = sysOperationLogService.findSysOperationLogPage(params);
		return page;
	}

	@Override
	public Map<String, Object> getSysOperationLogMap(Map<String, Object> params) {
		Map<String, Object> sysOperationLogMap = sysOperationLogService.getSysOperationLogMap(params);
		return sysOperationLogMap;
	}

	@Override
	public Map<String, Object> saveSysOperationLogTrans(Map<String, Object> params) {
		String id = MapUtils.getString(params, SysOperationLogApiConstants.UUID);
		//新增
		if (null == id) {
			return sysOperationLogService.saveSysOperationLog(params);
		} else {
			//修改
			sysOperationLogService.updateSysOperationLog(params);
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteOperationLogTrans(Map<String, Object> params) {
		SysOperationLogEntity sysOperationLogEntity=sysOperationLogService.deleteSysOperation(params);
		params.clear();
		params.put("result",sysOperationLogEntity);
		return params;
	}

	@Override
	public void deleteSysOperationLogTrans(Map<String, Object> params) {
		sysOperationLogService.deleteSysOperationLog(params);
	}

}
