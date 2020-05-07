package cn.fjut.gmxx.campusclub.baseclubfunds.repository;

import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseClubFundsRepository extends JpaRepository<BaseClubFundsEntity,String>,
        JpaSpecificationExecutor<BaseClubFundsEntity> {

    /**
     * 获取当前最大的财务编号
     * @return
     */
    @Query(value = "SELECT max(FUNDS_CD) from base_club_funds",nativeQuery = true)
    String  findFundsMaxCd();

    /**
     * 根据uuid获取财务详情
     * @param uuid
     * @return
     */
    BaseClubFundsEntity findByUuid(String uuid);

    /**
     * 根据orderId获取财务详情
     * @param orderId
     * @return
     */
    BaseClubFundsEntity findByOrderId(String orderId);
}
