package cn.fjut.gmxx.campusclub.sysuser.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysUserApi
* @类描述 <pre></pre>
* @作者 shenjindui 1
* @创建时间 2019-12-28
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2019-12-28 新建
* ----------------------------------------------
*/

public interface ISysUserApi {

	/**
	 * 分页查询用户信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysUserPage(Map<String, Object> params);

	/**
	 * 根据参数获取用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysUserMap(Map<String, Object> params);

	/**
	 * 保存用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> saveSysUserMap(Map<String, Object> params);

	/**
	 * 用户忘记密码
	 * @param params
	 * @return
	 */
	Map<String, Object> forgotSysUserMap(Map<String, Object> params);

	/**
	 * 根据参数获取用户信息详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysUserDetail(Map<String, Object> params);

	/**
	 *  获取用户信息详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysUserByOneMap(Map<String, Object> params);

    /**
     * 更新用户信息
     * @param params
     * @return
     */
	Map<String, Object> updateSysUserByOneMap(Map<String, Object> params);

    /**
     * 删除用户信息
     * @param params
     * @return
     */
	Map<String, Object> deleteSysUserByMap(Map<String, Object> params);

    /**
     *  获取用户信息详情
     * @param params
     * @return
     */
    Map<String, Object> getSysUsersMap(Map<String, Object> params);

    /**
     * 初始化用户信息详情
     * @param params
     * @return
     */
	Map<String, Object> initUserMap(Map<String, Object> params);
}