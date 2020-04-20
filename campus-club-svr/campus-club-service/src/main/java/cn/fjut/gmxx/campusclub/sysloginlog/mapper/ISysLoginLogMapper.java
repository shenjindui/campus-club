package cn.fjut.gmxx.campusclub.sysloginlog.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysloginlog.entity.SysLoginLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 ISysLoginLogDao
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
@Repository
public interface ISysLoginLogMapper extends BaseMapper<SysLoginLogEntity> {

    List<Map<String, Object>> findSysLoginLogList(Map<String, Object> params);

}

