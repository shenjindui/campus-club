package cn.fjut.gmxx.campusclub.baseclubmember.repository;

import cn.fjut.gmxx.campusclub.baseclubmember.entity.BaseClubMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubMemberRepository extends JpaRepository<BaseClubMemberEntity,String>,
        JpaSpecificationExecutor<BaseClubMemberEntity> {
    @Query(value = "SELECT max(member_cd) from base_club_member",nativeQuery = true)
    String  findMaxMemberCd();


}
