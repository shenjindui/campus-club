package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysWorkflowBusinessRepository extends JpaRepository<WorkflowBusinessEntity,String>,
        JpaSpecificationExecutor<WorkflowBusinessEntity> {

    /**
     * 获取最大的工作流业务编号
     * @return
     */
    @Query(value = "SELECT max(work_flow_business_code) from sys_workflow_business",nativeQuery = true)
    String  findSysWorkflowBusinessMaxCode();

    /**
     * 根据userCode，pscStCode获取工作流业务信息
     * @param userCode
     * @param pscStCode
     * @return
     */
    List<WorkflowBusinessEntity> findByUserCodeAndPcsStCode(String userCode,String pscStCode);

    /**
     *  根据userCode获取工作流业务信息
     * @param userCode
     * @return
     */
    @Query(value = "SELECT * from sys_workflow_business where user_code=?1 and pcs_st_code in(2,3)",nativeQuery = true)
    List<WorkflowBusinessEntity> findByUserCodeAndPcsStCodeList(String userCode);

    /**
     * 根据uuid获取工作流业务信息
     * @param uuid
     * @return
     */
    WorkflowBusinessEntity findByUuid(String uuid);

    /**
     *  根据businessCode获取工作流业务信息
     * @param businessCode
     * @return
     */
    WorkflowBusinessEntity findByBusinessCode(String businessCode);
}
