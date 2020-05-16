package cn.fjut.gmxx.campusclub.sysuserrolerel.repository;

import cn.fjut.gmxx.campusclub.sysuserrolerel.entity.SysUserRoleRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRoleRelRepository extends JpaRepository<SysUserRoleRelEntity,String>,
        JpaSpecificationExecutor<SysUserRoleRelEntity> {

    /**
     * 根据userCode，defaultRole获取用户关联角色信息
     * @param userCode
     * @param defaultRole
     * @return
     */
    SysUserRoleRelEntity findByUserCodeAndDefaultRole(String userCode,String defaultRole);

    /**
     * 根据uuid获取用户关联角色信息
     * @param uuid
     * @return
     */
    SysUserRoleRelEntity findByUuid(String uuid);

}
