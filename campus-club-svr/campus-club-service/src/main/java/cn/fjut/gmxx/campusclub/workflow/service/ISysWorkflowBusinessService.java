package cn.fjut.gmxx.campusclub.workflow.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;

import java.util.Map;

/**
* @类名称 ISysWorkflowBusinessService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-01
* @版本 v
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
*  shenjindui 2020-02-01 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowBusinessService {

	/**
	 * 分页查询工作流业务列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysWorkflowBusinessPage(Map<String, Object> params);

	/**
	 * 根据参数获取工作流业务信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysWorkflowBusinessMap(Map<String, Object> params);

	/**
	 * 保存工作流业务信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveSysWorkflowBusiness(Map<String, Object> params);

	/**
	 * 发送至复核岗审批操作
	 * @param params
	 * @return
	 */
	Map<String,Object> saveToNextSysWorkflowBusiness(Map<String, Object> params);

	/**
	 * 复核岗审批通过更新操作
	 * @param params
	 * @return
	 */
	Map<String,Object> saveNextSysWorkflowBusiness(Map<String, Object> params);

	/**
	 * 更新工作流业务信息
	 * @param params
	 * @return
	 */
	Map<String,Object> updateSysWorkflowBusiness(Map<String, Object> params);

	/**
	 * 删除工作流业务信息
	 * @param params
	 */
	void deleteSysWorkflowBusiness(Map<String, Object> params);

	/**
	 *  根据sysBussinessCode获取工作流业务信息
	 * @param sysBussinessCode
	 * @return
	 */
	WorkflowBusinessEntity findBySysBussinessCode(String sysBussinessCode);
}

