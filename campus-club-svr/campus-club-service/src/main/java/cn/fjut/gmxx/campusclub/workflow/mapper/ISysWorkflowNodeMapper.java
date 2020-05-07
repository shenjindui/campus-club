package cn.fjut.gmxx.campusclub.workflow.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowNodeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowNodeDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-31
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-31 新建
* ----------------------------------------------
*
*/
@Repository
public interface ISysWorkflowNodeMapper extends BaseMapper<WorkflowNodeEntity> {

    /**
     * 分页查询工作流节点列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysWorkflowNodeList(Map<String, Object> params);

    /**
     * 查询工作流节点列表（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysWorkflowNodeAll(Map<String, Object> params);
}

