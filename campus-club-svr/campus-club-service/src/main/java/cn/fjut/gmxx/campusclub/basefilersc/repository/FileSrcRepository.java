package cn.fjut.gmxx.campusclub.basefilersc.repository;

import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2020/1/21.
 */
@Repository
public interface FileSrcRepository extends JpaRepository<BaseFileRscEntity,String>,
        JpaSpecificationExecutor<BaseFileRscEntity> {


    @Query(value = "SELECT max(file_id) from base_file_rsc",nativeQuery = true)
    String  findSysFileMaxFileId();

    BaseFileRscEntity findByUuid(String uuid);


    BaseFileRscEntity findByFileId(String fileId);
}
