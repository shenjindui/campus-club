package cn.fjut.gmxx.campusclub.sysoperationlog.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 ISysOperationLogDao
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
@Repository
public interface ISysOperationLogMapper extends BaseMapper<SysOperationLogEntity> {

    /**
     * 分页查询操作日志列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysOperationLogList(Map<String, Object> params);
}

