package cn.fjut.gmxx.campusclub.sysoperationlog.service;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;

import java.util.Map;

/**
* @类名称 ISysOperationLogService
* @类描述 <pre>请填写</pre>
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
public interface ISysOperationLogService {

    /**
     * 分页查询系统操作日志
     * @param params
     * @return
     */
	PageInfo<Map<String, Object>> findSysOperationLogPage(Map<String, Object> params);

    /**
     * 根据Map参数获取系统操作详情
     * @param params
     * @return
     */
	Map<String, Object> getSysOperationLogMap(Map<String, Object> params);

    /**
     * 保存系统操作日志详情
     * @param params
     * @return
     */
	Map<String,Object> saveSysOperationLog(Map<String, Object> params);

    /**
     * 删除系统操作日志
     * @param params
     * @return
     */
	SysOperationLogEntity deleteSysOperation(Map<String, Object> params);

    /**
     * 更新系统操作日志
     * @param params
     * @return
     */
	Map<String,Object> updateSysOperationLog(Map<String, Object> params);
}

