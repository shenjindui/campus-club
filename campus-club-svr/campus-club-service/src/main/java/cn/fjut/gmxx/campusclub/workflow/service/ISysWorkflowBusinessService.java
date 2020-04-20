package cn.fjut.gmxx.campusclub.workflow.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;

import java.util.Map;


/**
* @类名称 ISysWorkflowBusinessService
* @类描述 <pre>请填写</pre>
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
public interface ISysWorkflowBusinessService {

	PageInfo<Map<String, Object>> findSysWorkflowBusinessPage(Map<String, Object> params);

	Map<String, Object> getSysWorkflowBusinessMap(Map<String, Object> params);

	Map<String,Object> saveSysWorkflowBusiness(Map<String, Object> params);

	//发送至复核岗审批操作
	Map<String,Object> saveToNextSysWorkflowBusiness(Map<String, Object> params);

	//复核岗审批通过更新操作
	Map<String,Object> saveNextSysWorkflowBusiness(Map<String, Object> params);

	Map<String,Object> updateSysWorkflowBusiness(Map<String, Object> params);

	void deleteSysWorkflowBusiness(Map<String, Object> params);

	WorkflowBusinessEntity findBySysBussinessCode(String sysBussinessCode);

}

