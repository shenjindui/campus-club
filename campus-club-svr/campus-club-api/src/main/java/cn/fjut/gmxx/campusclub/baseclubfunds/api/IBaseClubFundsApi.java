package cn.fjut.gmxx.campusclub.baseclubfunds.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubFundsApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-21
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-21 新建
* ----------------------------------------------
*
*/
public interface IBaseClubFundsApi {

	PageInfo<Map<String, Object>> findBaseClubFundsPage(Map<String, Object> params);

	Map<String, Object> getBaseClubFundsMap(Map<String, Object> params);

	Map<String, Object> saveBaseClubFundsTrans(Map<String, Object> params);

	Map<String, Object> saveBaseClubFundsInit(Map<String, Object> params);

	Map<String, Object> deleteBaseClubFundsTrans(Map<String, Object> params);

	//用户登陆时发送的消息到前台
	List<Map<String, Object>> findBaseClubFunds(Map<String, Object> params);

	Map<String, Object> saveBaseClubFundsByOrderId(Map<String, Object> params);
}