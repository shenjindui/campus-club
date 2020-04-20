package cn.fjut.gmxx.campusclub.baseclubfunds.repository;

import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubFundsRepository extends JpaRepository<BaseClubFundsEntity,String>,
        JpaSpecificationExecutor<BaseClubFundsEntity> {

    @Query(value = "SELECT max(FUNDS_CD) from base_club_funds",nativeQuery = true)
    String  findFundsMaxCd();

    BaseClubFundsEntity findByUuid(String uuid);

    BaseClubFundsEntity findByOrderId(String orderId);


}
