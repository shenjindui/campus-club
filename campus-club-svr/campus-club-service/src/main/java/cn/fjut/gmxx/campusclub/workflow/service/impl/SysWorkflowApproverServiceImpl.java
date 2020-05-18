package cn.fjut.gmxx.campusclub.workflow.service.impl;


import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApproverApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorklowApproverEntity;
import cn.fjut.gmxx.campusclub.workflow.mapper.ISysWorkflowApproverMapper;
import cn.fjut.gmxx.campusclub.workflow.repository.SysWorkflowApproverRepository;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowApproverService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowApproverService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-31
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-31 新建
* ----------------------------------------------
*
*/
@Service("sysWorkflowApproverService")
public class SysWorkflowApproverServiceImpl implements ISysWorkflowApproverService {
	
	@Autowired
	private ISysWorkflowApproverMapper sysWorkflowApproverMapper;

	@Autowired
	SysWorkflowApproverRepository sysWorkflowApproverRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ExcetionMsg excetionMsg;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowApproverPage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
		WorklowApproverEntity entity=new WorklowApproverEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<WorklowApproverEntity> example = Example.of(entity,matcher);
		queryParams.put("total",sysWorkflowApproverRepository.count(example));
		queryParams.put(SysWorkflowApproverApiConstants.DEL_IND, SysWorkflowApproverApiConstants.DEL_IND_0);
		List<Map<String, Object>> resultList=sysWorkflowApproverMapper.findSysWorkflowApproverList(queryParams);
		for (Map<String, Object> map:resultList){
			SysUserEntity sysUserEntity=userRepository.findByUserCode(MapUtils.getString(map,"userCode"));
			map.put("userName",sysUserEntity.getRealname());
			map.put("jobNum",sysUserEntity.getJobNum());
		}
		return new PageInfo<>(resultList,queryParams);
	}
	
	@Override
	public Map<String, Object> getSysWorkflowApproverMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> sysWorkflowApproverPage = this.findSysWorkflowApproverPage(params);
		long total = sysWorkflowApproverPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = sysWorkflowApproverPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysWorkflowApprover(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
		WorklowApproverEntity entity = new WorklowApproverEntity();
		//MapToEntityUtils.map2Entity(params, entity);
		WorklowApproverEntity result = sysWorkflowApproverRepository.save(entity);
		params.put(SysWorkflowApproverApiConstants.uuid, result.getUuid());
		return params;
	}
	
	@Override
	public void updateSysWorkflowApprover(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowApproverApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		WorklowApproverEntity entity = new WorklowApproverEntity();
		entity.setUuid(uuid);
		entity=	sysWorkflowApproverMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		//MapToEntityUtils.map2Entity(params, entity);

		sysWorkflowApproverRepository.save(entity);
	}
	
	@Override
	public void deleteSysWorkflowApprover(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowApproverApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		WorklowApproverEntity entity = new WorklowApproverEntity();
		entity.setUuid(uuid);
		entity=	sysWorkflowApproverMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(SysWorkflowApproverApiConstants.DEL_IND_1); // 逻辑删除标识
		sysWorkflowApproverRepository.save(entity);
	}
}


