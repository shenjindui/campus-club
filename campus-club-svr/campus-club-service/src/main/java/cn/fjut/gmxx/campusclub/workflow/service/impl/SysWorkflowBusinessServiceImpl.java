package cn.fjut.gmxx.campusclub.workflow.service.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.repository.BaseClubInfRepository;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import cn.fjut.gmxx.campusclub.sysbusiness.repository.SysBusinessRepository;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApproverApiConstants;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowBusinessApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;
import cn.fjut.gmxx.campusclub.workflow.mapper.ISysWorkflowBusinessMapper;
import cn.fjut.gmxx.campusclub.workflow.repository.SysWorkflowBusinessRepository;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowBusinessService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysWorkflowBusinessService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-01
* @版本 vcampus-club
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* campus-club shenjindui 2020-02-01 新建
* ----------------------------------------------
*
*/
@Service("sysWorkflowBusinessService")
public class SysWorkflowBusinessServiceImpl implements ISysWorkflowBusinessService {
	
	@Autowired
	private ISysWorkflowBusinessMapper sysWorkflowBusinessMapper;

	@Autowired
	private SysWorkflowBusinessRepository sysWorkflowBusinessRepository;

	@Autowired
	BaseClubInfRepository baseClubInfRepository;

	@Autowired
	SysBusinessRepository sysBusinessRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IBaseClubMemberService baseClubMemberService;



	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowBusinessPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		//进行分页参数设置
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
		//查询总数
		WorkflowBusinessEntity entity=new WorkflowBusinessEntity();
		entity.setDelInd("0");
		//查询匹配器
		/*ExampleMatcher matcher=ExampleMatcher.matching().withMatcher(MapUtils.getString(params,"userCode"), ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<WorkflowBusinessEntity> example = Example.of(entity,matcher);*/
		if(MapUtils.getString(params,"pcsStFlag")!=null){
			queryParams.put("total",sysWorkflowBusinessRepository.findByUserCodeAndPcsStCodeList(
					MapUtils.getString(params,"userCode")).size());
		}else{
			queryParams.put("total",sysWorkflowBusinessRepository.findByUserCodeAndPcsStCode(
					MapUtils.getString(params,"userCode"),MapUtils.getString(params,"pcsStCode")).size());
		}
		queryParams.put(SysWorkflowBusinessApiConstants.DEL_IND, SysWorkflowBusinessApiConstants.DEL_IND_0);
		return new PageInfo<>(sysWorkflowBusinessMapper.findSysWorkflowBusinessList(queryParams),queryParams);
	}
	
	@Override
	public Map<String, Object> getSysWorkflowBusinessMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> sysWorkflowBusinessPage = this.findSysWorkflowBusinessPage(params);
		//判断是否存在数据
		long total = sysWorkflowBusinessPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = sysWorkflowBusinessPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysWorkflowBusiness(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("异常提示:[参数不能为空]");
		}

		WorkflowBusinessEntity entity = new WorkflowBusinessEntity();
		entity.mapCoverToEntity(params);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser("sys_aoto");
        entity.setUpdateTime(new Date());
        entity.setUpdateUser("sys_aoto");
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
		entity.setPcsStCode("1");//工作流业务添加时设置为审核中
		//MapToEntityUtils.map2Entity(params, entity);
        String maxBusinessCode=sysWorkflowBusinessRepository.findSysWorkflowBusinessMaxCode();
        entity.setWorkFlowBusinessCode(EncodeUtils.getNowCode(SysWorkflowBusinessApiConstants.WorkflowBusinessPrefixes,
				maxBusinessCode));
		WorkflowBusinessEntity result = sysWorkflowBusinessRepository.save(entity);
		if(result!=null&&MapUtils.getString(params,"businessCode")!=null&&
				(MapUtils.getString(params,"isSt")==null || !MapUtils.getString(params,"isSt").equals("N"))){
			SysBusinessEntity sysBusinessEntity=sysBusinessRepository.findByBusinessCode(MapUtils.getString(params,"businessCode"));
			BaseClubInfoEntity caseClubInfoEntity=baseClubInfRepository.findByStCd(sysBusinessEntity.getBusinessAssociationCode());
			caseClubInfoEntity.setWorkflowCd(1);
			baseClubInfRepository.save(caseClubInfoEntity);
		}
		params.put(SysWorkflowBusinessApiConstants.uuid, result.getUuid());
		return params;
	}

	@Override
	public Map<String, Object> saveToNextSysWorkflowBusiness(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("异常提示:[参数不能为空]");
		}

		//更新上一环节的状态 参数中的uuid就是
		WorkflowBusinessEntity preWorkflowBusinessEntity=sysWorkflowBusinessRepository.findByUuid(MapUtils.getString(params,"uuid"));
		preWorkflowBusinessEntity.setPcsStCode("2");//审核通过
		sysWorkflowBusinessRepository.saveAndFlush(preWorkflowBusinessEntity);
		//去除某些参数
        params.remove("uuid");//去除uuid
        params.remove("approverOpinion");//去除上一环节的建议
		//创建一个新的工作流业务
		WorkflowBusinessEntity entity = new WorkflowBusinessEntity();
		entity.mapCoverToEntity(params);
		entity.setCreateTime(new Date());//设置时间
		entity.setCreateUser("sys_aoto");
		entity.setUpdateTime(new Date());
		entity.setUpdateUser("sys_aoto");
		entity.setDelInd("0");//设置默认不删除
		entity.setStatusCd(1);//设置为生效
		entity.setVersion(1);
		entity.setPcsStCode("1");//工作流业务添加时设置为审核中
		entity.setBusinessCode(preWorkflowBusinessEntity.getBusinessCode());
		String maxBusinessCode=sysWorkflowBusinessRepository.findSysWorkflowBusinessMaxCode();
		entity.setWorkFlowBusinessCode(EncodeUtils.getNowCode(SysWorkflowBusinessApiConstants.WorkflowBusinessPrefixes,
				maxBusinessCode));
		WorkflowBusinessEntity result = sysWorkflowBusinessRepository.save(entity);
		params.put(SysWorkflowBusinessApiConstants.uuid, result.getUuid());
		return params;
	}

	//复核岗审批通过操作
    @Override
    public Map<String, Object> saveNextSysWorkflowBusiness(Map<String, Object> params) {
        // 组装方法要判空
        if (params == null || params.isEmpty()) {
            throw ExceptionFactory.getBizException("异常提示:[参数不能为空]");
        }
        //更新 参数中的uuid
        WorkflowBusinessEntity preWorkflowBusinessEntity=sysWorkflowBusinessRepository.findByUuid(MapUtils.getString(params,"uuid"));
        preWorkflowBusinessEntity.setPcsStCode("2");//审核通过
        preWorkflowBusinessEntity.setSuggestion(MapUtils.getString(params,"approverOpinion"));
        sysWorkflowBusinessRepository.saveAndFlush(preWorkflowBusinessEntity);
        //更新系统业务表
        SysBusinessEntity sysBusinessEntity=sysBusinessRepository.findByBusinessCode(preWorkflowBusinessEntity.getBusinessCode());
        sysBusinessEntity.setBusinessState("1");//业务流程结束
        SysBusinessEntity businessEntity =sysBusinessRepository.saveAndFlush(sysBusinessEntity);

        //如果参数中不存在isBaseInfo则需要进行社团的表更新
		if(MapUtils.getString(params,"isBaseInfo")==null){
			BaseClubInfoEntity baseClubInfoEntity= baseClubInfRepository.findByStCd(businessEntity.getBusinessAssociationCode());
			baseClubInfoEntity.setWorkflowCd(2);//工作流审核通过
			baseClubInfoEntity.setStatusCd(1);//社团生效
			baseClubInfRepository.saveAndFlush(baseClubInfoEntity);
		}else{
            Map<String, Object> saveMaps=new HashMap<>();
            SysUserEntity sysUserEntity=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
            if(sysUserEntity!=null){
                saveMaps.put("memberName",sysUserEntity.getRealname());
                saveMaps.put("memberSno",sysUserEntity.getJobNum());
                saveMaps.put("phone",sysUserEntity.getMobile());
                saveMaps.put("sex",sysUserEntity.getSexCd());
                saveMaps.put("stCd",businessEntity.getBusinessAssociationCode());
                saveMaps.put("approverUserCode",businessEntity.getApproverUserCode());
            }
            baseClubMemberService.saveBaseClubMember(saveMaps);
        }

        return params;
    }

    @Override
	public Map<String,Object> updateSysWorkflowBusiness(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
        String uuid = MapUtils.getString(params, SysWorkflowBusinessApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("参数获取异常"+params);
		}
		WorkflowBusinessEntity entity = sysWorkflowBusinessRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("未找到数据，系统异常", "findByUuid");
		}
        entity.mapCoverToEntity(params);
        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));

        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());

        WorkflowBusinessEntity result=sysWorkflowBusinessRepository.saveAndFlush(entity);
        params.put(SysWorkflowBusinessApiConstants.uuid, result.getUuid());
        params.put(SysWorkflowBusinessApiConstants.business_code, result.getBusinessCode());
        return params;
	}
	
	@Override
	public void deleteSysWorkflowBusiness(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowApproverApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		//WorkflowBusinessEntity entity = sysWorkflowBusinessMapper.findOne(id);
		WorkflowBusinessEntity entity = new WorkflowBusinessEntity();
		entity.setUuid(uuid);
		entity=	sysWorkflowBusinessMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(SysWorkflowBusinessApiConstants.DEL_IND_1); // 逻辑删除标识
		sysWorkflowBusinessRepository.save(entity);
	}

	@Override
	public WorkflowBusinessEntity findBySysBussinessCode(String sysBussinessCode) {
		return sysWorkflowBusinessRepository.findByBusinessCode(sysBussinessCode);
	}


}


