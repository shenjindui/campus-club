package cn.fjut.gmxx.campusclub.baseclubmessage.service;

import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubMessageService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V10
* @创建时间 2020-03-26
* @版本 V10
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V10 shenjindui 2020-03-26 新建
* ----------------------------------------------
*
*/
public interface IBaseClubMessageService {

	/**
	 * 分页查询留言信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubMessagePage(Map<String, Object> params);

	/**
	 * 查询留言信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params);

	/**
	 * 根据参数获取社团留言信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubMessageMap(Map<String, Object> params);

	/**
	 * 保存社团留言信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubMessage(Map<String, Object> params);

	/**
	 * 更新社团留言信息
	 * @param params
	 * @return
	 */
	Map<String,Object> updateBaseClubMessage(Map<String, Object> params);

	/**
	 * 删除社团留言信息
	 * @param params
	 * @return
	 */
	BaseClubMessageEntity deleteBaseClubMessage(Map<String, Object> params);

	/**
	 * 计算社团留言信息条数
	 * @param params
	 * @return
	 */
	long findBaseClubMessageCount(Map<String, Object> params);
}

