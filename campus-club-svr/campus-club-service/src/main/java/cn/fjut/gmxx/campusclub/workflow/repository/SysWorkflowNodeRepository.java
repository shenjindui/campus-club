package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysWorkflowNodeRepository extends JpaRepository<WorkflowNodeEntity,String>,
        JpaSpecificationExecutor<WorkflowNodeEntity> {

    /**
     * 根据WorkflowNodeCode获取工作流节点信息
     * @param WorkflowNodeCode
     * @return
     */
    WorkflowNodeEntity findByWorkFlowNodeCode(String WorkflowNodeCode);

    /**
     * 获取最大的工作流节点编号
     * @return
     */
    @Query(value = "SELECT max(work_flow_node_code) from sys_workflow_node",nativeQuery = true)
    String  findWorkFlowNodeCodeMaxCd();

    /**
     * 根据uuid获取工作流节点信息
     * @param uuid
     * @return
     */
    WorkflowNodeEntity findByUuid(String uuid);

    /**
     * 根据workFlowCode获取工作流节点信息
     * @param workFlowCode
     * @return
     */
    List<WorkflowNodeEntity> findByWorkFlowCode(String workFlowCode);

    /**
     * 根据workFlowCode，workFlowNodeType，statusCd获取工作流节点信息
     * @param workFlowCode
     * @param workFlowNodeType
     * @param statusCd
     * @return
     */
    WorkflowNodeEntity  findByWorkFlowCodeAndWorkFlowNodeTypeAndStatusCd(String workFlowCode,String workFlowNodeType,
                                                                         int statusCd);

    /**
     * 根据workFlowCode，workFlowNodeType获取工作流节点信息
     * @param workFlowCode
     * @param workFlowNodeType
     * @return
     */
    WorkflowNodeEntity  findByWorkFlowCodeAndWorkFlowNodeType(String workFlowCode,String workFlowNodeType);

    /**
     * 根据workFlowCode，workFlowNodeName获取工作流节点信息
     * @param workFlowCode
     * @param workFlowNodeName
     * @return
     */
    WorkflowNodeEntity findByWorkFlowCodeAndAndWorkFlowNodeName(String workFlowCode,String workFlowNodeName );
}
