package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2020/1/31.
 */
@Repository
public interface SysWorkflowLinkRepository extends JpaRepository<WorkflowLinkEntity,String>,
        JpaSpecificationExecutor<WorkflowLinkEntity> {
    WorkflowLinkEntity findByWorkFlowCodeAndWorkflowLinkNextNode(String workFlowCode,String workFlowLinkNextCode);

}
