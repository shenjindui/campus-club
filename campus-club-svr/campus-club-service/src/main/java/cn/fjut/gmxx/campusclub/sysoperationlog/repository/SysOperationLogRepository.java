package cn.fjut.gmxx.campusclub.sysoperationlog.repository;

import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SysOperationLogRepository extends JpaRepository<SysOperationLogEntity,String>, JpaSpecificationExecutor<SysOperationLogEntity> {


    /**
     * 根据uuid获取操作日志信息
     * @param uuid
     * @return
     */
    SysOperationLogEntity  findByUuid(String uuid);

    /**
     * 获取操作日志最大的编号
     * @return
     */
    @Query(value = "SELECT max(operation_code) from sys_operation_log",nativeQuery = true)
    String  findSysOperationLogMaxCode();
}
