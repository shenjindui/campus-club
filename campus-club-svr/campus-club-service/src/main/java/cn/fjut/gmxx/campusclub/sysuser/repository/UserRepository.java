package cn.fjut.gmxx.campusclub.sysuser.repository;

import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface UserRepository extends JpaRepository<SysUserEntity,Integer>, JpaSpecificationExecutor<SysUserEntity> {

    SysUserEntity findByLoginNameAndPassword(String loginName,String  password);

    SysUserEntity findByLoginNameAndJobNumAndMobile(String loginName,String  jobNum,String mobile);

    SysUserEntity findByLoginName(String loginName);

    SysUserEntity findByUuid(String id);

    SysUserEntity findByUserCode(String userCode);

    @Transactional
    @Modifying
    @Query(value = "update sys_user set password=?2 where login_name=?1",nativeQuery = true)
    int resetSysUserPass(String loginName,String password);


    @Query(value = "SELECT max(user_code) from sys_user",nativeQuery = true)
    String  findSysUserMaxUserCode();


    @Transactional
    @Modifying
    @Query(value = "update sys_user set  login_succ_count=login_succ_count+1 where user_code=?1",nativeQuery = true)
    int sysUserLoginSuucess(String userCode);

    @Transactional
    @Modifying
    @Query(value = "update sys_user set  pass_error_count=pass_error_count+1 where login_name=?1",nativeQuery = true)
    int sysUserLoginFail(String loginName);

}
