package cn.fjut.gmxx.campusclub.sysuserrolerel.repository;

import cn.fjut.gmxx.campusclub.sysuserrolerel.entity.SysUserRoleRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by admin on 2020/1/11.
 */
public interface UserRoleRelRepository extends JpaRepository<SysUserRoleRelEntity,Integer>,
        JpaSpecificationExecutor<SysUserRoleRelEntity> {
    SysUserRoleRelEntity findByUserCodeAndDefaultRole(String userCode,String defaultRole);

}
