package cn.fjut.gmxx.campusclub.baseclubinfo.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubInfoApi
* @类描述 <pre></pre>
* @作者 v v
* @创建时间 2020-01-18
* @版本 vv
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* v v 2020-01-18 新建
* ----------------------------------------------
*
*/
public interface IBaseClubInfoApi {

	PageInfo<Map<String, Object>> findBaseClubInfoPage(Map<String, Object> params);

	Map<String, Object> getBaseClubInfoMap(Map<String, Object> params);

	Map<String, Object> saveBaseClubInfoTrans(Map<String, Object> params);

	Map<String, Object> saveBaseClubOpinionTrans(Map<String, Object> params);

	void deleteBaseClubInfoTrans(Map<String, Object> params);

	Map<String, Object> joinBaseClubInfoMap(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params);

	List<Map<String, Object>> getBaseClubInfoByStsy(Map<String, Object> params);
}