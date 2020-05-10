package cn.fjut.gmxx.campusclub.baseclubinfo.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
/**
* @类名称 IBaseClubInfoApi
* @类描述 <pre></pre>
* @作者 shenjindui
* @创建时间 2020-01-18
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
*  2020-01-18 新建
* ----------------------------------------------
*
*/
public interface IBaseClubInfoApi {

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
	Map<String, Object> getBaseClubInfo(Map<String, Object> params);

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
	Map<String, Object> saveBaseClubInfoTrans(Map<String, Object> params);

	/**
	 * 保存社团申请意见
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubOpinionTrans(Map<String, Object> params);

	/**
	 * 删除社团
	 * @param params
	 */
	void deleteBaseClubInfoTrans(Map<String, Object> params);

	/**
	 * 人员申请加入社团
	 * @param params
	 * @return
	 */
	Map<String, Object> joinBaseClubInfoMap(Map<String, Object> params);

	/**
	 * 没有分页查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params);

	/**
	 * 社团社员查询社团信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBaseClubInfoByStsy(Map<String, Object> params);

	/**
	 * 社团添加初始化信息
	 * @param params
	 * @return
	 */
	Map<String, Object> addInitBaseClubInfoMap(Map<String, Object> params);
}