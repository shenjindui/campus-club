package cn.fjut.gmxx.campusclub.sysuser.mapper;


import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysUserList(Map<String, Object> params);

    /**
     * 查询用户列表（所有）
     * @param params
     * @return
     */
    List<Map<String, Object>> findSysUserListAll(Map<String, Object> params);

    /**
     * 根据userCode获取用户详情
     * @param userCode
     * @return
     */
    SysUserEntity selectByUserCode(@Param("userCode") String userCode);
}
