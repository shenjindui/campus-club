package cn.fjut.gmxx.campusclub.sysloginlog.api.impl;

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysloginlog.api.ISysLoginLogApi;
import cn.fjut.gmxx.campusclub.sysloginlog.api.SysLoginLogApiConstants;
import cn.fjut.gmxx.campusclub.sysloginlog.entity.SysLoginLogEntity;
import cn.fjut.gmxx.campusclub.sysloginlog.service.ISysLoginLogService;
import cn.fjut.gmxx.campusclub.utlis.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysLoginLogApi")
public class SysLoginLogApiImpl implements ISysLoginLogApi {

	@Autowired
	private ISysLoginLogService sysLoginLogService;

	@Override
	public PageInfo<Map<String, Object>> findSysLoginLogPage(Map<String, Object> params) {
		if(params!=null &&params.get("paramsTime")!=null){ //如果时间控件的值不为空
			List<String> paramsTimeList=(List<String>)params.get("paramsTime");
			String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
			String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
			params.put("startTime",startTime);
			params.put("endTime",endTime);
		}
		PageInfo<Map<String, Object>> page = sysLoginLogService.findSysLoginLogPage(params);
		return page;
	}

	@Override
	public Map<String, Object> getSysLoginLogMap(Map<String, Object> params) {
        if(params==null){
            throw ExceptionFactory.getBizException("请求错误");
        }
	    Map<String, Object> sysLoginLogMap = sysLoginLogService.getSysLoginLogMap(params);
		return sysLoginLogMap;
	}

	@Override
	public Map<String, Object> saveSysLoginLogTrans(Map<String, Object> params) {
		String uuid  = MapUtils.getString(params, SysLoginLogApiConstants.UUID);

		//新增
		if (null == uuid) {
			return sysLoginLogService.saveSysLoginLog(params);
		} else {
			//修改
			sysLoginLogService.updateSysLoginLog(params);
		}
		return null;
	}

    @Override
    public Map<String, Object> deleteSysLoginTrans(Map<String, Object> params) {
        SysLoginLogEntity sysMenuEntity=sysLoginLogService.deleteSysLogin(params);
        params.clear();
        params.put("result",sysMenuEntity);
        return params;
    }

    @Override
	public void deleteSysLoginLogTrans(Map<String, Object> params) {
		sysLoginLogService.deleteSysLoginLog(params);
	}

}

