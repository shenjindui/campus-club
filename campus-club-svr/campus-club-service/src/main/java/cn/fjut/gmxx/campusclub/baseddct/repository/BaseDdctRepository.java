package cn.fjut.gmxx.campusclub.baseddct.repository;

import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseDdctRepository extends JpaRepository<BaseDdctEntity,Integer>, JpaSpecificationExecutor<BaseDdctEntity> {

    List<BaseDdctEntity> findByDctKey(String dctKey);
    BaseDdctEntity findByUuid(String uuid);

    BaseDdctEntity findByDctKeyAndDctVal(String ddctKey,String ddctVal);

    BaseDdctEntity  findByDctKeyAndDctTpCd(String ddctKey,String dctTpCd);

}
