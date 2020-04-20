package cn.fjut.gmxx.campusclub.workflow.repository;

import cn.fjut.gmxx.campusclub.workflow.entity.WorklowApproverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2020/1/31.
 */
@Repository
public interface SysWorkflowApproverRepository extends JpaRepository<WorklowApproverEntity,String>,
        JpaSpecificationExecutor<WorklowApproverEntity> {
}
