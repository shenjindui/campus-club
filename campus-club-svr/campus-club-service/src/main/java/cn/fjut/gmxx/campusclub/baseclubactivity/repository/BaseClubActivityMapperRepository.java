package cn.fjut.gmxx.campusclub.baseclubactivity.repository;

import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubActivityMapperRepository extends JpaRepository<BaseClubActivityEntity,String>,
        JpaSpecificationExecutor<BaseClubActivityEntity> {

    /**
     * 查询活动的最大编号
     * @return
     */
    @Query(value = "SELECT max(activity_id) from base_club_activity",nativeQuery = true)
    String  findMaxActivityId();

    /**
     * 根据uuid获取社团活动详情
     * @param uuid
     * @return
     */
    BaseClubActivityEntity findByUuid(String uuid);

    /**
     * 根据activityName获取社团活动详情
     * @param activityName
     * @return
     */
    BaseClubActivityEntity findByActivityName(String activityName);

    /**
     * 根据activityId获取社团活动详情
     * @param activityId
     * @return
     */
    BaseClubActivityEntity findByActivityId(String activityId);
}
