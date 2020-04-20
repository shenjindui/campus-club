package cn.fjut.gmxx.campusclub.workflow.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;



/**
* @类名称 ISysWorkflowNodeService
* @类描述 <pre>请填写</pre>
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
public interface ISysWorkflowNodeService {

	PageInfo<Map<String, Object>> findSysWorkflowNodePage(Map<String, Object> params);

	Map<String, Object> getSysWorkflowNodeMap(Map<String, Object> params);

	Map<String,Object> saveSysWorkflowNode(Map<String, Object> params);

	Map<String,Object> updateSysWorkflowNode(Map<String, Object> params);

	Map<String,Object> deleteSysWorkflowNode(Map<String, Object> params);

	public long findSysWorkflowNodeCount(Map<String, Object> params);

	PageInfo<Map<String, Object>> findSysWorkflowNodeAll(Map<String, Object> params);

}

