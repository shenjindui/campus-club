package cn.fjut.gmxx.campusclub.baseclubinfo.service.impl;


import cn.fjut.gmxx.campusclub.baseclubinfo.api.BaseClubInfoApiConstants;
import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.mapper.IBaseClubInfoMapper;
import cn.fjut.gmxx.campusclub.baseclubinfo.repository.BaseClubInfRepository;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubInfoService
* @类描述 <pre>请填写</pre>
* @作者 v v
* @创建时间 2020-01-18
* @版本 vv
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* v v 2020-01-18 新建
* ----------------------------------------------
*
*/
@Service("baseClubInfoService")
public class BaseClubInfoServiceImpl implements IBaseClubInfoService {
	
	@Autowired
	private IBaseClubInfoMapper baseClubInfoMapper;

	@Autowired
	private BaseClubInfRepository baseClubInfRepository;

	@Autowired
	UserRepository userRepository;

	//系统业务表
	@Autowired
	SysBusinessRepository sysBusinessRepository;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubInfoPage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
		BaseClubInfoEntity entity=new BaseClubInfoEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubInfoEntity> example = Example.of(entity,matcher);
		queryParams.put("total",baseClubInfRepository.count(example));
		queryParams.put(BaseClubInfoApiConstants.DEL_IND, BaseClubInfoApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubInfoMapper.findBaseClubInfoList(queryParams),queryParams);
	}
	
	@Override
	public Map<String, Object> getBaseClubInfoMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> baseClubInfoPage = this.findBaseClubInfoPage(params);
		//判断是否存在数据
		long total = baseClubInfoPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = baseClubInfoPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseClubInfo(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}

		//查找当前操作用户的基本信息
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
		BaseClubInfoEntity entity=(BaseClubInfoEntity)MapTrunPojo.map2Object(params,BaseClubInfoEntity.class);
		entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(0);//设置为生效
        entity.setVersion(1);
		entity.setWorkflowCd(0);//初次添加时为 未提交  审核中 审核通过  审核不通过
		entity.mapCoverToEntity(params);
		//获取当前最大编码
		//设置Id
		String maxStCd=baseClubInfRepository.findMaxStCd();
		String nowStCd=null;
		//如果暂时不存在，则设置为默认的st-00001
		if(maxStCd==null){
			nowStCd="st-00001";
		}else{
			nowStCd= EncodeUtils.getConteactNo("st-",Integer.parseInt(maxStCd.split("-")[1]));
		}
		entity.setStCd(nowStCd);

		//查找是否已经存在此名称的社团
		BaseClubInfoEntity baseClubInfoEntity=baseClubInfRepository.findByStName(params.get("stName").toString());
		if(baseClubInfoEntity!=null){
			throw ExceptionFactory.getBizException(params.get("stName")+"此社团名称已存在");
		}
		BaseClubInfoEntity result = baseClubInfRepository.save(entity);
		//社团申请时进入系统业务表的插入
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
			sysBusinessEntity.setBusinessAssociationCode(nowStCd);
			//设置业务描述
			sysBusinessEntity.setBusinessDesc(MapUtils.getString(params,"remark"));
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
		}else{
			throw ExceptionFactory.getBizException("系统提示:社团创建失败!");
		}

		params.put(BaseClubInfoApiConstants.UUID, result.getUuid());
		params.put(BaseClubInfoApiConstants.ST_CD,result.getStCd());
		params.put(BaseClubInfoApiConstants.ST_FOUNDER,result.getStFounder());
		return params;
	}
	
	@Override
	public Map<String, Object> updateBaseClubInfo(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String uuid = MapUtils.getString(params, BaseClubInfoApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubInfoEntity entity = baseClubInfRepository.findByUuid(uuid);
		entity.mapCoverToEntity(params);
		//查找当前操作用户
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));

		//组装保存的entity
		entity.setUpdateTime(new Date());
		entity.setUpdateUser(currentUser.getLoginName());
		//查找是否已经存在此名称的菜单
		if(MapUtils.getString(params,"stName")!=null||MapUtils.getString(params,"schoolNo")!=null
				||MapUtils.getString(params,"collegeNo")!=null){
			BaseClubInfoEntity sysClubEntity=baseClubInfRepository.findByStNameAndSchoolNoAndCollegeNo(
					params.get("stName").toString(),params.get("schoolNo").toString(),params.get("collegeNo").toString());
			if(sysClubEntity!=null&&!(sysClubEntity.getStCd().equals(params.get("stCd").toString()))){
				throw ExceptionFactory.getBizException("在学校编号["+params.get("schoolNo")+"]下的学院编号["+params.get("collegeNo")+"],"+params.get("stCd")+"此社团名称已被注册！");
			}
		}
		if(MapUtils.getString(params,"isWorkFlow")==null){
			SysBusinessEntity sysBusinessEntity=sysBusinessRepository.findByBusinessAssociationCode(MapUtils.getString(params,"stCd"));
			params.put("sysBusinessCode",sysBusinessEntity.getBusinessCode());
		}
		BaseClubInfoEntity result=baseClubInfRepository.save(entity);
		params.put(SysMenuApiConstants.uuid, result.getUuid());
		return params;
	}
	
	@Override
	public void deleteBaseClubInfo(Map<String, Object> params) {
		String id = MapUtils.getString(params, BaseClubInfoApiConstants.UUID);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubInfoEntity entity = baseClubInfRepository.findByUuid(id);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubInfoApiConstants.DEL_IND_1); // 逻辑删除标识
		baseClubInfRepository.save(entity);
	}

	@Override
	public Map<String, Object> saveBaseClubOpinion(Map<String, Object> params) {
		String id = MapUtils.getString(params, BaseClubInfoApiConstants.UUID);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubInfoEntity entity = baseClubInfRepository.findByUuid(id);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.mapCoverToEntity(params);
		BaseClubInfoEntity result=baseClubInfRepository.save(entity);
		params.put(SysMenuApiConstants.uuid, result.getUuid());
		return params;
	}

	@Override
	public BaseClubInfoEntity finBaseClubInfoByStCd(String stCd) {
		return baseClubInfRepository.findByStCd(stCd);
	}

	@Override
	public BaseClubInfoEntity finBaseClubInfoByStChargeSno(String stChargeSno) {
		return baseClubInfRepository.findByStChargeSno(stChargeSno);
	}

	@Override
	public List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params) {
		return baseClubInfoMapper.findBaseClubInfo(params);
	}


}


