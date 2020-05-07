package cn.fjut.gmxx.campusclub.baseclubmember.repository;

import cn.fjut.gmxx.campusclub.baseclubmember.entity.BaseClubMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubMemberRepository extends JpaRepository<BaseClubMemberEntity,String>,
        JpaSpecificationExecutor<BaseClubMemberEntity> {
    /**
     * 查询最大的成员编号
     * @return
     */
    @Query(value = "SELECT max(member_cd) from base_club_member",nativeQuery = true)
    String  findMaxMemberCd();

    /**
     * 根据uuid获取社团成员信息
     * @param uuid
     * @return
     */
    BaseClubMemberEntity findByUuid(String uuid);
}
