package cn.fjut.gmxx.campusclub.baseclubmember.service.impl;

import cn.fjut.gmxx.campusclub.baseclubmember.api.BaseClubMemberApiConstants;
import cn.fjut.gmxx.campusclub.baseclubmember.entity.BaseClubMemberEntity;
import cn.fjut.gmxx.campusclub.baseclubmember.mapper.IBaseClubMemberMapper;
import cn.fjut.gmxx.campusclub.baseclubmember.repository.BaseClubMemberRepository;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
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
* @类名称 IBaseClubMemberService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1
* @创建时间 2020-02-22
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
@Service("baseClubMemberService")
public class BaseClubMemberServiceImpl implements IBaseClubMemberService {
	
	@Autowired
	private IBaseClubMemberMapper baseClubMemberMapper;

	@Autowired
	private BaseClubMemberRepository baseClubMemberRepository;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubMemberPage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		if(MapUtils.getString(params,"init")==null){
            queryParams= PageHelp.setPageParms(params);
        }
		BaseClubMemberEntity entity=new BaseClubMemberEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubMemberEntity> example = Example.of(entity,matcher);
		queryParams.put("total",baseClubMemberRepository.count(example));
        queryParams.put(BaseClubMemberApiConstants.DEL_IND, BaseClubMemberApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubMemberMapper.findBaseClubMemberList(queryParams),queryParams);
	}

	@Override
	public List<Map<String, Object>> findBaseClubMemberAll(Map<String, Object> params) {
		return baseClubMemberMapper.findBaseClubMemberListAll(params);
	}

	@Override
	public Map<String, Object> getBaseClubMemberMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> baseClubMemberPage = this.findBaseClubMemberPage(params);
		long total = baseClubMemberPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = baseClubMemberPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseClubMember(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
		BaseClubMemberEntity entity = new BaseClubMemberEntity();
		entity.mapCoverToEntity(params);
		String maxClubMemberCode=baseClubMemberRepository.findMaxMemberCd();
		String nowClubMemberCode=null;
		if(maxClubMemberCode==null){
			nowClubMemberCode="member-00001";
		}else{
			nowClubMemberCode= EncodeUtils.getConteactNo("member-",Integer.parseInt(maxClubMemberCode.split("-")[1]));
		}
		entity.setMemberCd(nowClubMemberCode);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(MapUtils.getString(params,"approverUserCode"));
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(MapUtils.getString(params,"approverUserCode"));
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
		BaseClubMemberEntity result = baseClubMemberRepository.save(entity);
		params.put(BaseClubMemberApiConstants.UUID, result.getUuid());
		return params;
	}
	
	@Override
	public void updateBaseClubMember(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubMemberApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubMemberEntity entity = new BaseClubMemberEntity();
		entity.setUuid(uuid);
		entity=(BaseClubMemberEntity)baseClubMemberMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		//MapToEntityUtils.map2Entity(params, entity);
		baseClubMemberRepository.save(entity);
	}
	
	@Override
	public void deleteBaseClubMember(Map<String, Object> params) {
		String id = MapUtils.getString(params, BaseClubMemberApiConstants.UUID);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
        BaseClubMemberEntity entity = new BaseClubMemberEntity();
        entity.setUuid(id);
        entity=baseClubMemberMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubMemberApiConstants.DEL_IND_1);
		baseClubMemberRepository.save(entity);
	}
}


