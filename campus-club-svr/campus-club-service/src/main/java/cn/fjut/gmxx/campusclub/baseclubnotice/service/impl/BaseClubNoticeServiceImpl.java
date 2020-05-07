package cn.fjut.gmxx.campusclub.baseclubnotice.service.impl;


import cn.fjut.gmxx.campusclub.baseclubfunds.api.BaseClubFundsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubnotice.api.BaseClubNoticeApiConstants;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeVo;
import cn.fjut.gmxx.campusclub.baseclubnotice.mapper.IBaseClubNoticeMapper;
import cn.fjut.gmxx.campusclub.baseclubnotice.repository.BaseClubNoticeRepository;
import cn.fjut.gmxx.campusclub.baseclubnotice.service.IBaseClubNoticeService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
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
* @类名称 IBaseClubNoticeService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-24
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-24 新建
* ----------------------------------------------
*
*/
@Service("baseClubNoticeService")
public class BaseClubNoticeServiceImpl implements IBaseClubNoticeService{
	
	@Autowired
	private IBaseClubNoticeMapper baseClubNoticeMapper;

	@Autowired
	private BaseClubNoticeRepository baseClubNoticeRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubNoticePage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
		BaseClubNoticeEntity entity=new BaseClubNoticeEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubNoticeEntity> example = Example.of(entity,matcher);
		queryParams.put("total",baseClubNoticeRepository.count(example));
		queryParams.put(BaseClubNoticeApiConstants.DEL_IND, BaseClubNoticeApiConstants.DEL_IND_0);
		List<Map<String, Object>> list=baseClubNoticeMapper.findBaseClubNoticeList(queryParams);
		return new PageInfo<>(list,queryParams);
	}

    @Override
    public List<Map<String, Object>> findBaseClubNoticeAll(Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
        BaseClubNoticeEntity entity=new BaseClubNoticeEntity();
        entity.setDelInd("0");
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<BaseClubNoticeEntity> example = Example.of(entity,matcher);
        queryParams.put("total",baseClubNoticeRepository.count(example));
        queryParams.put(BaseClubNoticeApiConstants.DEL_IND, BaseClubNoticeApiConstants.DEL_IND_0);
        List<Map<String, Object>> list=baseClubNoticeMapper.findBaseClubNoticeListAll(queryParams);
        return list;
    }

    @Override
	public Map<String, Object> getBaseClubNoticeMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> baseClubNoticePage = this.findBaseClubNoticePage(params);
		long total = baseClubNoticePage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = baseClubNoticePage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseClubNotice(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}

		BaseClubNoticeEntity entity = new BaseClubNoticeEntity();
		entity.mapCoverToEntity(params);
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
		entity.setNoticeStatus("1");
        entity.setPublisher(currentUser.getUserCode());
        entity.setReleaseTime(new Date());
        entity.setTrafficVolume("0");
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        String maxNoticeCd=baseClubNoticeRepository.findMaxNoticeCd();
        String nowNoticeCd=null;
        //如果暂时不存在，则设置为默认的st-00001
        if(maxNoticeCd==null){
            nowNoticeCd="notice-00001";
        }else{
            nowNoticeCd= EncodeUtils.getConteactNo("notice-",Integer.parseInt(maxNoticeCd.split("-")[1]));
        }
        entity.setNoticeCd(nowNoticeCd);
		BaseClubNoticeEntity result = baseClubNoticeRepository.save(entity);
		params.put(BaseClubNoticeApiConstants.UUID, result.getUuid());
		return params;
	}
	
	@Override
	public Map<String,Object> updateBaseClubNotice(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubNoticeApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubNoticeEntity entity =  baseClubNoticeRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.mapCoverToEntity(params);
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
		//组装保存的entity
		entity.setUpdateTime(new Date());
		entity.setUpdateUser(currentUser.getLoginName());
        BaseClubNoticeEntity result = baseClubNoticeRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}
	
	@Override
	public BaseClubNoticeEntity deleteBaseClubNotice(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, BaseClubNoticeApiConstants.uuid);
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        BaseClubNoticeEntity entity = baseClubNoticeRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
        }
        entity.setDelInd(BaseClubFundsApiConstants.DEL_IND_1);
        return baseClubNoticeRepository.save(entity);
    }

	@Override
	public long findBaseClubNoticeCount(Map<String, Object> params) {
		BaseClubNoticeEntity entity = new BaseClubNoticeEntity();
		if(MapUtils.getString(params,"noticeStCd")!=null){
			entity.setNoticeStCd(MapUtils.getString(params,"noticeStCd"));
		}
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("noticeStCd",
				ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubNoticeEntity> example = Example.of(entity,matcher);
		return baseClubNoticeRepository.count(example);
	}

	@Override
	public List<BaseClubNoticeVo> countBaseClubNotice(Map<String, Object> params) {
		return baseClubNoticeMapper.countBaseClubNotice(params);
	}

}


