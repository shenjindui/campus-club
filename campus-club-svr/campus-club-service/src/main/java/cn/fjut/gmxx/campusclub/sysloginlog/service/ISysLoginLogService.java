package cn.fjut.gmxx.campusclub.sysloginlog.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysloginlog.entity.SysLoginLogEntity;

import java.util.Map;



/**
* @类名称 ISysLoginLogService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-14
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-14 新建
* ----------------------------------------------
*
*/
public interface ISysLoginLogService {

	PageInfo<Map<String, Object>> findSysLoginLogPage(Map<String, Object> params);

	Map<String, Object> getSysLoginLogMap(Map<String, Object> params);

	Map<String,Object> saveSysLoginLog(Map<String, Object> params);

	SysLoginLogEntity deleteSysLogin(Map<String, Object> params);

	void updateSysLoginLog(Map<String, Object> params);

	void deleteSysLoginLog(Map<String, Object> params);

}

