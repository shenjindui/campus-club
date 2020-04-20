package cn.fjut.gmxx.campusclub.sysoperationlog.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;


/**
* @类名称 ISysOperationLogApi
* @类描述 <pre></pre>
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
public interface ISysOperationLogApi {

	PageInfo<Map<String, Object>> findSysOperationLogPage(Map<String, Object> params);

	Map<String, Object> getSysOperationLogMap(Map<String, Object> params);

	Map<String, Object> saveSysOperationLogTrans(Map<String, Object> params);

	Map<String, Object> deleteOperationLogTrans(Map<String, Object> params);

	void deleteSysOperationLogTrans(Map<String, Object> params);
}