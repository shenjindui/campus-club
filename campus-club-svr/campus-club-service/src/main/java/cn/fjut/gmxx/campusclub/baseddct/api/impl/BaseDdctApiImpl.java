package cn.fjut.gmxx.campusclub.baseddct.api.impl;

import cn.fjut.gmxx.campusclub.baseddct.api.BaseDdctApiConstants;
import cn.fjut.gmxx.campusclub.baseddct.api.IBaseDdctApi;
import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.baseddct.service.IBaseDdctService;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("baseDdctApi")
public class BaseDdctApiImpl implements IBaseDdctApi {

	@Autowired
	private IBaseDdctService baseDdctService;

	@Autowired
	private ExcetionMsg excetionMsg;

	@Override
	public PageInfo<Map<String, Object>> findBaseDdctPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = baseDdctService.findBaseDdctPage(params);
		return page;
	}

	@Override
	public List<Map<String, Object>> findBaseDdctNoPage(Map<String, Object> params) {
		return baseDdctService.findBaseDdctNoPage(params);
	}

	@Override
	public Map<String, Object> getBaseDdctMap(Map<String, Object> params) {
		Map<String, Object> baseDdctMap = baseDdctService.getBaseDdctMap(params);
		return baseDdctMap;
	}

	@Override
	public Map<String, Object> saveBaseDdctTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseDdctApiConstants.UUID);
		if (null == uuid) {
			return baseDdctService.saveBaseDdct(params);
		} else {
			return baseDdctService.updateBaseDdct(params);
		}
	}

	@Override
	public Map<String, Object> deleteBaseDdctTrans(Map<String, Object> params) {
		BaseDdctEntity sysDdctEntity=baseDdctService.deleteBaseDdct(params);
		params.clear();
		params.put("result",sysDdctEntity);
		return params;
	}
}

