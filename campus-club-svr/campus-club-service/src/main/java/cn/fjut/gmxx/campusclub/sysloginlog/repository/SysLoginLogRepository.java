package cn.fjut.gmxx.campusclub.sysloginlog.repository;

import cn.fjut.gmxx.campusclub.sysloginlog.entity.SysLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLoginLogRepository extends JpaRepository<SysLoginLogEntity,String>, JpaSpecificationExecutor<SysLoginLogEntity> {

    /**
     * 根据uuid获取登陆日志信息
     * @param uuid
     * @return
     */
    SysLoginLogEntity  findByUuid(String uuid);
}
