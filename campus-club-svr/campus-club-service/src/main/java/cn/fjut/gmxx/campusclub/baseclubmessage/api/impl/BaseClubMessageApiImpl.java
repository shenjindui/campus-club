package cn.fjut.gmxx.campusclub.baseclubmessage.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubmessage.api.BaseClubMessageApiConstants;
import cn.fjut.gmxx.campusclub.baseclubmessage.api.IBaseClubMessageApi;
import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import cn.fjut.gmxx.campusclub.baseclubmessage.service.IBaseClubMessageService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
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
		PageInfo<Map<String, Object>> page = baseClubMessageService.findBaseClubMessagePage(params);
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

		//新增
		if (null == uuid) {
			return baseClubMessageService.saveBaseClubMessage(params);
		} else {
			//修改
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

