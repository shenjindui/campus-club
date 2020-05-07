package cn.fjut.gmxx.campusclub.baseclubmessage.repository;

import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseClubMessageRepository extends JpaRepository<BaseClubMessageEntity,String>,
        JpaSpecificationExecutor<BaseClubMessageEntity> {

    /**
     * 根据uuid查询信息
     * @param uuid
     * @return
     */
    BaseClubMessageEntity  findByUuid(String uuid);

    /**
     * 获取当前最大的留言编号
     * @return
     */
    @Query(value = "SELECT max(message_cd) from base_club_message",nativeQuery = true)
    String  findMaxMessageCd();
}
