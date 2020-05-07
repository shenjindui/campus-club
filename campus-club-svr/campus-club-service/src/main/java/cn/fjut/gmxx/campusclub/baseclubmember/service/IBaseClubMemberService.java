package cn.fjut.gmxx.campusclub.baseclubmember.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubMemberService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1
* @创建时间 2020-02-22
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
public interface IBaseClubMemberService {

	/**
	 * 分页查询社团信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubMemberPage(Map<String, Object> params);

	/**
	 * 查询社团信息（所有）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubMemberAll(Map<String, Object> params);

	/**
	 * 根据参数获取社团成员信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubMemberMap(Map<String, Object> params);

	/**
	 * 保存社团成员信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubMember(Map<String, Object> params);

	/**
	 * 更新社团成员信息
	 * @param params
	 */
	void updateBaseClubMember(Map<String, Object> params);

	/**
	 * 删除社团成员
	 * @param params
	 */
	void deleteBaseClubMember(Map<String, Object> params);
}

