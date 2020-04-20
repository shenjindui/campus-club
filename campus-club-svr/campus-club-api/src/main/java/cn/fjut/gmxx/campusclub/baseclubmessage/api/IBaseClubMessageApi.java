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

	PageInfo<Map<String, Object>> findBaseClubMessagePage(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params);

	Map<String, Object> getBaseClubMessageMap(Map<String, Object> params);

	Map<String, Object> saveBaseClubMessageTrans(Map<String, Object> params);

    Map<String, Object> clubMessagesInit(Map<String, Object> params);


    Map<String, Object> deleteBaseClubMessageTrans(Map<String, Object> params);
}