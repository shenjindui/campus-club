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
		Integer id = MapUtils.getInteger(params, SysWorkflowApproverApiConstants.ID);

		//新增
		if (null == id) {
			return sysWorkflowApproverService.saveSysWorkflowApprover(params);
		} else {
			//修改
			sysWorkflowApproverService.updateSysWorkflowApprover(params);
		}
		return null;
	}

	@Override
	public void deleteSysWorkflowApproverTrans(Map<String, Object> params) {
		sysWorkflowApproverService.deleteSysWorkflowApprover(params);
	}

}

