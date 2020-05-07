package cn.fjut.gmxx.campusclub.sysbusiness.repository;

import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SysBusinessRepository extends JpaRepository<SysBusinessEntity,String>,
        JpaSpecificationExecutor<SysBusinessEntity> {

    /**
     * 查询最大的系统业务编号
     * @return
     */
    @Query(value = "SELECT max(business_code) from sys_business",nativeQuery = true)
    String  findMaxBusinessCode();

    /**
     * 根据businessCode获取系统业务详情
     * @param businessCode
     * @return
     */
    SysBusinessEntity findByBusinessCode(String businessCode);

    /**
     * 根据businessAssociationCode获取系统业务详情
     * @param businessAssociationCode
     * @return
     */
    SysBusinessEntity findByBusinessAssociationCode(String businessAssociationCode);

    /**
     * 根据uuid获取系统业务详情
     * @param uuid
     * @return
     */
    SysBusinessEntity findByUuid(String uuid);
}
