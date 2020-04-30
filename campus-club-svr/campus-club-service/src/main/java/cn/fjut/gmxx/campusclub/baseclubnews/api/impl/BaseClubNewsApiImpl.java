package cn.fjut.gmxx.campusclub.baseclubnews.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubnews.api.BaseClubNewsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubnews.api.IBaseClubNewsApi;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.baseclubnews.service.IBaseClubNewsService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseClubNewsApi")
public class BaseClubNewsApiImpl implements IBaseClubNewsApi {

	@Autowired
	private IBaseClubNewsService baseClubNewsService;
    @Autowired
    IBaseClubInfoApi baseClubInfoApi;
	@Override
	public PageInfo<Map<String, Object>> findBaseClubNewsPage(Map<String, Object> params) {
		QueryTimeParseUtils.parseQueryTime(params);
        if(MapUtils.getString(params,"stChargeSno")!=null){
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("stChargeSno",MapUtils.getString(params,"stChargeSno"));
            Map<String, Object> resultMap  = baseClubInfoApi.getBaseClubInfoMap(queryParams);
            params.put("newsStCd",MapUtils.getString(resultMap,"stCd"));
        }
		PageInfo<Map<String, Object>> page = baseClubNewsService.findBaseClubNewsPage(params);
        page.setTotal(baseClubNewsService.findBaseClubNewsCount(params));
		return page;
	}

	@Override
	public List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params) {
		return baseClubNewsService.findBaseClubNewsAll(params);
	}

	@Override
	public Map<String, Object> getBaseClubNewsMap(Map<String, Object> params) {
		Map<String, Object> baseClubNewsMap = baseClubNewsService.getBaseClubNewsMap(params);
		return baseClubNewsMap;
	}

	@Override
	public Map<String, Object> saveBaseClubNewsTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubNewsApiConstants.UUID);
		//新增
		if (null == uuid) {
			return baseClubNewsService.saveBaseClubNews(params);
		} else {
			//修改
			return baseClubNewsService.updateBaseClubNews(params);
		}
	}

	@Override
	public Map<String, Object> deleteBaseClubNewsTrans(Map<String, Object> params) {
		BaseClubNewsEntity entity=baseClubNewsService.deleteBaseClubNews(params);
		params.clear();
		params.put("result",entity);
		return params;
	}

}

