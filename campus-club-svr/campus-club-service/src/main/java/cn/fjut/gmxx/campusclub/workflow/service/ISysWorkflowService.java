package cn.fjut.gmxx.campusclub.workflow.service;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;

import java.util.Map;

/**
 * Created by admin on 2020/2/5.
 */
public interface ISysWorkflowService {
    PageInfo<Map<String, Object>> findSysWorkflowPage(Map<String, Object> params);

    Map<String, Object> getSysWorkflowMap(Map<String, Object> params);

    Map<String,Object> saveSysWorkflow(Map<String, Object> params);

    Map<String,Object> updateSysWorkflow(Map<String, Object> params);

    WorkflowEntity deleteSysWorkflow(Map<String, Object> params);

    WorkflowEntity findByWorkflowCode(String workflowCode);
}
