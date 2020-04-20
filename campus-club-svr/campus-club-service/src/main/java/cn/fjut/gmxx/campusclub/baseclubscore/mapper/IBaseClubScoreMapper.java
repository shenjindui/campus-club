package cn.fjut.gmxx.campusclub.baseclubscore.mapper;

import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubScoreDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-27
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-27 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseClubScoreMapper extends BaseMapper<BaseClubScoreEntity> {

    List<Map<String, Object>> findBaseClubScoreList(Map<String, Object> params);

    List<Map<String, Object>> findBaseClubScoreAll(Map<String, Object> params);

}

