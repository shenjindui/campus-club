package cn.fjut.gmxx.campusclub.baseddct.repository;

import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseDdctRepository extends JpaRepository<BaseDdctEntity,String>,
        JpaSpecificationExecutor<BaseDdctEntity> {

    /**
     * 根据dctKey查询列表
     * @param dctKey
     * @return
     */
    List<BaseDdctEntity> findByDctKey(String dctKey);

    /**
     * 根据uuid查询信息
     * @param uuid
     * @return
     */
    BaseDdctEntity findByUuid(String uuid);

    /**
     *  根据ddctKey,ddctVal查询信息
     * @param ddctKey
     * @param ddctVal
     * @return
     */
    BaseDdctEntity findByDctKeyAndDctVal(String ddctKey,String ddctVal);

    /**
     * 根据ddctKey,dctTpCd查询信息
     * @param ddctKey
     * @param dctTpCd
     * @return
     */
    BaseDdctEntity  findByDctKeyAndDctTpCd(String ddctKey,String dctTpCd);
}
