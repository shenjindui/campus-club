package cn.fjut.gmxx.campusclub.baseclubfunds.mapper;

import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubFundsMapper
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-08
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-08 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseClubFundsMapper extends BaseMapper<BaseClubFundsEntity> {

    List<Map<String, Object>> findBaseClubFundsList(Map<String, Object> params);

    List<Map<String, Object>> findBaseClubFunds(Map<String, Object> params);


}

