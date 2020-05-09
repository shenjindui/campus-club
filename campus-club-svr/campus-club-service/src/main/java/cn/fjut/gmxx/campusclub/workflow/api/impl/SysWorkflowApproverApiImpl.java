package cn.fjut.gmxx.campusclub.workflow.api.impl;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.api.ISysWorkflowApproverApi;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApproverApiConstants;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowApproverService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sysWorkflowApproverApi")
public class SysWorkflowApproverApiImpl implements ISysWorkflowApproverApi {

	@Autowired
	private ISysWorkflowApproverService sysWorkflowApproverService;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowApproverPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = sysWorkflowApproverService.findSysWorkflowApproverPage(params);
		return page;
	}

	@Override
	public Map<String, Object> getSysWorkflowApproverMap(Map<String, Object> params) {
		Map<String, Object> sysWorkflowApproverMap = sysWorkflowApproverService.getSysWorkflowApproverMap(params);
		return sysWorkflowApproverMap;
	}

	@Override
	public Map<String, Object> saveSysWorkflowApproverTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowApproverApiConstants.uuid);
		if (null == uuid) {
			return sysWorkflowApproverService.saveSysWorkflowApprover(params);
		} else {
			sysWorkflowApproverService.updateSysWorkflowApprover(params);
		}
		return null;
	}

	@Override
	public void deleteSysWorkflowApproverTrans(Map<String, Object> params) {
		sysWorkflowApproverService.deleteSysWorkflowApprover(params);
	}
}

