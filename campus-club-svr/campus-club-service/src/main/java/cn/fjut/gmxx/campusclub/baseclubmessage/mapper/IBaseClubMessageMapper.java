package cn.fjut.gmxx.campusclub.baseclubmessage.mapper;

import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubMessageDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V10
* @创建时间 2020-03-26
* @版本 vV10
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V10 shenjindui 2020-03-26 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseClubMessageMapper extends BaseMapper<BaseClubMessageEntity> {

    /**
     * 分页查询社团留言信息
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubMessageList(Map<String, Object> params);

    /**
     * 查询社团留言信息（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params);

}

