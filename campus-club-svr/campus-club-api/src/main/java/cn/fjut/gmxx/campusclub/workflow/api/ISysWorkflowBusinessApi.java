package cn.fjut.gmxx.campusclub.workflow.api;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysWorkflowBusinessApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-01
* @版本 vcampus-club
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* campus-club shenjindui 2020-02-01 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowBusinessApi {

	PageInfo<Map<String, Object>> findSysWorkflowBusinessPage(Map<String, Object> params);

	Map<String, Object> getSysWorkflowBusinessMap(Map<String, Object> params);

	Map<String, Object> saveSysWorkflowBusinessTrans(Map<String, Object> params);

	void deleteSysWorkflowBusinessTrans(Map<String, Object> params);


	Map<String, Object> refuseSysWorkflowBusinessTrans(Map<String, Object> params);

	//发送到下一级别审批
	Map<String, Object> saveToNextSysWorkflowBusinessTrans(Map<String, Object> params);

	//复核岗审批通过操作
	Map<String, Object> saveNextSysWorkflowBusinessTrans(Map<String, Object> params);
}