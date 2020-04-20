package cn.fjut.gmxx.campusclub.baseclubactivity.service.impl;


import cn.fjut.gmxx.campusclub.baseclubactivity.api.BaseClubActivityApiConstants;
import cn.fjut.gmxx.campusclub.baseclubactivity.entity.BaseClubActivityEntity;
import cn.fjut.gmxx.campusclub.baseclubactivity.mapper.IBaseClubActivityMapper;
import cn.fjut.gmxx.campusclub.baseclubactivity.repository.BaseClubActivityMapperRepository;
import cn.fjut.gmxx.campusclub.baseclubactivity.service.IBaseClubActivityService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
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
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> baseClubActivityPage = this.findBaseClubActivityPage(params);
		//判断是否存在数据
		long total = baseClubActivityPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = baseClubActivityPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseClubActivity(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}

		BaseClubActivityEntity entity = new BaseClubActivityEntity();
		//MapToEntityUtils.map2Entity(params, entity);

		BaseClubActivityEntity result = baseClubActivityMapperRepository.save(entity);
		params.put(BaseClubActivityApiConstants.uuid, result.getUuid());

		return params;
	}
	
	@Override
	public void updateBaseClubActivity(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		Integer id = MapUtils.getInteger(params, BaseClubActivityApiConstants.uuid);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		//BaseClubActivityEntity entity = baseClubActivityDao.findOne(id);
		/*if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}*/
		//MapToEntityUtils.map2Entity(params, entity);

		//baseClubActivityMapper.update(entity);
	}
	
	@Override
	public void deleteBaseClubActivity(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubActivityApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		/*BaseClubActivityEntity entity =new BaseClubActivityEntity();
		entity.setUuid(uuid);*/
		BaseClubActivityEntity entity = /*baseClubActivityMapper.selectById(entity)*/null;
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubActivityApiConstants.DEL_IND_1); // 逻辑删除标识
		///baseClubActivityMapper.updateByExample(entity);
	}
	
	
	
}


