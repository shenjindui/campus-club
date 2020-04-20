package cn.fjut.gmxx.campusclub.sysrole.service.impl;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysrole.api.SysRoleApiConstants;
import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;
import cn.fjut.gmxx.campusclub.sysrole.mapper.RoleMapper;
import cn.fjut.gmxx.campusclub.sysrole.repository.RoleRepository;
import cn.fjut.gmxx.campusclub.sysrole.service.ISysRoleService;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-01-11 10:38
 **/
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public PageInfo<Map<String, Object>> findSysRolePage(Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        Map<String, Object> queryParams= PageHelp.setPageParms(params);
        queryParams.put("total",roleRepository.count());
        queryParams.put(SysRoleApiConstants.DEL_IND, SysRoleApiConstants.DEL_IND_0);
        return new PageInfo<>(roleMapper.findSysRoleList(queryParams),queryParams);

    }

    @Override
    public Map<String, Object> getSysRoleMap(Map<String, Object> params) {
        PageInfo<Map<String, Object>> sysRolePage = this.findSysRolePage(params);
        long total = sysRolePage.getTotal();
        if (0 < total) {
            List<Map<String, Object>> list = sysRolePage.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> saveSysRole(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw ExceptionFactory.getBizException("campus-club-00003", "params");
        }
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        SysRoleEntity entity = (SysRoleEntity) MapTrunPojo.map2Object(params,SysRoleEntity.class);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.mapCoverToEntity(params);
        //设置roleCode
        String maxRoleCode=roleRepository.findSysRoleMaxRoleCode();
        String nowRoleCode=null;
        //如果暂时不存在文件，则设置为默认的file-00001
        if(maxRoleCode==null){
            nowRoleCode="role-00001";
        }else{
            nowRoleCode= EncodeUtils.getConteactNo("role-",Integer.parseInt(maxRoleCode.split("-")[1]));
        }
        entity.setRoleCode(nowRoleCode);
        //查找是否已经存在此名称的菜单
        SysRoleEntity sysRoleEntity=roleRepository.findByRoleName(params.get("roleName").toString());
        if(sysRoleEntity!=null){
            throw ExceptionFactory.getBizException(params.get("roleName")+"此角色名称已存在");
        }
        SysRoleEntity result = roleRepository.save(entity);
        params.put(SysRoleApiConstants.uuid, result.getUuid());
        return params;
    }

    @Override
    public Map<String,Object> updateSysRole(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, SysRoleApiConstants.uuid);
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        SysRoleEntity entity = roleRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
        }
        entity.mapCoverToEntity(params);
        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        //查找是否已经存在此名称的角色
        SysRoleEntity sysRoleEntity=roleRepository.findByRoleName(params.get("roleName").toString());
        if(sysRoleEntity!=null&&!(sysRoleEntity.getRoleCode().equals(params.get("roleCode").toString()))){
            throw ExceptionFactory.getBizException(params.get("roleName")+"此角色名称已存在");
        }
        SysRoleEntity result = roleRepository.save(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
    }

    @Override
    public SysRoleEntity deleteSysRole(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, SysRoleApiConstants.uuid);
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        SysRoleEntity entity = roleRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
        }
        if(MapUtils.getString(params,"flag").equals("1")){ //获取标志，生效
            entity.setStatusCd(1);//设置为失效
        }
        if(MapUtils.getString(params,"flag").equals("0")){ //获取标志，失效
            entity.setStatusCd(0);//设置为失效
        }
        return roleRepository.save(entity);
    }

    @Override
    public List<Map<String, Object>> findSysRoleNoPage(Map<String, Object> params) {
        Map<String, Object> queryParams= PageHelp.setPageParms(params);
        queryParams.put(SysRoleApiConstants.DEL_IND, SysRoleApiConstants.DEL_IND_0);
        return roleMapper.findSysRoleListAll(queryParams);
    }
}
