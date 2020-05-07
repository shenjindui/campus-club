package cn.fjut.gmxx.campusclub.baseclubactivity.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
/**
* @类名称 IBaseClubActivityApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-08
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-08 新建
* ----------------------------------------------
*
*/
public interface IBaseClubActivityApi {

	/**
	 * 分页查询数据
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubActivityPage(Map<String, Object> params);

	/**
	 * 查询数据所有的
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubActivityAll(Map<String, Object> params);

	/**
	 * 根据参数获取活动详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubActivityMap(Map<String, Object> params);

	/**
	 * 保存活动详情
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubActivityTrans(Map<String, Object> params);

	/**
	 * 删除活动
	 * @param params
	 */
	void deleteBaseClubActivityTrans(Map<String, Object> params);

	/**
	 * 初始化保存的方法
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubActivityInitTrans(Map<String, Object> params);
}