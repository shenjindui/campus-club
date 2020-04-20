package cn.fjut.gmxx.campusclub.baseclubmessage.repository;

import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubMessageRepository extends JpaRepository<BaseClubMessageEntity,String>,
        JpaSpecificationExecutor<BaseClubMessageEntity> {

    BaseClubMessageEntity  findByUuid(String uuid);

    @Query(value = "SELECT max(message_cd) from base_club_message",nativeQuery = true)
    String  findMaxMessageCd();


}
