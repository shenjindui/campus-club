package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/1/31.
 */
@Repository
public interface SysWorkflowBusinessRepository extends JpaRepository<WorkflowBusinessEntity,String>,
        JpaSpecificationExecutor<WorkflowBusinessEntity> {

    @Query(value = "SELECT max(work_flow_business_code) from sys_workflow_business",nativeQuery = true)
    String  findSysWorkflowBusinessMaxCode();


    List<WorkflowBusinessEntity> findByUserCodeAndPcsStCode(String userCode,String pscStCode);

    @Query(value = "SELECT * from sys_workflow_business where user_code=?1 and pcs_st_code in(2,3)",nativeQuery = true)
    List<WorkflowBusinessEntity> findByUserCodeAndPcsStCodeList(String userCode);

    WorkflowBusinessEntity findByUuid(String uuid);

    WorkflowBusinessEntity findByBusinessCode(String businessCode);


}
