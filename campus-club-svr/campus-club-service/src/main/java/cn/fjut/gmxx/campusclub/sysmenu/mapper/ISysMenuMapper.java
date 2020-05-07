package cn.fjut.gmxx.campusclub.sysmenu.mapper;

import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @类名称 ISysMenuDao
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 2
* @创建时间 2020-01-11
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-11 新建
* ----------------------------------------------
*
*/
@Repository
public interface ISysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 分页查询菜单列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysMenuList(Map<String, Object> params);

    /**
     * 查询菜单列表（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysMenuListAll(Map<String, Object> params);
}

