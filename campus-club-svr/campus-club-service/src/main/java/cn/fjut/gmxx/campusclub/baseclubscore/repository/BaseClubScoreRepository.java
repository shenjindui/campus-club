package cn.fjut.gmxx.campusclub.baseclubscore.repository;

import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubScoreRepository extends JpaRepository<BaseClubScoreEntity,String>,
        JpaSpecificationExecutor<BaseClubScoreEntity> {

    BaseClubScoreEntity  findByUuid(String uuid);


    BaseClubScoreEntity findBySchoolYearAndEmestersAndRatersAssociationCode(String schoolYear,
    String emesters, String ratersAssociationCode);


    /*@Query(value = "SELECT max(base_club_score) from base_club_message",nativeQuery = true)
    String  findMaxMessageCd();*/


}
