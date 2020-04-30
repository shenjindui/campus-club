package cn.fjut.gmxx.campusclub.baseclubfunds.api.impl;

import cn.fjut.gmxx.campusclub.baseclubfunds.api.BaseClubFundsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubfunds.api.IBaseClubFundsApi;
import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import cn.fjut.gmxx.campusclub.baseclubfunds.service.IBaseClubFundsService;
import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseddct.api.IBaseDdctApi;
import cn.fjut.gmxx.campusclub.baseddct.common.DdctUtils;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("baseClubFundsApiImpl")
public class BaseClubFundsApiImpl implements IBaseClubFundsApi {

	@Autowired
	private IBaseClubFundsService baseClubFundsService;
	@Autowired
	private IBaseDdctApi baseDdctApi;
    @Autowired
	private IBaseClubInfoApi baseClubInfoApi;
    @Autowired
	private DdctUtils dctUtils;
	@Override
	public PageInfo<Map<String, Object>> findBaseClubFundsPage(Map<String, Object> params) {
		QueryTimeParseUtils.parseQueryTime(params);
		PageInfo<Map<String, Object>> page = baseClubFundsService.findBaseClubFundsPage(params);
		page.setTotal(baseClubFundsService.findBaseClubCount(params));
		return page;
	}

	@Override
	public Map<String, Object> getBaseClubFundsMap(Map<String, Object> params) {
		Map<String, Object> baseClubFundsMap = baseClubFundsService.getBaseClubFundsMap(params);
		Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("stCd",MapUtils.getString(baseClubFundsMap,"stCd"));
        baseClubFundsMap.put("stName",MapUtils.getString(baseClubInfoApi.getBaseClubInfoMap(queryParams),"stName"));
		return baseClubFundsMap;
	}

	@Override
	public Map<String, Object> saveBaseClubFundsTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubFundsApiConstants.uuid);

		//新增
		if (null == uuid) {
			return baseClubFundsService.saveBaseClubFunds(params);
		} else {
			//修改
            return baseClubFundsService.updateBaseClubFunds(params);
		}
	}

	@Override
	public Map<String, Object> saveBaseClubFundsInit(Map<String, Object> params) {
        Map<String, Object> resultMap=new HashMap<>();
        Map<String, Object> queryParams=new HashMap<>();
        //金额操作类型
        if(MapUtils.getString(params,"amountType")!=null){
            queryParams.put("dctKey",MapUtils.getString(params,"amountType"));
            List<Map<String, Object>> result=baseDdctApi.findBaseDdctPage(queryParams).getList();
            resultMap.put("amountTypeList",result);
        }
        queryParams.clear();
        //财务操作类型
        if(MapUtils.getString(params,"fundstype")!=null){
            queryParams.put("dctKey",MapUtils.getString(params,"fundstype"));
            List<Map<String, Object>> result=baseDdctApi.findBaseDdctPage(queryParams).getList();
            resultMap.put("fundstypeList",result);
        }
        //社团列表
        List<Map<String, Object> > stList = baseClubInfoApi.findBaseClubInfo(null);
        resultMap.put("stList",stList);
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteBaseClubFundsTrans(Map<String, Object> params) {
        BaseClubFundsEntity baseClubFundsEntity = baseClubFundsService.deleteBaseClubFunds(params);
		params.clear();
		params.put("result",baseClubFundsEntity);
		return params;
	}

	@Override
	public List<Map<String, Object>> findBaseClubFunds(Map<String, Object> params) {
		return baseClubFundsService.findBaseClubFunds(params);
	}

	@Override
	public Map<String, Object> saveBaseClubFundsByOrderId(Map<String, Object> params) {
		return baseClubFundsService.saveBaseClubFundsByOrderId(params);
	}

}

