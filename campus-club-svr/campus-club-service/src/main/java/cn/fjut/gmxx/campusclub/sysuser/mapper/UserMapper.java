package cn.fjut.gmxx.campusclub.sysuser.mapper;


import cn.fjut.gmxx.campusclub.data.BaseMapper;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2020/1/7.
 */
@Repository
public interface UserMapper extends BaseMapper<SysUserEntity> {

    List<Map<String, Object>> findSysUserList(Map<String, Object> params);


    List<Map<String, Object>> findSysUserListAll(Map<String, Object> params);


    SysUserEntity selectByUserCode(@Param("userCode") String userCode);


}
