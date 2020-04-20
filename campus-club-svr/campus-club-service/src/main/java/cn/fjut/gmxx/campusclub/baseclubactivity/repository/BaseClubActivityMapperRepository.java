package cn.fjut.gmxx.campusclub.baseclubactivity.repository;

import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface BaseClubActivityMapperRepository extends JpaRepository<BaseClubActivityEntity,String>,
        JpaSpecificationExecutor<BaseClubActivityEntity> {

}
