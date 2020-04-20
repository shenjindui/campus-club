package cn.fjut.gmxx.campusclub.workflow.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;


/**
* @类名称 ISysWorkflowApi
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
public interface ISysWorkflowApi {

	PageInfo<Map<String, Object>> findSysWorkflowPage(Map<String, Object> params);

	Map<String, Object> getSysWorkflowMap(Map<String, Object> params);

	Map<String, Object> saveSysWorkflowTrans(Map<String, Object> params);

	Map<String, Object> deleteSysWorkflowTrans(Map<String, Object> params);
}