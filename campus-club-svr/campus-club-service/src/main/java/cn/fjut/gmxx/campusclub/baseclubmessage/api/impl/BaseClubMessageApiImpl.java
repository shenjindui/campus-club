package cn.fjut.gmxx.campusclub.baseclubmessage.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubmessage.api.BaseClubMessageApiConstants;
import cn.fjut.gmxx.campusclub.baseclubmessage.api.IBaseClubMessageApi;
import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import cn.fjut.gmxx.campusclub.baseclubmessage.service.IBaseClubMessageService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("baseClubMessageApi")
public class BaseClubMessageApiImpl implements IBaseClubMessageApi {

	@Autowired
	private IBaseClubMessageService baseClubMessageService;
	@Autowired
	IBaseClubInfoApi baseClubInfoApi;
	@Override
	public PageInfo<Map<String, Object>> findBaseClubMessagePage(Map<String, Object> params) {
        QueryTimeParseUtils.parseQueryTime(params);
        if(MapUtils.getString(params,"stChargeSno")!=null){
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("stChargeSno",MapUtils.getString(params,"stChargeSno"));
            Map<String, Object> resultMap  = baseClubInfoApi.getBaseClubInfoMap(queryParams);
            params.put("messageStCd",MapUtils.getString(resultMap,"stCd"));
        }
		PageInfo<Map<String, Object>> page = baseClubMessageService.findBaseClubMessagePage(params);
        page.setTotal(baseClubMessageService.findBaseClubMessageCount(params));
		return page;
	}

	@Override
	public List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params) {
		return baseClubMessageService.findBaseClubMessageAll(params);
	}

	@Override
	public Map<String, Object> getBaseClubMessageMap(Map<String, Object> params) {
		Map<String, Object> baseClubMessageMap = baseClubMessageService.getBaseClubMessageMap(params);
		return baseClubMessageMap;
	}

	@Override
	public Map<String, Object> saveBaseClubMessageTrans(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, BaseClubMessageApiConstants.uuid);
		if (null == uuid) {
			return baseClubMessageService.saveBaseClubMessage(params);
		} else {
            return baseClubMessageService.updateBaseClubMessage(params);
		}
	}

	@Override
	public Map<String, Object> clubMessagesInit(Map<String, Object> params) {
		Map<String, Object> resultMap=new HashMap<>();
		//社团列表
		List<Map<String, Object> > stList = baseClubInfoApi.findBaseClubInfo(null);
		resultMap.put("stList",stList);
		return resultMap;
	}

	@Override
	public  Map<String, Object> deleteBaseClubMessageTrans(Map<String, Object> params) {
        BaseClubMessageEntity baseClubMessageEntity=baseClubMessageService.deleteBaseClubMessage(params);
        params.clear();
        params.put("result",baseClubMessageEntity);
        return params;
	}
}

