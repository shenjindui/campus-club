package cn.fjut.gmxx.campusclub.baseclubnews.repository;

import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubNewsRepository extends JpaRepository<BaseClubNewsEntity,String>,
        JpaSpecificationExecutor<BaseClubNewsEntity> {

    /**
     * 获取最大的新闻编号
     * @return
     */
    @Query(value = "SELECT max(news_cd) from base_club_news",nativeQuery = true)
    String  findSysNewsMaxCd();

    /**
     * 根据uuid获取社团新闻详情
     * @param uuid
     * @return
     */
    BaseClubNewsEntity findByUuid(String uuid);
}
