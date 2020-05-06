package cn.fjut.gmxx.campusclub.baseddct.service.impl;


import cn.fjut.gmxx.campusclub.baseddct.api.BaseDdctApiConstants;
import cn.fjut.gmxx.campusclub.baseddct.common.DdctUtils;
import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.baseddct.mapper.IBaseDdctMapper;
import cn.fjut.gmxx.campusclub.baseddct.repository.BaseDdctRepository;
import cn.fjut.gmxx.campusclub.baseddct.repository.DictCacheRepository;
import cn.fjut.gmxx.campusclub.baseddct.service.IBaseDdctService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseDdctService
* @类描述 <pre>请填写</pre>
* @作者 sjd V
* @创建时间 2020-01-12
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V sjd 2020-01-12 新建
* ----------------------------------------------
*
*/
@Service("baseDdctService")
public class BaseDdctServiceImpl implements IBaseDdctService{
	
	@Autowired
	private IBaseDdctMapper baseDdctDao;

	@Autowired
	BaseDdctRepository baseDdctRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
	DictCacheRepository dictCacheRepository;

    @Autowired
    DdctUtils ddctUtils;

	@Override
	public PageInfo<Map<String, Object>> findBaseDdctPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		//进行分页参数设置
		Map<String, Object> queryParams= PageHelp.setPageParms(params);
		//查询总数
		queryParams.put("total",baseDdctRepository.count());
		queryParams.put(BaseDdctApiConstants.DEL_IND, BaseDdctApiConstants.DEL_IND_0);
		return new PageInfo<>(baseDdctDao.findBaseDdctList(queryParams),queryParams);
	}

	@Override
	public List<Map<String, Object>> findBaseDdctNoPage(Map<String, Object> params) {
		return baseDdctDao.findBaseDdctListAll(params);
	}

	@Override
	public Map<String, Object> getBaseDdctMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> baseDdctPage = this.findBaseDdctPage(params);
		//判断是否存在数据
		long total = baseDdctPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = baseDdctPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseDdct(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campusclub-00003", "params");
		}

		BaseDdctEntity entity = (BaseDdctEntity) MapTrunPojo.map2Object(params,BaseDdctEntity.class);
        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));

        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.mapCoverToEntity(params);
		BaseDdctEntity result = baseDdctRepository.save(entity);
		params.put(BaseDdctApiConstants.UUID, result.getUuid());

		return params;
	}
	
	@Override
	public Map<String, Object> updateBaseDdct(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String uuid = MapUtils.getString(params, BaseDdctApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campusclub-00002");
		}
		BaseDdctEntity entity = baseDdctRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campusclub-00003", "findOne");
		}
		 //entity = (BaseDdctEntity) MapTrunPojo.map2Object(params,BaseDdctEntity.class);
         entity.mapCoverToEntity(params);

        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        BaseDdctEntity result = baseDdctRepository.save(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}
	
	@Override
	public BaseDdctEntity deleteBaseDdct(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseDdctApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campusclub-00002");
		}
		BaseDdctEntity entity = baseDdctRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campusclub-00003", "findOne");
		}
		if(MapUtils.getString(params,"flag").equals("1")){ //获取标志，生效
			entity.setStatusCd(1);//设置为失效
		}
		if(MapUtils.getString(params,"flag").equals("0")){ //获取标志，失效
			entity.setStatusCd(0);//设置为失效
		}
		//entity.setDelInd(BaseDdctApiConstants.DEL_IND_1); // 逻辑删除标识
		return baseDdctRepository.save(entity);
	}

	@Override
	public List<BaseDdctEntity> getBaseDctListByKey(Map<String, Object> params) {
		return baseDdctRepository.findByDctKey(MapUtils.getString(params,"dctKey"));
	}

	@Override
	public BaseDdctEntity getBaseDdctDetail(String ddctKey, String ddctVal) {
		return baseDdctRepository.findByDctKeyAndDctVal(ddctKey,ddctVal);
	}

	@Override
	public BaseDdctEntity getBaseDetail(String ddctKey, String dctTpCd) {
		BaseDdctEntity baseDdctEntity=dictCacheRepository.findByCode(dctTpCd);
		//先从缓存中获取
		if(baseDdctEntity!=null&&baseDdctEntity.getDctKey().equals(ddctKey)){
           return baseDdctEntity;
		}else{
			//否则从数据库获取
			BaseDdctEntity ddctEntity=baseDdctRepository.findByDctKeyAndDctTpCd(ddctKey,dctTpCd);
			return ddctEntity;
		}
	}

    /*public static void main(String[] args) {
        DdctUtils dctUtils=new DdctUtils();
        String value=dctUtils.getValue("statusCd","statusCd1");
        System.out.print(value);
	}*/
}


