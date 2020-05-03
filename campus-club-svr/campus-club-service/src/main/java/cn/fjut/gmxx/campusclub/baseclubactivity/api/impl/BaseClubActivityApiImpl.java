package cn.fjut.gmxx.campusclub.baseclubactivity.api.impl;

import cn.fjut.gmxx.campusclub.baseclubactivity.api.BaseClubActivityApiConstants;
import cn.fjut.gmxx.campusclub.baseclubactivity.api.IBaseClubActivityApi;
import cn.fjut.gmxx.campusclub.baseclubactivity.service.IBaseClubActivityService;
import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import cn.fjut.gmxx.campusclub.basefilersc.service.IBaseFileRscService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.DateUtils;
import cn.fjut.gmxx.campusclub.utlis.GsonUtils;
import cn.fjut.gmxx.campusclub.utlis.ListUtils;
import cn.fjut.gmxx.campusclub.utlis.UrlUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("baseClubActivityApi")
public class BaseClubActivityApiImpl implements IBaseClubActivityApi {

	@Autowired
	private IBaseClubActivityService baseClubActivityService;
	@Autowired
	private IBaseFileRscService baseFileRscService;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubActivityPage(Map<String, Object> params) {
		if(MapUtils.getString(params,"paramsTime")!=null){ //如果时间控件的值不为空
			List<String> paramsTimeList=(List<String>)params.get("paramsTime");
			String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
			String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
			params.put("startsTime",startTime);
			params.put("endsTime",endTime);
		}
		PageInfo<Map<String, Object>> page = baseClubActivityService.findBaseClubActivityPage(params);
		//获取文件信息
		List<Map<String, Object>> list=page.getList();
		for (Map<String, Object> map:list) {
			List<String> fileList=GsonUtils.gsonToList(MapUtils.getString(map,"activityFile"),String.class);
			if(fileList!=null&&fileList.size()>1){
				BaseFileRscEntity baseFileRscEntity=baseFileRscService.findBaseFileRscEntityByFileId(fileList.get(0));
				map.put("fileRte",baseFileRscEntity.getFileRte());
				map.put("fileNm",baseFileRscEntity.getFileNm());
			}
		}
		String isLimit=MapUtils.getString(params,"limit");
		if(isLimit!=null){
            List<Map<String, Object>> resultList=ListUtils.getListIndex(page.getList(),4);
            UrlUtils.getTrueUrl(resultList);
        }else {
            //前端文件格式转换
           // UrlUtils.getTrueUrl(page.getList());
        }
		return page;
	}

	@Override
	public List<Map<String, Object>> findBaseClubActivityAll(Map<String, Object> params) {
		return baseClubActivityService.findBaseClubActivityAll(params);
	}

	@Override
	public Map<String, Object> getBaseClubActivityMap(Map<String, Object> params) {
		Map<String, Object> baseClubActivityMap = baseClubActivityService.getBaseClubActivityMap(params);
		return baseClubActivityMap;
	}

	@Override
	public Map<String, Object> saveBaseClubActivityTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubActivityApiConstants.uuid);
		if (null == uuid) {
			return baseClubActivityService.saveBaseClubActivity(params);
		} else {
			return baseClubActivityService.updateBaseClubActivity(params);
		}
	}

	@Override
	public void deleteBaseClubActivityTrans(Map<String, Object> params) {
		baseClubActivityService.deleteBaseClubActivity(params);
	}

}

