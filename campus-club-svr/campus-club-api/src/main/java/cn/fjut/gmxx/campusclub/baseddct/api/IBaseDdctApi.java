package cn.fjut.gmxx.campusclub.baseddct.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseDdctApi
* @类描述 <pre></pre>
* @作者 sjd V
* @创建时间 2020-01-12
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V sjd 2020-01-12 新建
* ----------------------------------------------
*
*/
public interface IBaseDdctApi {

	PageInfo<Map<String, Object>> findBaseDdctPage(Map<String, Object> params);

	List<Map<String, Object>> findBaseDdctNoPage(Map<String, Object> params);

	Map<String, Object> getBaseDdctMap(Map<String, Object> params);

	Map<String, Object> saveBaseDdctTrans(Map<String, Object> params);

	Map<String, Object> deleteBaseDdctTrans(Map<String, Object> params);
}