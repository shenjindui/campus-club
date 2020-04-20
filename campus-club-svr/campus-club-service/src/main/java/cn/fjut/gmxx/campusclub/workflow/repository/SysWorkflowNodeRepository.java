package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/1/31.
 */
@Repository
public interface SysWorkflowNodeRepository extends JpaRepository<WorkflowNodeEntity,String>,
        JpaSpecificationExecutor<WorkflowNodeEntity> {

    WorkflowNodeEntity findByWorkFlowNodeCode(String WorkflowNodeCode);

    @Query(value = "SELECT max(work_flow_node_code) from sys_workflow_node",nativeQuery = true)
    String  findWorkFlowNodeCodeMaxCd();

    WorkflowNodeEntity findByUuid(String uuid);

    List<WorkflowNodeEntity> findByWorkFlowCode(String workFlowCode);

    WorkflowNodeEntity  findByWorkFlowCodeAndWorkFlowNodeTypeAndStatusCd(String workFlowCode,String workFlowNodeType,
                                                                         int statusCd);

    WorkflowNodeEntity  findByWorkFlowCodeAndWorkFlowNodeType(String workFlowCode,String workFlowNodeType);

    WorkflowNodeEntity findByWorkFlowCodeAndAndWorkFlowNodeName(String workFlowCode,String workFlowNodeName );




}
