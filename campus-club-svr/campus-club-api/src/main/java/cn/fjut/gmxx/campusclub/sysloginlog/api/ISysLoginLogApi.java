package cn.fjut.gmxx.campusclub.sysloginlog.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;


/**
* @类名称 ISysLoginLogApi
* @类描述 <pre></pre>
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
public interface ISysLoginLogApi {

	PageInfo<Map<String, Object>> findSysLoginLogPage(Map<String, Object> params);

	Map<String, Object> getSysLoginLogMap(Map<String, Object> params);

	Map<String, Object> saveSysLoginLogTrans(Map<String, Object> params);

	Map<String, Object> deleteSysLoginTrans(Map<String, Object> params);

	void deleteSysLoginLogTrans(Map<String, Object> params);
}