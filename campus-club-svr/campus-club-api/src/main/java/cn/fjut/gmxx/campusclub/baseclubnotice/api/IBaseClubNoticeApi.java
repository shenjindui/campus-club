package cn.fjut.gmxx.campusclub.baseclubnotice.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
/**
* @类名称 IBaseClubNoticeApi
* @类描述 <pre></pre>
* @作者 shenjindui V
* @创建时间 2020-02-24
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-24 新建
* ----------------------------------------------
*
*/
public interface IBaseClubNoticeApi {

	/**
	 * 分页查询公告信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubNoticePage(Map<String, Object> params);

	/**
	 * 查询公告信息（所有）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubNoticeAll(Map<String, Object> params);

	/**
	 * 根据参数获取公告信息详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubNoticeMap(Map<String, Object> params);

	/**
	 * 保存公告信息
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubNoticeTrans(Map<String, Object> params);

	/**
	 * 删除公告信息
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteBaseClubNoticeTrans(Map<String, Object> params);

	/**
	 * 计算社团公告条数
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> countBaseClubNotice(Map<String, Object> params);
}