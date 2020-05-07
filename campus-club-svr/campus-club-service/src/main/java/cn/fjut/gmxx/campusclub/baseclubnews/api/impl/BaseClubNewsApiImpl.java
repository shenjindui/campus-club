package cn.fjut.gmxx.campusclub.baseclubnews.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.baseclubnews.api.BaseClubNewsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubnews.api.IBaseClubNewsApi;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsVo;
import cn.fjut.gmxx.campusclub.baseclubnews.service.IBaseClubNewsService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseClubNewsApi")
public class BaseClubNewsApiImpl implements IBaseClubNewsApi {

	@Autowired
	private IBaseClubNewsService baseClubNewsService;
    @Autowired
    IBaseClubInfoApi baseClubInfoApi;
    @Autowired
    IBaseClubMemberService baseClubMemberService;
	@Override
	public PageInfo<Map<String, Object>> findBaseClubNewsPage(Map<String, Object> params) {
		QueryTimeParseUtils.parseQueryTime(params);
        if(MapUtils.getString(params,"stChargeSno")!=null){
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("stChargeSno",MapUtils.getString(params,"stChargeSno"));
            Map<String, Object> resultMap  = baseClubInfoApi.getBaseClubInfoMap(queryParams);
            params.put("newsStCd",MapUtils.getString(resultMap,"stCd"));
        }
        //如果是社员查看获取它的社团编号
        if(MapUtils.getString(params,"stSySno")!=null){
            List<Map<String, Object>> results = baseClubMemberService.findBaseClubMemberAll(params);
            List<String> lists = new ArrayList<>();
            for (Map<String, Object> map:results) {
                lists.add(MapUtils.getString(map,"stCd"));
                params.put("newsStCds", lists);
            }
            params.put("newsStCd",MapUtils.getString(results.get(0),"stCd"));
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
		//这里设置为如果此接口就设置为访问量加1
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("uuid",MapUtils.getString(baseClubNewsMap,"uuid"));
        String trafficVolume=
                String.valueOf(Integer.parseInt(MapUtils.getString(baseClubNewsMap,"trafficVolume"))+1);
        updateParams.put("trafficVolume",trafficVolume);
        updateParams.put("userCode",MapUtils.getString(params,"userCode"));
        baseClubNewsService.updateBaseClubNews(updateParams);
		return baseClubNewsMap;
	}

	@Override
	public Map<String, Object> saveBaseClubNewsTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubNewsApiConstants.UUID);
		if (null == uuid) {
			return baseClubNewsService.saveBaseClubNews(params);
		} else {
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

	@Override
	public List<Map<String, Object>> countBaseClubNews(Map<String, Object> params) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<BaseClubNewsVo> list = baseClubNewsService.countBaseClubNews(params);
		for (BaseClubNewsVo vo:list) {
			Map<String, Object> map = new HashMap<String, Object>();
			BeanUtil.copyProperties(vo, map);
			resultList.add(map);
		}
		return resultList;
	}
}

