package cn.fjut.gmxx.campusclub.sysoperationlog.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 ISysOperationLogApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-15
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-15 新建
* ----------------------------------------------
*
*/
public interface ISysOperationLogApi {

	/**
	 * 分页查找系统操作日志
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysOperationLogPage(Map<String, Object> params);

	/**
	 * 根据条件获取系统操作日志详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysOperationLogMap(Map<String, Object> params);

	/**
	 * 保存系统操作日志
	 * @param params
	 * @return
	 */
	Map<String, Object> saveSysOperationLogTrans(Map<String, Object> params);

	/**
	 * 删除系统操作日志
	 * @param params
	 * @return
	 */
	Map<String, Object> deleteOperationLogTrans(Map<String, Object> params);

}