package cn.fjut.gmxx.campusclub.workflow.api.impl;

import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.DateUtils;
import cn.fjut.gmxx.campusclub.workflow.api.ISysWorkflowApi;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysWorkflowApi")
public class SysWorkflowApiImpl implements ISysWorkflowApi {

	@Autowired
	private ISysWorkflowService sysWorkflowService;

	@Autowired
	private ExcetionMsg excetionMsg;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowPage(Map<String, Object> params) {
		if(params!=null && MapUtils.getString(params,"paramsTime")!=null){ //如果时间控件的值不为空
			List<String> paramsTimeList=(List<String>)params.get("paramsTime");
			String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
			String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
			params.put("startsTime",startTime);
			params.put("endsTime",endTime);
		}
		PageInfo<Map<String, Object>> page = sysWorkflowService.findSysWorkflowPage(params);
		return page;
	}

	@Override
	public Map<String, Object> getSysWorkflowMap(Map<String, Object> params) {
		Map<String, Object> sysWorkflowMap = sysWorkflowService.getSysWorkflowMap(params);
		return sysWorkflowMap;
	}

	@Override
	public Map<String, Object> saveSysWorkflowTrans(Map<String, Object> params) {
		String id = MapUtils.getString(params, SysWorkflowApiConstants.uuid);
		if (null == id) {
			return sysWorkflowService.saveSysWorkflow(params);
		} else {
            return sysWorkflowService.updateSysWorkflow(params);
		}
	}

	@Override
	public Map<String, Object> deleteSysWorkflowTrans(Map<String, Object> params) {
        WorkflowEntity workflowEntity=sysWorkflowService.deleteSysWorkflow(params);
        params.clear();
        params.put("result",workflowEntity);
        return params;
	}
}

