package cn.fjut.gmxx.campusclub.baseclubscore.repository;

import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubScoreRepository extends JpaRepository<BaseClubScoreEntity,String>,
        JpaSpecificationExecutor<BaseClubScoreEntity> {

    /**
     * 根据uuid获取得分信息
     * @param uuid
     * @return
     */
    BaseClubScoreEntity  findByUuid(String uuid);

    /**
     * 根据 schoolYear,emesters,ratersAssociationCode获取得分信息
     * @param schoolYear
     * @param emesters
     * @param ratersAssociationCode
     * @return
     */
    BaseClubScoreEntity findBySchoolYearAndEmestersAndRatersAssociationCode(String schoolYear,
    String emesters, String ratersAssociationCode);

    /*@Query(value = "SELECT max(base_club_score) from base_club_message",nativeQuery = true)
    String  findMaxMessageCd();*/
}
