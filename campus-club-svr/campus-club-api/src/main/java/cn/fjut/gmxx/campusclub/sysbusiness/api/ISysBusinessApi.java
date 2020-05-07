package cn.fjut.gmxx.campusclub.sysbusiness.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;
/**
* @类名称 ISysBusinessApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-31
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-31 新建
* ----------------------------------------------
*
*/
public interface ISysBusinessApi {

	/**
	 * 分页查询系统业务信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findSysBusinessPage(Map<String, Object> params);

	/**
	 * 根据参数获取系统业务信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getSysBusinessMap(Map<String, Object> params);

	/**
	 * 保存系统业务信息
	 * @param params
	 * @return
	 */
	Map<String, Object> saveSysBusinessTrans(Map<String, Object> params);

	/**
	 * 删除系统业务信息
	 * @param params
	 */
	void deleteSysBusinessTrans(Map<String, Object> params);
}