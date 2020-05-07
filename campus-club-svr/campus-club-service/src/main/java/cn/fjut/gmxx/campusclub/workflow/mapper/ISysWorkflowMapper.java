package cn.fjut.gmxx.campusclub.workflow.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-05
* @版本 v1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-05 新建
* ----------------------------------------------
*
*/
@Repository
public interface ISysWorkflowMapper extends BaseMapper<WorkflowEntity> {

    /**
     * 分页查询工作流列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysWorkflowList(Map<String, Object> params);

    /**
     * 查询工作流列表（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysWorkflowAll(Map<String, Object> params);
}

