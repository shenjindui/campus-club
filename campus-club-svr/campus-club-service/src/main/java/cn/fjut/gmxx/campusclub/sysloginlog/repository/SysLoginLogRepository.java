package cn.fjut.gmxx.campusclub.sysloginlog.repository;

import cn.fjut.gmxx.campusclub.sysloginlog.entity.SysLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface SysLoginLogRepository extends JpaRepository<SysLoginLogEntity,String>, JpaSpecificationExecutor<SysLoginLogEntity> {


    SysLoginLogEntity  findByUuid(String uuid);



}
