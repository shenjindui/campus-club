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
* @版本 vV10
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V10 shenjindui 2020-03-26 新建
* ----------------------------------------------
*
*/
public interface IBaseClubMessageService {

	PageInfo<Map<String, Object>> findBaseClubMessagePage(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params);

	Map<String, Object> getBaseClubMessageMap(Map<String, Object> params);

	Map<String,Object> saveBaseClubMessage(Map<String, Object> params);

	Map<String,Object> updateBaseClubMessage(Map<String, Object> params);

	BaseClubMessageEntity deleteBaseClubMessage(Map<String, Object> params);

}

