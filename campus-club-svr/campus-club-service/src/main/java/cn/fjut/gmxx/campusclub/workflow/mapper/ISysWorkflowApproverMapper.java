package cn.fjut.gmxx.campusclub.workflow.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.workflow.entity.WorklowApproverEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowApproverDao
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
@Repository
public interface ISysWorkflowApproverMapper extends BaseMapper<WorklowApproverEntity> {

    /**
     * 分页查询审批人列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysWorkflowApproverList(Map<String, Object> params);
}

