package cn.fjut.gmxx.campusclub.workflow.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowLinkEntity;

import java.util.Map;



/**
* @类名称 ISysWorkflowLinkService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-05
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-05 新建
* ----------------------------------------------
*
*/
public interface ISysWorkflowLinkService {

	PageInfo<Map<String, Object>> findSysWorkflowLinkPage(Map<String, Object> params);

	Map<String, Object> getSysWorkflowLinkMap(Map<String, Object> params);

	Map<String,Object> saveSysWorkflowLink(Map<String, Object> params);

	void updateSysWorkflowLink(Map<String, Object> params);

	void deleteSysWorkflowLink(Map<String, Object> params);

	WorkflowLinkEntity findByWorkflowCodeAndNodeCode(String workFlowCode,String workFlowLinkNextCode);

}

