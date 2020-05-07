package cn.fjut.gmxx.campusclub.workflow.service.impl;


import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApiConstants;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowNodeApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowNodeEntity;
import cn.fjut.gmxx.campusclub.workflow.mapper.ISysWorkflowNodeMapper;
import cn.fjut.gmxx.campusclub.workflow.repository.SysWorkflowNodeRepository;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowNodeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowNodeService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-31
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-31 新建
* ----------------------------------------------
*
*/
@Service("sysWorkflowNodeService")
public class SysWorkflowNodeServiceImpl implements ISysWorkflowNodeService {
	
	@Autowired
	private ISysWorkflowNodeMapper sysWorkflowNodeMapper;

	@Autowired
	private SysWorkflowNodeRepository sysWorkflowNodeRepository;

    @Autowired
    UserRepository userRepository;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowNodePage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        queryParams= PageHelp.setPageParms(params);
        WorkflowNodeEntity entity=new WorkflowNodeEntity();
        entity.setDelInd("0");
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<WorkflowNodeEntity> example = Example.of(entity,matcher);
        queryParams.put("total",sysWorkflowNodeRepository.count(example));
        queryParams.put(SysWorkflowApiConstants.DEL_IND, SysWorkflowApiConstants.DEL_IND_0);
		return new PageInfo<>(sysWorkflowNodeMapper.findSysWorkflowNodeList(queryParams),queryParams);
	}
	
	@Override
	public Map<String, Object> getSysWorkflowNodeMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> sysWorkflowNodePage = this.findSysWorkflowNodePage(params);
		long total = sysWorkflowNodePage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = sysWorkflowNodePage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysWorkflowNode(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        if(sysWorkflowNodeRepository.findByWorkFlowCodeAndWorkFlowNodeTypeAndStatusCd(
                MapUtils.getString(params,"workFlowCode"),MapUtils.getString(params,"workFlowNodeType"),1)
                !=null){
            throw ExceptionFactory.getBizException("当前流程节点已存在，不能重复添加！");
        }
        switch (MapUtils.getString(params,"workFlowNodeType")){
            case "1":break; //如果时第一层直接通过
            case "2":this.CheckWorkFlowNodeIsExit(MapUtils.getString(params,"workFlowCode"),
                    "1");break;
            case "3":this.CheckWorkFlowNodeIsExit(MapUtils.getString(params,"workFlowCode"),
                    "2");break;
            default:break;
        }
		if(sysWorkflowNodeRepository.findByWorkFlowCodeAndAndWorkFlowNodeName(
				MapUtils.getString(params,"workFlowCode"),MapUtils.getString(params,"workFlowNodeName"))
				!=null){
			throw ExceptionFactory.getBizException("同一流程中，已存在此节点名称！");
		}
		WorkflowNodeEntity entity = new WorkflowNodeEntity();
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.setRemark("工作流程节点新增");
        String maxCode=sysWorkflowNodeRepository.findWorkFlowNodeCodeMaxCd();
        String nowCode=null;
        if(maxCode==null){
            nowCode="workflownode-00001";
        }else{
            nowCode= EncodeUtils.getConteactNo("workflownode-",Integer.parseInt(maxCode.split("-")[1]));
        }
        entity.setWorkFlowNodeCode(nowCode);
        entity.mapCoverToEntity(params);
		WorkflowNodeEntity result = sysWorkflowNodeRepository.save(entity);
		params.put(SysWorkflowNodeApiConstants.uuid, result.getUuid());
		return params;
	}

    /**
     * 节点保存时需要判断前一个节点是否存在
     * @param workFlowCode
     * @param workFlowNodeType
     */
	private void CheckWorkFlowNodeIsExit(String workFlowCode,String workFlowNodeType){
        if(sysWorkflowNodeRepository.findByWorkFlowCodeAndWorkFlowNodeTypeAndStatusCd(
                workFlowCode,workFlowNodeType,1)
                ==null){
            throw ExceptionFactory.getBizException("清先添加上一级节点层级！");
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
	@Override
	public Map<String,Object> updateSysWorkflowNode(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowNodeApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("获取节点uuid异常");
		}
		WorkflowNodeEntity entity = sysWorkflowNodeRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
        entity.mapCoverToEntity(params);
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        WorkflowNodeEntity workflowNodeEntity= sysWorkflowNodeRepository.findByWorkFlowCodeAndWorkFlowNodeTypeAndStatusCd(
                MapUtils.getString(params,"workFlowCode"),MapUtils.getString(params,"workFlowNodeType"),1);
        if(workflowNodeEntity!=null&&workflowNodeEntity.getUuid()!=uuid){
            throw ExceptionFactory.getBizException("当前流程节点已存在，不能重复添加！");
        }
        WorkflowNodeEntity workflowNodeEntity2=sysWorkflowNodeRepository.findByWorkFlowCodeAndAndWorkFlowNodeName(
                MapUtils.getString(params,"workFlowCode"),MapUtils.getString(params,"workFlowNodeName"));
        if(workflowNodeEntity2!=null&&workflowNodeEntity2.getUuid()!=uuid){
            throw ExceptionFactory.getBizException("同一流程中，已存在此节点名称！");
        }
        switch (MapUtils.getString(params,"workFlowNodeType")){
            case "1":break; //如果时第一层直接通过
            case "2":this.CheckWorkFlowNodeIsExit(MapUtils.getString(params,"workFlowCode"),
                    "1");break;
            case "3":this.CheckWorkFlowNodeIsExit(MapUtils.getString(params,"workFlowCode"),
                    "2");break;
            default:break;
        }
        WorkflowNodeEntity result=sysWorkflowNodeRepository.save(entity);
        params.put(SysWorkflowNodeApiConstants.uuid, result.getUuid());
		return params;
	}
	
	@Override
	public Map<String, Object> deleteSysWorkflowNode(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowNodeApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		WorkflowNodeEntity entity = sysWorkflowNodeRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(SysWorkflowNodeApiConstants.DEL_IND_1); // 逻辑删除标识
		sysWorkflowNodeRepository.save(entity);
		return params;
	}

    @Override
    public long findSysWorkflowNodeCount(Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        WorkflowNodeEntity entity=new WorkflowNodeEntity();
        entity.setDelInd("0");
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<WorkflowNodeEntity> example = Example.of(entity,matcher);
        return sysWorkflowNodeRepository.count(example);
    }

    @Override
    public PageInfo<Map<String, Object>> findSysWorkflowNodeAll(Map<String, Object> params) {
        return new PageInfo<>(sysWorkflowNodeMapper.findSysWorkflowNodeAll(params),params);
    }

    /**
     * 查询某个工作流下 某个节点是否已存在
     * @param woekFlowCode
     * @param woekFlowNodeType
     * @return
     */

	/*private Boolean findWorkNodeIsExit(String woekFlowCode,String woekFlowNodeType){
        Boolean isExit=false;
        WorkflowNodeEntity workflowNodeEntity=sysWorkflowNodeRepository.findByWorkFlowCodeAndWorkFlowNodeType(
                woekFlowCode,woekFlowNodeType);
        if(workflowNodeEntity!=null){
            isExit=true;
        }
        return isExit;
    }*/
}


