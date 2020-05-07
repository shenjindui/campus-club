package cn.fjut.gmxx.campusclub.baseclubnews.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
/**
* @类名称 IBaseClubNewsApi
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
public interface IBaseClubNewsApi {

    /**
     * 分页查询社团新闻列表
     * @param params
     * @return
     */
	PageInfo<Map<String, Object>> findBaseClubNewsPage(Map<String, Object> params);

    /**
     * 查询社团新闻列表（所有）
     * @param params
     * @return
     */
	List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params);

    /**
     * 根据参数获取社团新闻详情
     * @param params
     * @return
     */
	Map<String, Object> getBaseClubNewsMap(Map<String, Object> params);

    /**
     * 保存社团新闻详情
     * @param params
     * @return
     */
	Map<String, Object> saveBaseClubNewsTrans(Map<String, Object> params);

    /**
     * 删除社团新闻详情
     * @param params
     * @return
     */
	Map<String, Object> deleteBaseClubNewsTrans(Map<String, Object> params);

    /**
     * 根据参数计算社团新闻条数
     * @param params
     * @return
     */
	List<Map<String, Object>> countBaseClubNews(Map<String, Object> params);
}