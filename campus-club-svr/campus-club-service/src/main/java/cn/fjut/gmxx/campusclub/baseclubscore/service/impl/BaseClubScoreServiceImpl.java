package cn.fjut.gmxx.campusclub.baseclubscore.service.impl;


import cn.fjut.gmxx.campusclub.baseclubnews.api.BaseClubNewsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubscore.api.BaseClubScoreApiConstants;
import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import cn.fjut.gmxx.campusclub.baseclubscore.mapper.IBaseClubScoreMapper;
import cn.fjut.gmxx.campusclub.baseclubscore.repository.BaseClubScoreRepository;
import cn.fjut.gmxx.campusclub.baseclubscore.service.IBaseClubScoreService;
import cn.fjut.gmxx.campusclub.baseclubscore.strategyUtils.ClubScoreStrategy;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;
import cn.fjut.gmxx.campusclub.sysrole.repository.RoleRepository;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.sysuserrolerel.entity.SysUserRoleRelEntity;
import cn.fjut.gmxx.campusclub.sysuserrolerel.repository.UserRoleRelRepository;
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
* @类名称 IBaseClubScoreService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-27
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-27 新建
* ----------------------------------------------
*
*/
@Service("baseClubScoreService")
public class BaseClubScoreServiceImpl implements IBaseClubScoreService {
	
	@Autowired
	private IBaseClubScoreMapper baseClubScoreMapper;

	@Autowired
	private BaseClubScoreRepository baseClubScoreRepository;

	@Autowired
	private UserRepository userRepository;

    @Autowired
	private UserRoleRelRepository userRoleRelRepository;

    @Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ExcetionMsg excetionMsg;

    /*@Resource
    @Qualifier("autoTriggerIdExecStrategy")
    ClubScoreContext clubScoreContext;*/
    @Autowired
    private Map<String, ClubScoreStrategy> clubScoreContextMap;

    @Override
	public PageInfo<Map<String, Object>> findBaseClubScorePage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
		BaseClubScoreEntity entity=new BaseClubScoreEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubScoreEntity> example = Example.of(entity,matcher);
		queryParams.put("total",baseClubScoreRepository.count(example));
		queryParams.put(BaseClubNewsApiConstants.DEL_IND, BaseClubNewsApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubScoreMapper.findBaseClubScoreList(params),params);
	}
	
	@Override
	public Map<String, Object> getBaseClubScoreMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> baseClubScorePage = this.findBaseClubScorePage(params);
		long total = baseClubScorePage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = baseClubScorePage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> saveBaseClubScore(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        SysUserRoleRelEntity sysUserRoleRelEntity= userRoleRelRepository.findByUserCodeAndDefaultRole(
                currentUser.getUserCode(),"1");
        SysRoleEntity sysRoleEntity=roleRepository.findByRoleCode(sysUserRoleRelEntity.getRoleCode());
        switch (sysRoleEntity.getRoleCode()){
            case "role-00005":clubScoreContextMap.get("StrategyByRoleStSz").doSaveSocore(params);break;//社团社长操作
            case "role-00002":clubScoreContextMap.get("StrategyByRoleStSl").doSaveSocore(params);break;//社团社联操作
            case "role-00004":clubScoreContextMap.get("StrategyByRoleStTw").doSaveSocore(params);break;//团委操作
        }
        //Set<String> strings = clubScoreContextMap.keySet();
		/*

        //查询社团成员
		BaseClubScoreEntity entity = (BaseClubScoreEntity)MapTrunPojo.map2Object(params,BaseClubScoreEntity.class);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.mapCoverToEntity(params);
        String nowFundsCode=null;
		BaseClubScoreEntity result = baseClubScoreRepository.save(entity);
		params.put(BaseClubScoreApiConstants.uuid, result.getUuid());*/

		return params;
	}
	
	@Override
	public Map<String, Object> updateBaseClubScore(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String uuid = MapUtils.getString(params, BaseClubScoreApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
        BaseClubScoreEntity entity = baseClubScoreRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
        }
        entity.mapCoverToEntity(params);
        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        BaseClubScoreEntity result = baseClubScoreRepository.saveAndFlush(entity);
        params.put(BaseClubScoreApiConstants.uuid, result.getUuid());
        return params;
	}
	
	@Override
	public Map<String, Object> deleteBaseClubScore(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubScoreApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubScoreEntity entity = new BaseClubScoreEntity();
		entity.setUuid(uuid);
		entity=(BaseClubScoreEntity)baseClubScoreMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubScoreApiConstants.DEL_IND_1); // 逻辑删除标识
        baseClubScoreRepository.save(entity);
        return  params;
	}

	@Override
	public long findBaseClubCount(Map<String, Object> params) {
		//查询匹配器
		BaseClubScoreEntity entity = new BaseClubScoreEntity();
        String ratersAssociationCode= MapUtils.getString(params,"ratersAssociationCode");
        String ratersType=MapUtils.getString(params,"ratersType");
        if(ratersAssociationCode!=null){
            entity.setRatersAssociationCode(ratersAssociationCode);
        }
        if(ratersType!=null){
            entity.setRatersType(ratersType);
        }
        if(MapUtils.getString(params,"stCd")!=null){
            entity.setStCd(MapUtils.getString(params,"stCd"));
        }
		entity.setRatersPsccd(MapUtils.getString(params,"ratersPsccd"));
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("ratersPsccd",
				ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("ratersAssociationCode"
        ,ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("ratersType",
                ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("stCd",
                ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubScoreEntity> example = Example.of(entity,matcher);
		return baseClubScoreRepository.count(example);
	}
}


