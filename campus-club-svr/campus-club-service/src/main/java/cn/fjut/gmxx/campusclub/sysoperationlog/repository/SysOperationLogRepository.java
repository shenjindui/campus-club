package cn.fjut.gmxx.campusclub.sysoperationlog.repository;

import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface SysOperationLogRepository extends JpaRepository<SysOperationLogEntity,String>, JpaSpecificationExecutor<SysOperationLogEntity> {


    SysOperationLogEntity  findByUuid(String uuid);

    @Query(value = "SELECT max(operation_code) from sys_operation_log",nativeQuery = true)
    String  findSysOperationLogMaxCode();



}
