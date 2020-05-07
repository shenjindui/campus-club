package cn.fjut.gmxx.campusclub.sysrole.api.impl;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysrole.api.ISysRoleApi;
import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;
import cn.fjut.gmxx.campusclub.sysrole.service.ISysRoleService;
import cn.fjut.gmxx.campusclub.utlis.QueryTimeParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-01-11 10:40
 **/
@Service("sysRoleApi")
public class SysRoleApiImpl implements ISysRoleApi {

    @Autowired
    ISysRoleService iSysRoleService;

    @Override
    public PageInfo<Map<String, Object>> findSysRolePage(Map<String, Object> params) {
        QueryTimeParseUtils.parseQueryTime(params);//时间控件条件处理
        PageInfo<Map<String, Object>> page = iSysRoleService.findSysRolePage(params);
        return page;
    }

    @Override
    public Map<String, Object> getSysRoleMap(Map<String, Object> params) {
        if(params==null){
            throw ExceptionFactory.getBizException("请求错误");
        }
        Map<String, Object> sysMenuMap = iSysRoleService.getSysRoleMap(params);
        return sysMenuMap;
    }

    @Override
    public Map<String, Object> saveSysRoleTrans(Map<String, Object> params) {
        Object  uuid = params.get(SysMenuApiConstants.uuid);
        //新增
        if (null == uuid) {
            return iSysRoleService.saveSysRole(params);
        } else {
            //修改
            return iSysRoleService.updateSysRole(params);
        }
    }

    @Override
    public Map<String, Object> deleteSysRoleTrans(Map<String, Object> params) {
        SysRoleEntity sysRoleEntity=iSysRoleService.deleteSysRole(params);
        params.clear();
        params.put("result",sysRoleEntity);
        return params;
    }

    @Override
    public List<Map<String, Object>> findSysRoleNoPage(Map<String, Object> params) {
        return iSysRoleService.findSysRoleNoPage(params);
    }
}
