package cn.fjut.gmxx.campusclub.sysloginlog.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysLoginLogApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-14
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-14 新建
* ----------------------------------------------
*
*/
public interface ISysLoginLogApi {

	/**
	 * 分页查询登陆日志
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysLoginLogPage(Map<String, Object> params);

	/**
	 * 根据参数获取登陆详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysLoginLogMap(Map<String, Object> params);

	/**
	 * 保存登陆日志
	 * @param params
	 * @return
	 */
	Map<String, Object> saveSysLoginLogTrans(Map<String, Object> params);

	/**
	 * 删除登陆日志
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteSysLoginTrans(Map<String, Object> params);
}