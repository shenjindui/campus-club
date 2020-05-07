package cn.fjut.gmxx.campusclub.workflow.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysWorkflowApproverService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-31
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-31 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowApproverService {

	/**
	 * 分页查询工作流审批人列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysWorkflowApproverPage(Map<String, Object> params);

	/**
	 * 根据参数获取工作流审批人信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysWorkflowApproverMap(Map<String, Object> params);

	/**
	 * 保存工作流审批人信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveSysWorkflowApprover(Map<String, Object> params);

	/**
	 * 更新工作流审批人信息
	 * @param params
	 */
	void updateSysWorkflowApprover(Map<String, Object> params);

	/**
	 * 删除工作流审批人信息
	 * @param params
	 */
	void deleteSysWorkflowApprover(Map<String, Object> params);
}

