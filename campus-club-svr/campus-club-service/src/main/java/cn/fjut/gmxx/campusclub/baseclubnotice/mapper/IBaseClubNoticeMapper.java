package cn.fjut.gmxx.campusclub.baseclubnotice.mapper;

import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeVo;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubNoticeDao
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
public interface IBaseClubNoticeMapper extends BaseMapper<BaseClubNoticeEntity> {

    /**
     * 分页查询社团公告列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubNoticeList(Map<String, Object> params);

    /**
     * 查询社团公告列表（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubNoticeListAll(Map<String, Object> params);

    /**
     * 计算社团公告条数
     * @param params
     * @return
     */
    List<BaseClubNoticeVo> countBaseClubNotice(Map<String, Object> params);
}

