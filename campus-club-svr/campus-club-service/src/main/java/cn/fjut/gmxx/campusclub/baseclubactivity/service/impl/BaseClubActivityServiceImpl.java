package cn.fjut.gmxx.campusclub.baseclubactivity.service.impl;


import cn.fjut.gmxx.campusclub.baseclubactivity.api.BaseClubActivityApiConstants;
import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import cn.fjut.gmxx.campusclub.baseclubactivity.mapper.IBaseClubActivityMapper;
import cn.fjut.gmxx.campusclub.baseclubactivity.repository.BaseClubActivityMapperRepository;
import cn.fjut.gmxx.campusclub.baseclubactivity.service.IBaseClubActivityService;
import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import cn.fjut.gmxx.campusclub.sysbusiness.repository.SysBusinessRepository;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
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
* @类名称 IBaseClubActivityService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-08
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-08 新建
* ----------------------------------------------
*
*/
@Service("baseClubActivityService")
public class BaseClubActivityServiceImpl implements IBaseClubActivityService {
	
	@Autowired
	private IBaseClubActivityMapper baseClubActivityMapper;

	@Autowired
	private BaseClubActivityMapperRepository baseClubActivityMapperRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IBaseClubInfoService baseClubInfoService;

    //系统业务表
    @Autowired
    SysBusinessRepository sysBusinessRepository;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubActivityPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		//进行分页参数设置
		Map<String, Object> queryParams= PageHelp.setPageParms(params);
		MapTrunPojo.mapCopy(params,queryParams);
        //查询总数
        BaseClubActivityEntity entity=new BaseClubActivityEntity();
		entity.mapCoverToEntity(params);
        entity.setDelInd("0");
		//查询匹配器
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubActivityEntity> example = Example.of(entity,matcher);
		queryParams.put("total",baseClubActivityMapperRepository.count(example));
		//
        queryParams.put(BaseClubActivityApiConstants.DEL_IND, BaseClubActivityApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubActivityMapper.findBaseClubActivityList(queryParams),queryParams);
	}

	@Override
	public List<Map<String, Object>> findBaseClubActivityAll(Map<String, Object> params) {
		return baseClubActivityMapper.findBaseClubActivityAll(params);
	}

	@Override
	public Map<String, Object> getBaseClubActivityMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> baseClubActivityPage = this.findBaseClubActivityPage(params);
		long total = baseClubActivityPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = baseClubActivityPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
	@Override
	public Map<String, Object> saveBaseClubActivity(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        BaseClubActivityEntity sysActivityEntity=baseClubActivityMapperRepository
                .findByActivityName(params.get("activityName").toString());
        if(sysActivityEntity!=null){
            throw ExceptionFactory.getBizException(params.get("activityName")+"此活动名称已存在");
        }
		BaseClubActivityEntity entity = new BaseClubActivityEntity();
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");
        entity.setStatusCd(0);
        entity.setVersion(1);
        entity.setActivityScore(null);//添加的时候默认为 默认暂无
        entity.setAssociationAgree("0");//社联是否审批通过 默认否
        entity.setYouthLeagueAgree("0");//团委是否审批通过 默认否
        entity.setProposaAgree("0");//活动策划是否通过  默认否
        entity.mapCoverToEntity(params);
        String maxActivityCode=baseClubActivityMapperRepository.findMaxActivityId();
        String nowActivityCode=null;
        if(maxActivityCode==null){
            nowActivityCode="activity-00001";
        }else{
            nowActivityCode= EncodeUtils.getConteactNo("activity-",Integer.parseInt(maxActivityCode.split("-")[1]));
        }
        entity.setActivityId(nowActivityCode);
        //查找当前用户所主管的社团编号
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("stChargeSno",currentUser.getJobNum());
        Map<String, Object> resultMap = baseClubInfoService.getBaseClubInfoMap(queryParams);
        entity.setHostStCd(MapUtils.getString(resultMap,"stCd"));
        //社团活动考核人
        entity.setActivityAssessor("社联评审考核");
		BaseClubActivityEntity result = baseClubActivityMapperRepository.save(entity);
		//如果保存后不为空，则进行系统业务表插入
		if(result!=null){
            SysBusinessEntity sysBusinessEntity=new SysBusinessEntity();
            sysBusinessEntity.mapCoverToEntity(params);
            String maxBusinessCode=sysBusinessRepository.findMaxBusinessCode();
            String nowBusinessCode=null;
            if(maxBusinessCode==null){
                nowBusinessCode="sysBusiness-00001";
            }else{
                nowBusinessCode= EncodeUtils.getConteactNo("sysBusiness-",Integer.parseInt(maxBusinessCode.split("-")[1]));
            }
            //设置业务关联编号
            sysBusinessEntity.setBusinessAssociationCode(result.getHostStCd());
            //设置业务描述
            sysBusinessEntity.setBusinessDesc(result.getActivityId()+"社团活动创建申请");
            //设置业务编号
            sysBusinessEntity.setBusinessCode(nowBusinessCode);
            //设置业务创建人编号
            sysBusinessEntity.setApproverUserCode(MapUtils.getString(params,"userCode"));
            //设置业务状态 默认初始为 0 未完成
            sysBusinessEntity.setBusinessState("0");
            //设置业务类别 暂未实现默认为 1
            sysBusinessEntity.setBusinessCategory("1");
            SysBusinessEntity resultsyBusinessEntity=sysBusinessRepository.save(sysBusinessEntity);
            //参数返回系统业务业务编号
            params.put("sysBusinessCode",resultsyBusinessEntity.getBusinessCode());
        }
		params.put(BaseClubActivityApiConstants.uuid, result.getUuid());
		return params;
	}

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
	@Override
	public Map<String,Object> updateBaseClubActivity(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubActivityApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
        BaseClubActivityEntity baseClubActivityEntity=baseClubActivityMapperRepository.findByActivityName(
                params.get("activityName").toString());
        if(baseClubActivityEntity!=null&&!(baseClubActivityEntity.getUuid().equals(MapUtils.getString(params,"uuid")))){
            throw ExceptionFactory.getBizException(params.get("menuName")+"此活动名称已存在");
        }
        BaseClubActivityEntity entity = baseClubActivityMapperRepository.findByUuid(uuid);
        entity.mapCoverToEntity(params);
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        BaseClubActivityEntity result = baseClubActivityMapperRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}
	
	@Override
	public BaseClubActivityEntity deleteBaseClubActivity(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubActivityApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
        BaseClubActivityEntity entity = baseClubActivityMapperRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
        }
		entity.setDelInd(BaseClubActivityApiConstants.DEL_IND_1);
        return baseClubActivityMapperRepository.save(entity);
	}
}


