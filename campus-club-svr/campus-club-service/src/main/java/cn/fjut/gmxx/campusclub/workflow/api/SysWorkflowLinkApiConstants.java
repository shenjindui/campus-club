package cn.fjut.gmxx.campusclub.workflow.api;

/**
* @类名称 SysWorkflowLinkApiConstants
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
public interface SysWorkflowLinkApiConstants {

	String uuid = "uuid";
	String create_time = "createTime";
	String create_user = "createUser";
	String del_ind = "delInd";
	String remark = "remark";
	String status_cd = "statusCd";
	String update_time = "updateTime";
	String update_user = "updateUser";
	String version = "version";
	String work_flow_code = "workFlowCode";
	String work_flow_link_code = "workFlowLinkCode";
	String work_flow_link_name = "workFlowLinkName";
	String workflow_link_next_node = "workflowLinkNextNode";
	String workflow_link_pre_node = "workflowLinkPreNode";

	/**
	* 删除标识
	*/
	String DEL_IND = "delInd";
	/**
	* 删除标志：0：已删除
	*/
	String DEL_IND_0 = "0";
	/**
	* 删除标志：1：未删除
	*/
	String DEL_IND_1 = "1";
}


