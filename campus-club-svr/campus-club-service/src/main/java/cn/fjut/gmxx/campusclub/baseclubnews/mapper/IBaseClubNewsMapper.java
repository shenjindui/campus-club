package cn.fjut.gmxx.campusclub.baseclubnews.mapper;

import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubNewsDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-24
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-24 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseClubNewsMapper extends BaseMapper<BaseClubNewsEntity> {

    List<Map<String, Object>> findBaseClubNewsList(Map<String, Object> params);

    List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params);

}

