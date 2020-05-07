package cn.fjut.gmxx.campusclub.workflow.service.impl;


import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;
import cn.fjut.gmxx.campusclub.workflow.mapper.ISysWorkflowMapper;
import cn.fjut.gmxx.campusclub.workflow.repository.SysWorkflowRepository;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类名称 ISysWorkflowService
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
@Service("sysWorkflowService")
public class SysWorkflowServiceImpl implements ISysWorkflowService {

    @Autowired
    private ISysWorkflowMapper sysWorkflowMapper;

    @Autowired
    private SysWorkflowRepository sysWorkflowRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PageInfo<Map<String, Object>> findSysWorkflowPage(Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        queryParams= PageHelp.setPageParms(params);
        WorkflowEntity entity=new WorkflowEntity();
        entity.setDelInd("0");
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<WorkflowEntity> example = Example.of(entity,matcher);
        queryParams.put("total",sysWorkflowRepository.count(example));
        queryParams.put(SysWorkflowApiConstants.DEL_IND, SysWorkflowApiConstants.DEL_IND_0);
        return new PageInfo<>(sysWorkflowMapper.findSysWorkflowList(queryParams),queryParams);
    }

    @Override
    public Map<String, Object> getSysWorkflowMap(Map<String, Object> params) {
        PageInfo<Map<String, Object>> sysWorkflowPage = this.findSysWorkflowPage(params);
        long total = sysWorkflowPage.getTotal();
        if (0 < total) {
            List<Map<String, Object>> list = sysWorkflowPage.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> saveSysWorkflow(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw ExceptionFactory.getBizException("campus-club-00003", "params");
        }
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        if(sysWorkflowRepository.findByWorkFlowName(MapUtils.getString(params,"workFlowName"))!=null){
            throw ExceptionFactory.getBizException("流程名称已存在，不能重复！");
        }
        WorkflowEntity entity = new WorkflowEntity();
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.setRemark("工作流程新增");
        String maxCode=sysWorkflowRepository.findWorkFlowCodeMaxCd();
        String nowCode=null;
        if(maxCode==null){
            nowCode="workflow-00001";
        }else{
            nowCode= EncodeUtils.getConteactNo("workflow-",Integer.parseInt(maxCode.split("-")[1]));
        }
        entity.setWorkFlowCode(nowCode);
        entity.mapCoverToEntity(params);
        WorkflowEntity result = sysWorkflowRepository.save(entity);
        params.put(SysWorkflowApiConstants.uuid, result.getUuid());
        return params;
    }

    @Override
    public Map<String,Object> updateSysWorkflow(Map<String, Object> params) {
        String id = MapUtils.getString(params, SysWorkflowApiConstants.uuid);
        if (id == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        WorkflowEntity entity = sysWorkflowRepository.findByUuid(id);

        if (entity == null) {
            throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
        }
        String workFlowName=MapUtils.getString(params,"workFlowName");
        if(!entity.getWorkFlowName().equals(workFlowName)){
            if(sysWorkflowRepository.findByWorkFlowName(MapUtils.getString(params,"workFlowName"))!=null){
                throw ExceptionFactory.getBizException("流程名称已存在，不能重复！");
            }
        }
        entity.mapCoverToEntity(params);
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        WorkflowEntity result = sysWorkflowRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
    }

    @Override
    public WorkflowEntity deleteSysWorkflow(Map<String, Object> params) {
        String id = MapUtils.getString(params, SysWorkflowApiConstants.uuid);
        if (id == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        WorkflowEntity entity = sysWorkflowRepository.findByUuid(id);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
        }
        entity.setDelInd(SysWorkflowApiConstants.DEL_IND_1); // 逻辑删除标识
        return sysWorkflowRepository.save(entity);
    }

    @Override
    public WorkflowEntity findByWorkflowCode(String workflowCode) {
        return sysWorkflowRepository.findByWorkFlowCode(workflowCode);
    }
}


