package cn.fjut.gmxx.campusclub.baseclubnews.mapper;

import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsVo;
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

    /**
     * 分页查询社团新闻信息
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubNewsList(Map<String, Object> params);

    /**
     * 查询社团新闻信息（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params);

    /**
     * 根据参数计算社团信息的条数
     * @param params
     * @return
     */
    List<BaseClubNewsVo> countBaseClubNews(Map<String, Object> params);
}

