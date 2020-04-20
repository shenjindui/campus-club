package cn.fjut.gmxx.campusclub.sysuserrolerel.service.impl;

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuserrolerel.api.SysUserRoleRelApiConstants;
import cn.fjut.gmxx.campusclub.sysuserrolerel.entity.SysUserRoleRelEntity;
import cn.fjut.gmxx.campusclub.sysuserrolerel.mapper.ISysUserRoleRelMapper;
import cn.fjut.gmxx.campusclub.sysuserrolerel.repository.UserRoleRelRepository;
import cn.fjut.gmxx.campusclub.sysuserrolerel.service.ISysUserRoleRelService;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysUserRoleRelService
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
@Service("sysUserRoleRelService")
public class SysUserRoleRelServiceImpl implements ISysUserRoleRelService {
	
	@Autowired
	private ISysUserRoleRelMapper sysUserRoleRelMapper;

	@Autowired
	UserRoleRelRepository userRoleRelRepository;

	@Override
	public PageInfo<Map<String, Object>> findSysUserRoleRelPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		//进行分页参数设置

		Map<String, Object> queryParams= PageHelp.setPageParms(params);
		queryParams.put("total",userRoleRelRepository.count());
		queryParams.put(SysUserRoleRelApiConstants.DEL_IND, SysUserRoleRelApiConstants.DEL_IND_0);
		return new PageInfo<>(sysUserRoleRelMapper.findSysUserRoleRelList(queryParams),queryParams);
	}
	
	@Override
	public Map<String, Object> getSysUserRoleRelMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> sysUserRoleRelPage = this.findSysUserRoleRelPage(params);
		//判断是否存在数据
		long total = sysUserRoleRelPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = sysUserRoleRelPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysUserRoleRel(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus_club-00003", "params");
		}

		SysUserRoleRelEntity entity = new SysUserRoleRelEntity();
		entity.mapCoverToEntity(params);
		entity.setRoleCode("role-00007");//默认注册为系统游客
        entity.setDefaultRole("1");//注册设置为默认角色
		SysUserRoleRelEntity result = userRoleRelRepository.save(entity);
		params.put(SysUserRoleRelApiConstants.uuid, result.getUuid());
		return params;
	}
	
	@Override
	public void updateSysUserRoleRel(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
        String id = MapUtils.getString(params, SysUserRoleRelApiConstants.uuid);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus_club-00002");
		}
		SysUserRoleRelEntity entity = new SysUserRoleRelEntity();
        entity.setUuid(id);
        entity=	sysUserRoleRelMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
		}
		entity= (SysUserRoleRelEntity) MapTrunPojo.map2Object(params,SysUserRoleRelEntity.class);
		//MapToEntityUtils.map2Entity(params, entity);

		userRoleRelRepository.save(entity);
	}
	
	@Override
	public void deleteSysUserRoleRel(Map<String, Object> params) {
        String id = MapUtils.getString(params, SysUserRoleRelApiConstants.uuid);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus_club-00002");
		}
        SysUserRoleRelEntity entity = new SysUserRoleRelEntity();
        entity.setUuid(id);
        entity=	sysUserRoleRelMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
		}
		entity.setDelInd(SysUserRoleRelApiConstants.DEL_IND_1); // 逻辑删除标识
		userRoleRelRepository.save(entity);
	}
	
	
	
}

