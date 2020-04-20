package cn.fjut.gmxx.campusclub.baseddct.mapper;

import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseDdctDao
* @类描述 <pre>请填写</pre>
* @作者 sjd V
* @创建时间 2020-01-12
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V sjd 2020-01-12 新建
* ----------------------------------------------
*
*/
@Repository
@Mapper
public interface IBaseDdctMapper extends BaseMapper<BaseDdctEntity> {

    List<Map<String, Object>> findBaseDdctList(Map<String, Object> params);


}

