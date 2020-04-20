package cn.fjut.gmxx.campusclub.sysmenu.api.impl;

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.ISysMenuApi;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import cn.fjut.gmxx.campusclub.sysmenu.service.ISysMenuService;
import cn.fjut.gmxx.campusclub.utlis.DateUtils;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysMenuApi")
public class SysMenuApiImpl implements ISysMenuApi {

	@Autowired
	private ISysMenuService sysMenuService;
	@Autowired
	RedisUtils redisUtils;

	@Override
	public PageInfo<Map<String, Object>> findSysMenuPage(Map<String, Object> params) {
	    if(params.get("paramsTime")!=null){ //如果时间控件的值不为空
            List<String> paramsTimeList=(List<String>)params.get("paramsTime");
            String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
            String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
            params.put("startTime",startTime);
            params.put("endTime",endTime);
        }
		PageInfo<Map<String, Object>> page = sysMenuService.findSysMenuPage(params);
		page.setTotal(sysMenuService.findSysMenuNoPage(params).size());
		return page;

	}

	@Override
	public Map<String, Object> getSysMenuMap(Map<String, Object> params) {
		if(params==null){
			throw ExceptionFactory.getBizException("请求错误");
		}
		Map<String, Object> sysMenuMap = sysMenuService.getSysMenuMap(params);
		return sysMenuMap;
	}

	@Override
	public Map<String, Object> saveSysMenuTrans(Map<String, Object> params) {
		Object  uuid = params.get(SysMenuApiConstants.uuid);
		//新增
		if (null == uuid) {
			return sysMenuService.saveSysMenu(params);
		} else {
			//修改
            return sysMenuService.updateSysMenu(params);
		}

	}

	@Override
	public Map<String, Object> deleteSysMenuTrans(Map<String, Object> params) {
		SysMenuEntity sysMenuEntity=sysMenuService.deleteSysMenu(params);
		params.clear();
		params.put("result",sysMenuEntity);
		return params;
	}

	@Override
	public Map<String, Object> SysMenuToRoleTrans(Map<String, Object> params) {
		return sysMenuService.SysMenuToRole(params);
	}

}

