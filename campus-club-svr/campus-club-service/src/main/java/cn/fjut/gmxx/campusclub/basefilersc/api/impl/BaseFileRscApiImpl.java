package cn.fjut.gmxx.campusclub.basefilersc.api.impl;

import cn.fjut.gmxx.campusclub.basefilersc.api.BaseFileRscApiConstants;
import cn.fjut.gmxx.campusclub.basefilersc.api.IBaseFileRscApi;
import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import cn.fjut.gmxx.campusclub.basefilersc.service.IBaseFileRscService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("baseFileRscApi")
public class BaseFileRscApiImpl implements IBaseFileRscApi {

	@Autowired
	private IBaseFileRscService baseFileRscService;

	@Override
	public PageInfo<Map<String, Object>> findBaseFileRscPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = baseFileRscService.findBaseFileRscPage(params);
		//前端文件格式转换
		//UrlUtils.getTrueUrl(page.getList());
		return page;
	}

	@Override
	public Map<String, Object> getBaseFileRscMap(Map<String, Object> params) {
		Map<String, Object> baseFileRscMap = baseFileRscService.getBaseFileRscMap(params);
		return baseFileRscMap;
	}

	@Override
	public Map<String, Object> saveBaseFileRscTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseFileRscApiConstants.UUID);
		if (null == uuid) {
			return baseFileRscService.saveBaseFileRsc(params);
		} else {
			baseFileRscService.updateBaseFileRsc(params);
		}
		return null;
	}

    @Override
	public Map<String, Object> deleteBaseFileRscTrans(Map<String, Object> params) {
		BaseFileRscEntity baseFileRscEntity=baseFileRscService.deleteBaseFileRsc(params);
		params.clear();
		params.put("result",baseFileRscEntity);
		return params;
	}
}

