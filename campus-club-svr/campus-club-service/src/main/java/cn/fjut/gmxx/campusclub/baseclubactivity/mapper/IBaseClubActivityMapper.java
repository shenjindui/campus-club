package cn.fjut.gmxx.campusclub.baseclubactivity.mapper;

import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubActivityDao
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
public interface IBaseClubActivityMapper extends BaseMapper<BaseClubActivityEntity> {

    /**
     * 分页查询社团活动信息
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubActivityList(Map<String, Object> params);

    /**
     * 查询社团活动信息（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubActivityAll(Map<String, Object> params);

}

