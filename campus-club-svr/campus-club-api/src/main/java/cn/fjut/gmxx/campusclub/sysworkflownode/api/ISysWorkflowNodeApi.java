package cn.fjut.gmxx.campusclub.sysworkflownode.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;
/**
* @类名称 ISysWorkflowNodeApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-31
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-31 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowNodeApi {

	/**
	 * 分页查询工作流节点信息
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
	Map<String, Object> saveSysWorkflowNodeTrans(Map<String, Object> params);

	/**
	 * 保存时初始化工作流节点信息
	 * @param params
	 * @return
	 */
	Map<String, Object> saveSysWorkflowNodeInitTrans(Map<String, Object> params);

	/**
	 * 删除工作流节点信息
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteSysWorkflowNodeTrans(Map<String, Object> params);
}