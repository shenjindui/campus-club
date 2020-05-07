package cn.fjut.gmxx.campusclub.basefilersc.repository;

import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileSrcRepository extends JpaRepository<BaseFileRscEntity,String>,
        JpaSpecificationExecutor<BaseFileRscEntity> {


    /**
     * 获取文件的最大编号
     * @return
     */
    @Query(value = "SELECT max(file_id) from base_file_rsc",nativeQuery = true)
    String  findSysFileMaxFileId();

    /**
     * 根据uuid获取文件信息
     * @param uuid
     * @return
     */
    BaseFileRscEntity findByUuid(String uuid);

    /**
     * 根据fileId获取文件信息
     * @param fileId
     * @return
     */
    BaseFileRscEntity findByFileId(String fileId);
}
