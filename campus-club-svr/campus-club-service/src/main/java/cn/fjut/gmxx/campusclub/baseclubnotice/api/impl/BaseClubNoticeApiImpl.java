package cn.fjut.gmxx.campusclub.baseclubnotice.api.impl;

import cn.fjut.gmxx.campusclub.baseclubnotice.api.BaseClubNoticeApiConstants;
import cn.fjut.gmxx.campusclub.baseclubnotice.api.IBaseClubNoticeApi;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import cn.fjut.gmxx.campusclub.baseclubnotice.service.IBaseClubNoticeService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("baseClubNoticeApiImpl")
public class BaseClubNoticeApiImpl implements IBaseClubNoticeApi {

	@Autowired
	private IBaseClubNoticeService baseClubNoticeService;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubNoticePage(Map<String, Object> params) {
		if(params!=null && params.get("paramsTime")!=null){
			List<String> paramsTimeList=(List<String>)params.get("paramsTime");
			String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
			String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
			params.put("startTime",startTime);
			params.put("endTime",endTime);
		}
		PageInfo<Map<String, Object>> page = baseClubNoticeService.findBaseClubNoticePage(params);
		return page;
	}

	@Override
	public List<Map<String, Object>> findBaseClubNoticeAll(Map<String, Object> params) {
		if(params!=null && params.get("paramsTime")!=null){
			List<String> paramsTimeList=(List<String>)params.get("paramsTime");
			String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
			String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
			params.put("startTime",startTime);
			params.put("endTime",endTime);
		}
		List<Map<String, Object>> list = baseClubNoticeService.findBaseClubNoticeAll(params);
		return list;
	}

	@Override
	public Map<String, Object> getBaseClubNoticeMap(Map<String, Object> params) {
		Map<String, Object> baseClubNoticeMap = baseClubNoticeService.getBaseClubNoticeMap(params);
		return baseClubNoticeMap;
	}

	@Override
	public Map<String, Object> saveBaseClubNoticeTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubNoticeApiConstants.uuid);

		//新增
		if (null == uuid) {
			return baseClubNoticeService.saveBaseClubNotice(params);
		} else {
			//修改
			return baseClubNoticeService.updateBaseClubNotice(params);
		}
	}

	@Override
	public Map<String, Object> deleteBaseClubNoticeTrans(Map<String, Object> params) {
		BaseClubNoticeEntity baseClubNoticeEntity = baseClubNoticeService.deleteBaseClubNotice(params);
		params.clear();
		params.put("result",baseClubNoticeEntity);
		return params;

	}

}

