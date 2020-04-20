package cn.fjut.gmxx.campusclub.sysrole.repository;

import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by shenjindui  on  2020/1/11.
 */
@Repository
public interface RoleRepository extends JpaRepository<SysRoleEntity,String>, JpaSpecificationExecutor<SysRoleEntity> {
    /**
     * 查询当前最大的角色编号
     * @return
     */
    @Query(value = "SELECT max(role_code) from sys_role",nativeQuery = true)
    String  findSysRoleMaxRoleCode();

    /**
     * 根据角色名称获取对象
     * @param roleName
     * @return
     */
    SysRoleEntity findByRoleName(String roleName);

    /**
     * 根据uuid获取对象
     * @param uuid
     * @return
     */
    SysRoleEntity findByUuid(String uuid);

    /**
     * 根据角色编号获取对象
     * @param roleCode
     * @return
     */
    SysRoleEntity findByRoleCode(String roleCode);
}
