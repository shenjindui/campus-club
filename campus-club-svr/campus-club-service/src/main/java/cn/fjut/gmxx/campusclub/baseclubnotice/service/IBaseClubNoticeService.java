package cn.fjut.gmxx.campusclub.baseclubnotice.service;

import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeVo;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubNoticeService
* @类描述 <pre>请填写</pre>
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
public interface IBaseClubNoticeService {

	/**
	 * 分页查询公告列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubNoticePage(Map<String, Object> params);

	/**
	 * 查询公告列表（所有）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubNoticeAll(Map<String, Object> params);

	/**
	 * 根据参数获取公告信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubNoticeMap(Map<String, Object> params);

	/**
	 * 保存公告信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubNotice(Map<String, Object> params);

	/**
	 * 更新公告信息
	 * @param params
	 * @return
	 */
	Map<String,Object> updateBaseClubNotice(Map<String, Object> params);

	/**
	 * 删除公告信息
	 * @param params
	 * @return
	 */
	BaseClubNoticeEntity deleteBaseClubNotice(Map<String, Object> params);

	/**
	 * 计算公告条数
	 * @param params
	 * @return
	 */
	long findBaseClubNoticeCount(Map<String, Object> params);

	/**
	 * 计算公告条数
	 * @param params
	 * @return
	 */
	List<BaseClubNoticeVo> countBaseClubNotice(Map<String, Object> params);
}

