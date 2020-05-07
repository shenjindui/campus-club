package cn.fjut.gmxx.campusclub.baseclubscore.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;



/**
* @类名称 IBaseClubScoreService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-27
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-27 新建
* ----------------------------------------------
*
*/
public interface IBaseClubScoreService {

	/**
	 * 分页查询得分信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubScorePage(Map<String, Object> params);

	/**
	 * 根据参数获取社团得分信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubScoreMap(Map<String, Object> params);

	/**
	 * 保存社团得分信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubScore(Map<String, Object> params);

	/**
	 * 更新社团得分信息
	 * @param params
	 * @return
	 */
	Map<String, Object> updateBaseClubScore(Map<String, Object> params);

	/**
	 * 删除社团得分信息
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteBaseClubScore(Map<String, Object> params);

	/**
	 * 计算社团得分条数
	 * @param params
	 * @return
	 */
	long findBaseClubCount(Map<String, Object> params);
}

