package cn.fjut.gmxx.campusclub.baseclubscore.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;
/**
* @类名称 IBaseClubScoreApi
* @类描述 <pre></pre>
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
public interface IBaseClubScoreApi {

	/**
	 * 分页查询社团得分列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubScorePage(Map<String, Object> params);

	/**
	 * 根据参数获取社团评分详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubScoreMap(Map<String, Object> params);

	/**
	 * 保存社团评分详情
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubScoreTrans(Map<String, Object> params);

	/**
	 * 保存社团评分初始化
	 * @param params
	 * @return
	 */
	Map<String, Object> baseClubScoreInit(Map<String, Object> params);

	/**
	 * 删除社团评分
	 * @param params
	 */
	void deleteBaseClubScoreTrans(Map<String, Object> params);
}