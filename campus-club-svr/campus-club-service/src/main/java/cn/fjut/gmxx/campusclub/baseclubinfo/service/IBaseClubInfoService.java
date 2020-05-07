package cn.fjut.gmxx.campusclub.baseclubinfo.service;


import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubInfoService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui
* @创建时间 2020-01-18
* @版本 vv
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* shenjindui 2020-01-18 新建
* ----------------------------------------------
*
*/
public interface IBaseClubInfoService {

	/**
	 * 分页查询社团信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubInfoPage(Map<String, Object> params);

	/**
	 * 根据参数获取社团信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubInfoMap(Map<String, Object> params);

	/**
	 * 保存社团信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubInfo(Map<String, Object> params);

	/**
	 * 更新社团信息
	 * @param params
	 * @return
	 */
	Map<String, Object> updateBaseClubInfo(Map<String, Object> params);

	/**
	 * 删除社团信息
	 * @param params
	 */
	void deleteBaseClubInfo(Map<String, Object> params);

	/**
	 * 保存申请理由
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubOpinion(Map<String, Object> params);

	/**
	 * 根据stCd获取社团详情
	 * @param stCd
	 * @return
	 */
	BaseClubInfoEntity finBaseClubInfoByStCd(String stCd);

	/**
	 * 根据stChargeSno获取社团详情
	 * @param stChargeSno
	 * @return
	 */
	BaseClubInfoEntity finBaseClubInfoByStChargeSno(String stChargeSno);

	/**
	 * 查询社团信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params);
}

