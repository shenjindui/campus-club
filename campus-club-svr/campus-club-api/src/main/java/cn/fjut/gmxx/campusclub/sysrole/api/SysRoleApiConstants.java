package cn.fjut.gmxx.campusclub.sysrole.api;
/**
* @类名称 SysRoleApiConstants
* @类描述 <pre>请填写</pre>
* @作者 2 2
* @创建时间 2020-01-11
* @版本 v2
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* 2 2 2020-01-11 新建
* ----------------------------------------------
*
*/
public interface SysRoleApiConstants {
	/**
	 * uuid 数据唯一标识
	 */
	String uuid = "uuid";
	/**
	 * 创建时间
	 */
	String create_time = "createTime";
	/**
	 * 创建者
	 */
	String create_user = "createUser";
	/**
	 * 备注
	 */
	String remark = "remark";
	/**
	 * 状态
	 */
	String STATUS_CD = "statusCd";
	/**
	 * 更新时间
	 */
	String update_time = "updateTime";
	/**
	 * 更新人
	 */
	String update_user = "updateUser";
	/**
	 * 数据版本号
	 */
	String version = "version";
	/**
	 * 角色编号
	 */
	String role_code = "roleCode";
	/**
	 * 角色名称
	 */
	String role_name = "roleName";
	/**
	 * 是否为初始化角色标识
	 */
	String initRolcode="initRolcode";
	/**
	* 删除标识
	*/
	String DEL_IND = "delInd";
	/**
	* 删除标志：0：未删除
	*/
	String DEL_IND_0 = "0";
	/**
	* 删除标志：1：已删除
	*/
	String DEL_IND_1 = "1";
}


