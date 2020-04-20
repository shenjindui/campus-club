package cn.fjut.gmxx.campusclub.sysoperationlog.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;

import java.util.Map;



/**
* @类名称 ISysOperationLogService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-15
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-15 新建
* ----------------------------------------------
*
*/
public interface ISysOperationLogService {

	PageInfo<Map<String, Object>> findSysOperationLogPage(Map<String, Object> params);

	Map<String, Object> getSysOperationLogMap(Map<String, Object> params);

	Map<String,Object> saveSysOperationLog(Map<String, Object> params);

	SysOperationLogEntity deleteSysOperation(Map<String, Object> params);

	void updateSysOperationLog(Map<String, Object> params);

	void deleteSysOperationLog(Map<String, Object> params);

}

