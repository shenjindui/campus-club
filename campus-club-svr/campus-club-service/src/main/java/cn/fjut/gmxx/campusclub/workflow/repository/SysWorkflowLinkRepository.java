package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface SysWorkflowLinkRepository extends JpaRepository<WorkflowLinkEntity,String>,
        JpaSpecificationExecutor<WorkflowLinkEntity> {
    /**
     * 根据workFlowCode，workFlowLinkNextCode获取工作流节点信息
     * @param workFlowCode
     * @param workFlowLinkNextCode
     * @return
     */
    WorkflowLinkEntity findByWorkFlowCodeAndWorkflowLinkNextNode(String workFlowCode,String workFlowLinkNextCode);
}
