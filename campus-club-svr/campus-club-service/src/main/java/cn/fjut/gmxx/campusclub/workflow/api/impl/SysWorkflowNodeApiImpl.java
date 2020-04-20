package cn.fjut.gmxx.campusclub.workflow.api.impl;

import cn.fjut.gmxx.campusclub.baseddct.api.IBaseDdctApi;
import cn.fjut.gmxx.campusclub.baseddct.common.DdctUtils;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysworkflownode.api.ISysWorkflowNodeApi;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowNodeApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowNodeService;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysWorkflowNodeApi")
public class SysWorkflowNodeApiImpl implements ISysWorkflowNodeApi {

	@Autowired
	private ISysWorkflowNodeService sysWorkflowNodeService;
    @Autowired
    private ISysWorkflowService sysWorkflowService;
	@Autowired
    private DdctUtils dctUtils;
    @Autowired
    private IBaseDdctApi baseDdctApi;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowNodePage(Map<String, Object> params) {
		QueryTimeParseUtils.parseQueryTime(params);//处理时间范围的查询
		PageInfo<Map<String, Object>> page = sysWorkflowNodeService.findSysWorkflowNodePage(params);
		List<Map<String,Object>> pageList=page.getList();
		if(pageList!=null&&pageList.size()>1){
            for (Map<String,Object> map:page.getList()) {
                WorkflowEntity entity=sysWorkflowService.findByWorkflowCode(MapUtils.getString(map,"workFlowCode"));
                if(entity!=null){
                    map.put("workFlowName",entity.getWorkFlowName());
                }
				String workFlowNodeTypeName=dctUtils.getValue("workFlowNodeType",MapUtils.getString(map,"workFlowNodeType"));
				map.put("workFlowNodeTypeName",workFlowNodeTypeName);
            }
        }
        page.setTotal(sysWorkflowNodeService.findSysWorkflowNodeAll(params).getTotal());
        return page;
	}

	@Override
	public Map<String, Object> getSysWorkflowNodeMap(Map<String, Object> params) {
		Map<String, Object> sysWorkflowNodeMap = sysWorkflowNodeService.getSysWorkflowNodeMap(params);
        WorkflowEntity entity=sysWorkflowService.findByWorkflowCode(MapUtils.getString(sysWorkflowNodeMap,"workFlowCode"));
        if(entity!=null){
            sysWorkflowNodeMap.put("workFlowName",entity.getWorkFlowName());
        }
		return sysWorkflowNodeMap;
	}

	@Override
	public Map<String, Object> saveSysWorkflowNodeTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowNodeApiConstants.uuid);

		//新增
		if (null == uuid) {
			return sysWorkflowNodeService.saveSysWorkflowNode(params);
		} else {
			//修改
            return sysWorkflowNodeService.updateSysWorkflowNode(params);
		}
	}

    @Override
    public Map<String, Object> saveSysWorkflowNodeInitTrans(Map<String, Object> params) {
        Map<String, Object> resultMap=new HashMap<>();
        Map<String, Object> queryParams=new HashMap<>();
        if(MapUtils.getString(params,"workFlowNodeType")!=null){
            queryParams.put("dctKey",MapUtils.getString(params,"workFlowNodeType"));
            List<Map<String, Object>> result=baseDdctApi.findBaseDdctPage(queryParams).getList();
            resultMap.put("workFlowNodeTypeList",result);
        }
        return resultMap;
    }

    @Override
	public Map<String, Object> deleteSysWorkflowNodeTrans(Map<String, Object> params) {
		sysWorkflowNodeService.deleteSysWorkflowNode(params);
		return params;
	}

}

