package cn.fjut.gmxx.campusclub.sysbusiness.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysBusinessService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-31
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-31 新建
* ----------------------------------------------
*
*/
public interface ISysBusinessService {

    /**
     * 分页查询系统业务信息
     * @param params
     * @return
     */
	PageInfo<Map<String, Object>> findSysBusinessPage(Map<String, Object> params);

    /**
     * 查询系统业务信息（所有）
     * @param params
     * @return
     */
	List<Map<String, Object>> findSysBusinessNoPage(Map<String, Object> params);

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
	Map<String,Object> saveSysBusiness(Map<String, Object> params);

	/**
	 * 更新系统业务信息
	 * @param params
	 * @return
	 */
	Map<String,Object> updateSysBusiness(Map<String, Object> params);

	/**
	 * 删除系统业务信息
	 * @param params
	 */
	void deleteSysBusiness(Map<String, Object> params);

	/**
	 * 根据bussinessCode系统业务信息
	 * @param bussinessCode
	 * @return
	 */
	SysBusinessEntity findByBussinessCode(String bussinessCode);
}

