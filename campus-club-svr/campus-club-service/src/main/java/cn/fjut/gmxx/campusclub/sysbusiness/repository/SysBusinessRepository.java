package cn.fjut.gmxx.campusclub.sysbusiness.repository;

import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2020/1/31.
 */
@Repository
public interface SysBusinessRepository extends JpaRepository<SysBusinessEntity,String>,
        JpaSpecificationExecutor<SysBusinessEntity> {

    @Query(value = "SELECT max(business_code) from sys_business",nativeQuery = true)
    String  findMaxBusinessCode();

    SysBusinessEntity findByBusinessCode(String businessCode);


    SysBusinessEntity findByBusinessAssociationCode(String businessAssociationCode);

    SysBusinessEntity findByUuid(String uuid);


}
