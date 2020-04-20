package cn.fjut.gmxx.campusclub.sysuser.service;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;

import java.util.Map;


/**
* @类名称 ISysUserService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 1
* @创建时间 2019-12-28
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2019-12-28 新建
* ----------------------------------------------
*
*/
public interface ISysUserService {


    //JPA
	SysUserEntity getSysUserByMapWithJpa(Map<String, Object> params);

    SysUserEntity saveSysUserByMapWithJpa(Map<String, Object> params);

    SysUserEntity forgotSysUserByMapWithJpa(Map<String, Object> params);

    SysUserEntity getSysUserDetailByMapWithJpa(Map<String, Object> params);





    //mybatis
    Map<String, Object>  getSysUserByMapWithMybatis(Map<String, Object> params);

    int  updateSysUserByMapWithJap(Map<String, Object> params);

    PageInfo<Map<String, Object>> findSysUserPage(Map<String, Object> params);

    Map<String, Object> getSysUserMap(Map<String, Object> params);

    Map<String,Object> saveSysUser(Map<String, Object> params);

    Map<String, Object> updateSysUser(Map<String, Object> params);

    SysUserEntity updateSysUserByJpa(SysUserEntity params);

    SysUserEntity deleteOrNotSysUser(Map<String, Object> params);

    SysUserEntity getUserDetail(String userCode);

    //更新登陆成功失败次数

    int updateSysUserLoginSuccessOrFailCount(String loginName,int flag,String time);

    long findBaseUserCount(Map<String, Object> params);




}

