package cn.fjut.gmxx.campusclub.basefilersc.mapper;

import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import cn.fjut.gmxx.campusclub.data.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseFileRscDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-21
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-21 新建
* ----------------------------------------------
*
*/
@Repository
public interface IBaseFileRscMapper extends BaseMapper<BaseFileRscEntity> {

    /**
     * 分页查询文件列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findBaseFileRscList(Map<String, Object> params);
}

