package cn.fjut.gmxx.campusclub.workflow.service;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;

import java.util.Map;

public interface ISysWorkflowService {
    /**
     * 分页查询工作流列表
     * @param params
     * @return
     */
    PageInfo<Map<String, Object>> findSysWorkflowPage(Map<String, Object> params);

    /**
     * 根据参数获取工作流信息
     * @param params
     * @return
     */
    Map<String, Object> getSysWorkflowMap(Map<String, Object> params);

    /**
     * 保存工作流信息
     * @param params
     * @return
     */
    Map<String,Object> saveSysWorkflow(Map<String, Object> params);

    /**
     * 更新工作流信息
     * @param params
     * @return
     */
    Map<String,Object> updateSysWorkflow(Map<String, Object> params);

    /**
     * 删除工作流信息
     * @param params
     * @return
     */
    WorkflowEntity deleteSysWorkflow(Map<String, Object> params);

    /**
     * 根据workflowCode获取工作流信息
     * @param workflowCode
     * @return
     */
    WorkflowEntity findByWorkflowCode(String workflowCode);
}
