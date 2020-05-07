package cn.fjut.gmxx.campusclub.baseclubinfo.repository;

import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubInfRepository extends JpaRepository<BaseClubInfoEntity,String>,
        JpaSpecificationExecutor<BaseClubInfoEntity> {

    /**
     * 根据stCd获取社团信息
     * @param stCd
     * @return
     */
    BaseClubInfoEntity findByStCd(String stCd);

    /**
     * 根据uuid获取社团信息
     * @param uuid
     * @return
     */
    BaseClubInfoEntity  findByUuid(String uuid);

    /**
     * 获取当前社团最大编号
     * @return
     */
    @Query(value = "SELECT max(st_cd) from base_club_info",nativeQuery = true)
    String  findMaxStCd();

    /**
     *  根据stName获取社团信息
     * @param stName
     * @return
     */
    BaseClubInfoEntity findByStName(String stName);

    /**
     * 根据stName,SchoolNo,CollegeNo获取社团信息
     * @param stName
     * @param SchoolNo
     * @param CollegeNo
     * @return
     */
    BaseClubInfoEntity findByStNameAndSchoolNoAndCollegeNo(String stName,String SchoolNo,String CollegeNo);

    /**
     * 根据stChargeSno获取社团信息
     * @param stChargeSno
     * @return
     */
    BaseClubInfoEntity findByStChargeSno(String stChargeSno);
}
