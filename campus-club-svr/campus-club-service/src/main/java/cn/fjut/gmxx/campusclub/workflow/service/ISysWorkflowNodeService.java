package cn.fjut.gmxx.campusclub.workflow.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysWorkflowNodeService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-31
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-31 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowNodeService {

	/**
	 * 分页查询工作流节点列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysWorkflowNodePage(Map<String, Object> params);

	/**
	 * 根据参数获取工作流节点信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysWorkflowNodeMap(Map<String, Object> params);

	/**
	 * 保存工作流节点信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveSysWorkflowNode(Map<String, Object> params);

	/**
	 * 更新工作流节点信息
	 * @param params
	 * @return
	 */
	Map<String,Object> updateSysWorkflowNode(Map<String, Object> params);

	/**
	 * 删除工作流节点信息
	 * @param params
	 * @return
	 */
	Map<String,Object> deleteSysWorkflowNode(Map<String, Object> params);

	/**
	 * 计算工作流节点信息的条数
	 * @param params
	 * @return
	 */
	public long findSysWorkflowNodeCount(Map<String, Object> params);

	/**
	 * 查询工作流节点列表（所有）
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysWorkflowNodeAll(Map<String, Object> params);
}

