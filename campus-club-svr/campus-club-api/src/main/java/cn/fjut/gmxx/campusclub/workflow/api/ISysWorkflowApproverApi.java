package cn.fjut.gmxx.campusclub.workflow.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;


/**
* @类名称 ISysWorkflowApproverApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-31
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-31 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowApproverApi {

	PageInfo<Map<String, Object>> findSysWorkflowApproverPage(Map<String, Object> params);

	Map<String, Object> getSysWorkflowApproverMap(Map<String, Object> params);

	Map<String, Object> saveSysWorkflowApproverTrans(Map<String, Object> params);

	void deleteSysWorkflowApproverTrans(Map<String, Object> params);
}