package cn.fjut.gmxx.campusclub.workflow.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;


/**
* @类名称 ISysWorkflowApi
* @类描述 <pre></pre>
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
public interface ISysWorkflowApi {

	/**
	 * 分页查询工作流信息
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
	Map<String, Object> saveSysWorkflowTrans(Map<String, Object> params);

	/**
	 * 删除工作流信息
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteSysWorkflowTrans(Map<String, Object> params);
}