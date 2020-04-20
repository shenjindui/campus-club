package cn.fjut.gmxx.campusclub.baseclubmember.mapper;

import cn.fjut.gmxx.campusclub.baseclubmember.entity.BaseClubMemberEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubMemberDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1
* @创建时间 2020-02-22
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseClubMemberMapper extends BaseMapper<BaseClubMemberEntity> {

    List<Map<String, Object>> findBaseClubMemberList(Map<String, Object> params);

    List<Map<String, Object>> findBaseClubMemberListAll(Map<String, Object> params);

}

