package cn.fjut.gmxx.campusclub.baseclubfunds.service;


import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseClubFundsService
* @类描述 <pre>请填写</pre>
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
public interface IBaseClubFundsService {

	PageInfo<Map<String, Object>> findBaseClubFundsPage(Map<String, Object> params);

	Map<String, Object> getBaseClubFundsMap(Map<String, Object> params);

	Map<String,Object> saveBaseClubFunds(Map<String, Object> params);

	Map<String,Object> updateBaseClubFunds(Map<String, Object> params);

	BaseClubFundsEntity deleteBaseClubFunds(Map<String, Object> params);

    List<Map<String, Object>> findBaseClubFunds(Map<String, Object> params);

	Map<String, Object> saveBaseClubFundsByOrderId(Map<String, Object> params);

	long findBaseClubCount(Map<String, Object> params);
}

