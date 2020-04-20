package cn.fjut.gmxx.campusclub.baseclubnews.repository;

import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubNewsRepository extends JpaRepository<BaseClubNewsEntity,String>,
        JpaSpecificationExecutor<BaseClubNewsEntity> {

    @Query(value = "SELECT max(news_cd) from base_club_news",nativeQuery = true)
    String  findSysNewsMaxCd();

    BaseClubNewsEntity findByUuid(String uuid);
}
