package cn.fjut.gmxx.campusclub.baseclubinfo.mapper;

import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubInfoDao
* @类描述 <pre>请填写</pre>
* @作者 v v
* @创建时间 2020-01-18
* @版本 vv
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* v v 2020-01-18 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseClubInfoMapper extends BaseMapper<BaseClubInfoEntity> {

    List<Map<String, Object>> findBaseClubInfoList(Map<String, Object> params);

    //查询所有的数据
    List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params);

}
