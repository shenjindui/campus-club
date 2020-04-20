package cn.fjut.gmxx.campusclub.sysuserrolerel.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysuserrolerel.entity.SysUserRoleRelEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysUserRoleRelDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 2
* @创建时间 2020-01-11
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-11 新建
* ----------------------------------------------
*
*/
@Repository
public interface ISysUserRoleRelMapper extends BaseMapper<SysUserRoleRelEntity> {

    List<Map<String, Object>> findSysUserRoleRelList(Map<String, Object> params);

}

