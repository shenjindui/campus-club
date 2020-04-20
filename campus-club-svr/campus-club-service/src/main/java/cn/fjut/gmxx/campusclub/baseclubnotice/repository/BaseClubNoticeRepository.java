package cn.fjut.gmxx.campusclub.baseclubnotice.repository;

import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubNoticeRepository extends JpaRepository<BaseClubNoticeEntity,String>,
        JpaSpecificationExecutor<BaseClubNoticeEntity> {
    @Query(value = "SELECT max(notice_cd) from base_club_notice",nativeQuery = true)
    String  findMaxNoticeCd();
    BaseClubNoticeEntity findByUuid(String uuid);
}
