package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2020/1/31.
 */
@Repository
public interface SysWorkflowRepository extends JpaRepository<WorkflowEntity,String>,
        JpaSpecificationExecutor<WorkflowEntity> {

    WorkflowEntity findByWorkFlowCode(String WorkflowCode);

    @Query(value = "SELECT max(work_flow_code) from sys_workflow",nativeQuery = true)
    String  findWorkFlowCodeMaxCd();

    WorkflowEntity findByUuid(String uuid);

    WorkflowEntity findByWorkFlowName(String workFlowName);

}
