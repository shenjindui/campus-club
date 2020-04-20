package cn.fjut.gmxx.campusclub.baseclubinfo.repository;

import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubInfRepository extends JpaRepository<BaseClubInfoEntity,String>, JpaSpecificationExecutor<BaseClubInfoEntity> {
    BaseClubInfoEntity findByStCd(String stCd);

    BaseClubInfoEntity  findByUuid(String uuid);

    @Query(value = "SELECT max(st_cd) from base_club_info",nativeQuery = true)
    String  findMaxStCd();
    BaseClubInfoEntity findByStName(String stName);


    BaseClubInfoEntity findByStNameAndSchoolNoAndCollegeNo(String stName,String SchoolNo,String CollegeNo);

    BaseClubInfoEntity findByStChargeSno(String stChargeSno);



}
