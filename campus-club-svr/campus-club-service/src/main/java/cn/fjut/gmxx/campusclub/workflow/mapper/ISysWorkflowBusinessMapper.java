package cn.fjut.gmxx.campusclub.workflow.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowBusinessDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-01
* @版本 v1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
*  shenjindui 2020-02-01 新建
* ----------------------------------------------
*
*/
@Repository
public interface ISysWorkflowBusinessMapper extends BaseMapper<WorkflowBusinessEntity> {

    /**
     * 分页查询工作流业务列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysWorkflowBusinessList(Map<String, Object> params);
}

