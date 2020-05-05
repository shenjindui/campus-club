package cn.fjut.gmxx.campusclub.baseclubactivity.service;


import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseClubActivityService
* @类描述 <pre>请填写</pre>
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
public interface IBaseClubActivityService {

	PageInfo<Map<String, Object>> findBaseClubActivityPage(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubActivityAll(Map<String, Object> params);

	Map<String, Object> getBaseClubActivityMap(Map<String, Object> params);

	Map<String,Object> saveBaseClubActivity(Map<String, Object> params);

	Map<String,Object> updateBaseClubActivity(Map<String, Object> params);

	BaseClubActivityEntity deleteBaseClubActivity(Map<String, Object> params);

	BaseClubActivityEntity findBaseClubActivityByActivityId(String activityId);

}

