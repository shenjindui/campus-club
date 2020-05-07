package cn.fjut.gmxx.campusclub.baseclubnotice.repository;

import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubNoticeRepository extends JpaRepository<BaseClubNoticeEntity,String>,
        JpaSpecificationExecutor<BaseClubNoticeEntity> {
    /**
     * 获取最大的公告编号
     * @return
     */
    @Query(value = "SELECT max(notice_cd) from base_club_notice",nativeQuery = true)
    String  findMaxNoticeCd();

    /**
     * 根据uuid获取公告信息
     * @param uuid
     * @return
     */
    BaseClubNoticeEntity findByUuid(String uuid);
}
