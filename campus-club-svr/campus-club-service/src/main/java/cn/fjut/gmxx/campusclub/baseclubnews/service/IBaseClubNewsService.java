package cn.fjut.gmxx.campusclub.baseclubnews.service;

import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsVo;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubNewsService
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
public interface IBaseClubNewsService {

	/**
	 * 分页查询社团新闻
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubNewsPage(Map<String, Object> params);

	/**
	 * 查询社团新闻（所有）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params);

	/**
	 * 根据参数获取社团新闻信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubNewsMap(Map<String, Object> params);

	/**
	 * 保存社团新闻信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubNews(Map<String, Object> params);

	/**
	 * 更新社团新闻信息
	 * @param params
	 * @return
	 */
	Map<String, Object> updateBaseClubNews(Map<String, Object> params);

	/**
	 * 删除社团新闻信息
	 * @param params
	 * @return
	 */
	BaseClubNewsEntity deleteBaseClubNews(Map<String, Object> params);

	/**
	 * 计算社团新闻信息条数
	 * @param params
	 * @return
	 */
	long findBaseClubNewsCount(Map<String, Object> params);

	/**
	 * 计算社团新闻信息条数
	 * @param params
	 * @return
	 */
	List<BaseClubNewsVo> countBaseClubNews(Map<String, Object> params);
}

