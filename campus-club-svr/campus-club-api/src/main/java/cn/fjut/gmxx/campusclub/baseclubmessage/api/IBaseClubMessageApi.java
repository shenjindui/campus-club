package cn.fjut.gmxx.campusclub.baseclubmessage.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
/**
* @类名称 IBaseClubMessageApi
* @类描述 <pre></pre>
* @作者 shenjindui V10
* @创建时间 2020-03-26
* @版本 vV10
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V10 shenjindui 2020-03-26 新建
* ----------------------------------------------
*
*/
public interface IBaseClubMessageApi {

	/**
	 * 分页查询社团留言列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubMessagePage(Map<String, Object> params);

	/**
	 *  查询社团留言列表（所有）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params);

	/**
	 * 根据参数获取信息详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubMessageMap(Map<String, Object> params);

	/**
	 * 保存信息
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubMessageTrans(Map<String, Object> params);

	/**
	 * 社团留言信息初始化
	 * @param params
	 * @return
	 */
	Map<String, Object> clubMessagesInit(Map<String, Object> params);

	/**
	 * 删除社团留言信息
	 * @param params
	 * @return
	 */
    Map<String, Object> deleteBaseClubMessageTrans(Map<String, Object> params);
}