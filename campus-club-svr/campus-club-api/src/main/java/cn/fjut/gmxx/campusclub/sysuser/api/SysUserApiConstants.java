package cn.fjut.gmxx.campusclub.sysuser.api;

/**
* @类名称 SysUserApiConstants
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 1
* @创建时间 2019-12-28
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2019-12-28 新建
* ----------------------------------------------
*
*/
public interface SysUserApiConstants {

	String USER_CODE = "userCode";//用户编号
	String REALNAME = "realname";//真实姓名
	String LOGIN_NAME = "loginName";//用户名
	String PASSWORD = "password";
	String SALT = "salt";
	String ROLE_ID = "roleId";
	String DEPART_ID = "departId";
	String STATUS_CD = "statusCd";
	String SEX_CD = "sexCd";
	String JOB_NUM = "jobNum";
	String QQ = "qq";
	String WECHAT = "wechat";
	String MOBILE = "mobile";
	String EMAIL = "email";
	String PASS_ERROR_COUNT = "passErrorCount";
	String LOGIN_SUCC_COUNT = "loginSuccCount";
	String LOCK_TIME = "lockTime";
	String REMARK = "remark";
	String ADDRESS = "address";
	String LAST_LOGIN_TIME = "lastLoginTime";

	String ID = "id";

	String UUID = "uuid";
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


