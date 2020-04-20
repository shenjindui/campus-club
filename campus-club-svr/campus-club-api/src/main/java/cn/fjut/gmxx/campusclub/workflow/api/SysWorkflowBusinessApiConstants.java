package cn.fjut.gmxx.campusclub.workflow.api;

/**
* @类名称 SysWorkflowBusinessApiConstants
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
public interface SysWorkflowBusinessApiConstants {

	String uuid = "uuid";
	String create_time = "createTime";
	String create_user = "createUser";
	String del_ind = "delInd";
	String remark = "remark";
	String status_cd = "statusCd";
	String update_time = "updateTime";
	String update_user = "updateUser";
	String version = "version";
	String user_code = "userCode";
	String work_flow_approver_code = "workFlowApproverCode";
	String work_flow_code = "workFlowCode";
	String work_flow_node_code = "workFlowNodeCode";
	String work_flow_business_code = "workFlowBusinessCode";
	String approver_code = "approverCode";
	String suggestion = "suggestion";

	String ID = "id";
	/**
	* 删除标识
	*/
	String DEL_IND = "delInd";
	/**
	* 租户ID
	*/
	String TENANT_ID = "tenantId";
	/**
	* 客户端ID
	*/
	String CLNTEND_ID = "clntendId";

	/**
	* 删除标志：0：已删除
	*/
	String DEL_IND_0 = "0";
	/**
	* 删除标志：1：未删除
	*/
	String DEL_IND_1 = "1";
}


