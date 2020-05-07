package cn.fjut.gmxx.campusclub.sysbusiness.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysBusinessDao
* @类描述 <pre>请填写</pre>
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
@Repository
public interface ISysBusinessMapper extends BaseMapper<SysBusinessEntity> {

    /**
     * 分页查询系统业务信息
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysBusinessList(Map<String, Object> params);

    /**
     *  查询系统业务信息（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysBusinessAll(Map<String, Object> params);

}

