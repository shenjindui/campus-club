package cn.fjut.gmxx.campusclub.workflow.service.impl;


import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowLinkApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowLinkEntity;
import cn.fjut.gmxx.campusclub.workflow.mapper.ISysWorkflowLinkMapper;
import cn.fjut.gmxx.campusclub.workflow.repository.SysWorkflowLinkRepository;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowLinkService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowLinkService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-05
* @版本 v1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-05 新建
* ----------------------------------------------
*
*/
@Service("sysWorkflowLinkService")
public class SysWorkflowLinkServiceImpl implements ISysWorkflowLinkService {
	
	@Autowired
	private ISysWorkflowLinkMapper sysWorkflowLinkMapper;

	@Autowired
	private SysWorkflowLinkRepository sysWorkflowLinkRepository;

	@Autowired
	private ExcetionMsg excetionMsg;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowLinkPage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		params.put("id", MapUtils.getInteger(params, "id"));
		params.put(SysWorkflowLinkApiConstants.DEL_IND, SysWorkflowLinkApiConstants.DEL_IND_0);
		return new PageInfo<>(sysWorkflowLinkMapper.findSysWorkflowLinkList(params),params);
	}
	
	@Override
	public Map<String, Object> getSysWorkflowLinkMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> sysWorkflowLinkPage = this.findSysWorkflowLinkPage(params);
		long total = sysWorkflowLinkPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = sysWorkflowLinkPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysWorkflowLink(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
		WorkflowLinkEntity entity = new WorkflowLinkEntity();
		//MapToEntityUtils.map2Entity(params, entity);
		WorkflowLinkEntity result = sysWorkflowLinkRepository.save(entity);
		params.put(SysWorkflowLinkApiConstants.uuid, result.getUuid());
		return params;
	}
	
	@Override
	public void updateSysWorkflowLink(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowLinkApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		WorkflowLinkEntity entity = new WorkflowLinkEntity();
		entity.setUuid(uuid);
        entity=	sysWorkflowLinkMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		//MapToEntityUtils.map2Entity(params, entity);
		sysWorkflowLinkRepository.save(entity);
	}
	
	@Override
	public void deleteSysWorkflowLink(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, SysWorkflowLinkApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
        WorkflowLinkEntity entity = new WorkflowLinkEntity();
        entity.setUuid(uuid);
        entity=	sysWorkflowLinkMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(SysWorkflowLinkApiConstants.DEL_IND_1); // 逻辑删除标识
		sysWorkflowLinkRepository.save(entity);
	}

	@Override
	public WorkflowLinkEntity findByWorkflowCodeAndNodeCode(String workFlowCode,String workFlowLinkNextCode) {
		return sysWorkflowLinkRepository.findByWorkFlowCodeAndWorkflowLinkNextNode(
				workFlowCode,workFlowLinkNextCode);
	}
}


