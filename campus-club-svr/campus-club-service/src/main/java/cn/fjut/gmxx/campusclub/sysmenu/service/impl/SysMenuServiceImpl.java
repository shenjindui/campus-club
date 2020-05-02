package cn.fjut.gmxx.campusclub.sysmenu.service.impl;

import cn.fjut.gmxx.campusclub.baseddct.repository.BaseDdctRepository;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.manager.AbstractCampusClubServer;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysmenu.entity.SysMenuEntity;
import cn.fjut.gmxx.campusclub.sysmenu.mapper.ISysMenuMapper;
import cn.fjut.gmxx.campusclub.sysmenu.repository.MenuRepository;
import cn.fjut.gmxx.campusclub.sysmenu.service.ISysMenuService;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.GsonUtils;
import cn.fjut.gmxx.campusclub.utlis.IconUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @类名称 ISysMenuService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui
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
@Service("sysMenuService")
//声明式事务
@Transactional(readOnly = true,rollbackFor = RuntimeException.class)
public class SysMenuServiceImpl extends AbstractCampusClubServer implements ISysMenuService {
	
	@Autowired
	private ISysMenuMapper sysMenuMapper;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BaseDdctRepository baseDdctRepository;

	@Override
	public PageInfo<Map<String, Object>> findSysMenuPage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        if(MapUtils.getString(params,"init")==null){
            queryParams= PageHelp.setPageParms(params);
        }
		//查询总数
		SysMenuEntity entity=new SysMenuEntity();
		entity.mapCoverToEntity(params);
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<SysMenuEntity> example = Example.of(entity,matcher);
		queryParams.put("total",menuRepository.count(example));
		queryParams.put(SysMenuApiConstants.DEL_IND, SysMenuApiConstants.DEL_IND_0);
        List<Map<String, Object>> list=sysMenuMapper.findSysMenuList(queryParams);
		return new PageInfo<>(list,queryParams);
	}
	
	@Override
	public Map<String, Object> getSysMenuMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> sysMenuPage = this.findSysMenuPage(params);
		long total = sysMenuPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = sysMenuPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
	@Override
	public Map<String, Object> saveSysMenu(Map<String, Object> params) {
        super.validateNull(params);
        //查找当前操作用户
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
		SysMenuEntity entity = (SysMenuEntity) MapTrunPojo.map2Object(params,SysMenuEntity.class);
		entity.setCreateTime(new Date());//设置时间
		entity.setCreateUser(currentUser.getLoginName());
		entity.setUpdateTime(new Date());
		entity.setUpdateUser(currentUser.getLoginName());
		entity.setDelInd("0");//设置默认不删除
		entity.setStatusCd(1);//设置为生效
		entity.setVersion(1);
		entity.setIcon(IconUtils.getRomdam());//设置图标
		entity.mapCoverToEntity(params);
		String maxMenuCode=menuRepository.findSysMenuMaxMenuCode();
		String nowMenuCode=null;
		if(maxMenuCode==null){
			nowMenuCode="menu-00001";
		}else{
			nowMenuCode= EncodeUtils.getConteactNo("menu-",Integer.parseInt(maxMenuCode.split("-")[1]));
		}
        entity.setMenuCode(nowMenuCode);
		//查找是否已经存在此名称的菜单
		SysMenuEntity sysMenuEntity=menuRepository.findByMenuName(params.get("menuName").toString());
        if(sysMenuEntity!=null){
			throw ExceptionFactory.getBizException(params.get("menuName")+"此菜单名称已存在");
		}
		SysMenuEntity result = menuRepository.save(entity);
		params.put(SysMenuApiConstants.uuid, result.getUuid());
		return params;
	}
    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
	@Override
	public Map<String,Object> updateSysMenu(Map<String, Object> params) {
        String uuid = MapUtils.getString(params,SysMenuApiConstants.uuid);
        super.validateNull(uuid);
		SysMenuEntity entity = menuRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("系统异常");
		}
        entity.mapCoverToEntity(params);
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        SysMenuEntity sysMenuEntity=menuRepository.findByMenuName(params.get("menuName").toString());
        if(sysMenuEntity!=null&&!(sysMenuEntity.getMenuCode().equals(params.get("menuCode").toString()))){
            throw ExceptionFactory.getBizException(params.get("menuName")+"此菜单名称已存在");
        }
        SysMenuEntity result = menuRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}
    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
	@Override
	public SysMenuEntity deleteSysMenu(Map<String, Object> params) {
		String uuid = params.get(SysMenuApiConstants.uuid).toString();
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus_club-00002");
		}
		SysMenuEntity entity = menuRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
		}
		entity.setDelInd(SysMenuApiConstants.DEL_IND_1); // 逻辑删除标识
        return menuRepository.save(entity);
	}

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
	@Override
	public Map<String, Object> SysMenuToRole(Map<String, Object> params) {
		List<SysMenuEntity> listMenu=GsonUtils.gsonToList(MapUtils.getString(params,"selectMenuData"),SysMenuEntity.class);
		for (SysMenuEntity sysMenuEntity:listMenu) {
			SysMenuEntity menuEntity=menuRepository.findByUuid(sysMenuEntity.getUuid());
			if(menuEntity.getRoleCode()==null||menuEntity.getRoleCode().equals("")){//如果菜单未分配角色//则设置roleCode
				List<String> roleCodeList=new ArrayList<>();
				roleCodeList.add(MapUtils.getString(params,"selectRoleCode"));
				menuEntity.setRoleCode(GsonUtils.gsonString(roleCodeList));
				menuRepository.save(menuEntity);
			}else{
				//已经设置的roleCodeList
				Set<String> setMenuRole=GsonUtils.gsonToSet(menuEntity.getRoleCode(),String.class);
				setMenuRole.add(MapUtils.getString(params,"selectRoleCode"));
				menuEntity.setRoleCode(GsonUtils.gsonString(setMenuRole));
                menuRepository.save(menuEntity);
			}
		}
		params.clear();
		params.put("result","操作成功");
		return params;
	}
	@Override
	public long findSysMenuCount(Map<String, Object> params) {
		SysMenuEntity entity = new SysMenuEntity();
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("parentMenuCode",
				ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("delInd",
				ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<SysMenuEntity> example = Example.of(entity,matcher);
		return menuRepository.count(example);
	}
	@Override
	public List<Map<String, Object>> findSysMenuNoPage(Map<String, Object> params) {
		return sysMenuMapper.findSysMenuListAll(params);
	}


}


