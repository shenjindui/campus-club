package cn.fjut.gmxx.campusclub.baseclubnews.service.impl;


import cn.fjut.gmxx.campusclub.baseclubnews.api.BaseClubNewsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.baseclubnews.mapper.IBaseClubNewsMapper;
import cn.fjut.gmxx.campusclub.baseclubnews.repository.BaseClubNewsRepository;
import cn.fjut.gmxx.campusclub.baseclubnews.service.IBaseClubNewsService;
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
* @类名称 IBaseClubNewsService
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
@Service("baseClubNewsService")
public class BaseClubNewsServiceImpl implements IBaseClubNewsService{
	
	@Autowired
	private IBaseClubNewsMapper baseClubNewsMapper;

	@Autowired
	BaseClubNewsRepository baseClubNewsRepository;

    @Autowired
    UserRepository userRepository;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubNewsPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		//进行分页参数设置
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
       //查询总数
        BaseClubNewsEntity entity=new BaseClubNewsEntity();
        entity.setDelInd("0");
        //查询匹配器
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<BaseClubNewsEntity> example = Example.of(entity,matcher);
        queryParams.put("total",baseClubNewsRepository.count(example));
        queryParams.put(BaseClubNewsApiConstants.DEL_IND, BaseClubNewsApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubNewsMapper.findBaseClubNewsList(queryParams),queryParams);
	}

	@Override
	public List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params) {
		return baseClubNewsMapper.findBaseClubNewsAll(params);
	}

	@Override
	public Map<String, Object> getBaseClubNewsMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> baseClubNewsPage = this.findBaseClubNewsPage(params);
		//判断是否存在数据
		long total = baseClubNewsPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = baseClubNewsPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseClubNews(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        BaseClubNewsEntity entity = new BaseClubNewsEntity();
        entity.mapCoverToEntity(params);
        String maxCd=baseClubNewsRepository.findSysNewsMaxCd();
        String nowCd=null;
        if(maxCd==null){
            nowCd="news-00001";
        }else{
            nowCd= EncodeUtils.getConteactNo("news-",Integer.parseInt(maxCd.split("-")[1]));
        }
        entity.setNewsCd(nowCd);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.setNewsStatus("1");//
        entity.setPublisher(currentUser.getUserCode());
        entity.setPublishTime(new Date());
        entity.setTrafficVolume("0");
		BaseClubNewsEntity result = baseClubNewsRepository.save(entity);
		params.put(BaseClubNewsApiConstants.UUID, result.getUuid());
		return params;
	}
	
	@Override
	public Map<String, Object> updateBaseClubNews(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String uuid = MapUtils.getString(params, BaseClubNewsApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubNewsEntity entity = baseClubNewsRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
        entity.mapCoverToEntity(params);
        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));

        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        BaseClubNewsEntity result = baseClubNewsRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}
	
	@Override
	public BaseClubNewsEntity deleteBaseClubNews(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, BaseClubNewsApiConstants.UUID);
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        BaseClubNewsEntity entity = baseClubNewsRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubNewsApiConstants.DEL_IND_1); // 逻辑删除标识
        return baseClubNewsRepository.save(entity);
	}
	
	
	
}


