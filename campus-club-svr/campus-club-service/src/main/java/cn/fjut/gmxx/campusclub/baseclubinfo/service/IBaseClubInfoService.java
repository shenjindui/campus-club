package cn.fjut.gmxx.campusclub.baseclubinfo.service;


import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseClubInfoService
* @类描述 <pre>请填写</pre>
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
public interface IBaseClubInfoService {

	PageInfo<Map<String, Object>> findBaseClubInfoPage(Map<String, Object> params);

	Map<String, Object> getBaseClubInfoMap(Map<String, Object> params);

	Map<String,Object> saveBaseClubInfo(Map<String, Object> params);

	Map<String, Object> updateBaseClubInfo(Map<String, Object> params);

	void deleteBaseClubInfo(Map<String, Object> params);

	Map<String, Object> saveBaseClubOpinion(Map<String, Object> params);

	BaseClubInfoEntity finBaseClubInfoByStCd(String stCd);

	BaseClubInfoEntity finBaseClubInfoByStChargeSno(String stChargeSno);

	List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params);


}

