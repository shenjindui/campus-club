package cn.fjut.gmxx.campusclub.sysuser.repository;

import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<SysUserEntity,Integer>, JpaSpecificationExecutor<SysUserEntity> {

    /**
     * 根据loginName与password获取用户详情
     * @param loginName
     * @param password
     * @return
     */
    SysUserEntity findByLoginNameAndPassword(String loginName,String  password);

    /**
     * 根据loginName与jobNum，mobile获取用户详情
     * @param loginName
     * @param jobNum
     * @param mobile
     * @return
     */
    SysUserEntity findByLoginNameAndJobNumAndMobile(String loginName,String  jobNum,String mobile);

    /**
     * 根据loginName获取用户详情
     * @param loginName
     * @return
     */
    SysUserEntity findByLoginName(String loginName);

    /**
     * 根据id获取用户详情
     * @param id
     * @return
     */
    SysUserEntity findByUuid(String id);

    /**
     * 根据userCode获取用户详情
     * @param userCode
     * @return
     */
    SysUserEntity findByUserCode(String userCode);

    /**
     * 重置密码
     * @param loginName
     * @param password
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update sys_user set password=?2 where login_name=?1",nativeQuery = true)
    int resetSysUserPass(String loginName,String password);

    /**
     *  获取最大的用户编码
     * @return
     */
    @Query(value = "SELECT max(user_code) from sys_user",nativeQuery = true)
    String  findSysUserMaxUserCode();

    /**
     *  更新登陆成功的次数
     * @param userCode
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update sys_user set  login_succ_count=login_succ_count+1 where user_code=?1",nativeQuery = true)
    int sysUserLoginSuucess(String userCode);

    /**
     * 更新登陆失败的次数
     * @param loginName
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update sys_user set  pass_error_count=pass_error_count+1 where login_name=?1",nativeQuery = true)
    int sysUserLoginFail(String loginName);
}
