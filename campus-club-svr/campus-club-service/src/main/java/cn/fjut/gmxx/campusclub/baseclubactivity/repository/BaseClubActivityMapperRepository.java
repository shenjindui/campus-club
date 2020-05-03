package cn.fjut.gmxx.campusclub.baseclubactivity.repository;

import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseClubActivityMapperRepository extends JpaRepository<BaseClubActivityEntity,String>,
        JpaSpecificationExecutor<BaseClubActivityEntity> {

    @Query(value = "SELECT max(activity_id) from base_club_activity",nativeQuery = true)
    String  findMaxActivityId();

    BaseClubActivityEntity findByUuid(String uuid);

    BaseClubActivityEntity findByActivityName(String activityName);



}
