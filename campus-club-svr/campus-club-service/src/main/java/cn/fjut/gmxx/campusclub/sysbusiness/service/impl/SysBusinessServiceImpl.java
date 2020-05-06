package cn.fjut.gmxx.campusclub.sysbusiness.service.impl;

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.api.SysBusinessApiConstants;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import cn.fjut.gmxx.campusclub.sysbusiness.mapper.ISysBusinessMapper;
import cn.fjut.gmxx.campusclub.sysbusiness.repository.SysBusinessRepository;
import cn.fjut.gmxx.campusclub.sysbusiness.service.ISysBusinessService;
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
* @类名称 ISysBusinessService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-31
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-31 新建
* ----------------------------------------------
*
*/
@Service("sysBusinessService")
public class SysBusinessServiceImpl implements ISysBusinessService {
	
	@Autowired
	private ISysBusinessMapper sysBusinessMapper;

	@Autowired
	private SysBusinessRepository sysBusinessRepository;

	@Autowired
	UserRepository userRepository;


	@Override
	public PageInfo<Map<String, Object>> findSysBusinessPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
        //进行分页参数设置
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        queryParams= PageHelp.setPageParms(params);
        //查询总数
        SysBusinessEntity entity=new SysBusinessEntity();
        entity.setDelInd("0");
        //查询匹配器
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<SysBusinessEntity> example = Example.of(entity,matcher);
        queryParams.put("total",sysBusinessRepository.count(example));
        queryParams.put(SysBusinessApiConstants.DEL_IND, SysBusinessApiConstants.DEL_IND_0);
		return new PageInfo<>(sysBusinessMapper.findSysBusinessList(queryParams),queryParams);
	}

	@Override
	public List<Map<String, Object>> findSysBusinessNoPage(Map<String, Object> params) {
		return sysBusinessMapper.findSysBusinessAll(params);
	}

	@Override
	public Map<String, Object> getSysBusinessMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> sysBusinessPage = this.findSysBusinessPage(params);
		//判断是否存在数据
		long total = sysBusinessPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = sysBusinessPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysBusiness(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}

		SysBusinessEntity entity = new SysBusinessEntity();
		entity.mapCoverToEntity(params);
        //设置Code
        String maxSysBusinessId=sysBusinessRepository.findMaxBusinessCode();
        String nowSysBusiness=null;
        //如果暂时不存在文件，sysBusiness-00001
        if(maxSysBusinessId==null){
            nowSysBusiness="sysBusiness-00001";
        }else{
            nowSysBusiness= EncodeUtils.getConteactNo("sysBusiness-",Integer.parseInt(maxSysBusinessId.split("-")[1]));
        }
        entity.setBusinessCode(nowSysBusiness);
        entity.setBusinessState("0");
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser("sys_aoto");
        entity.setUpdateTime(new Date());
        entity.setUpdateUser("sys_aoto");
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
		SysBusinessEntity result = sysBusinessRepository.saveAndFlush(entity);
		params.put(SysBusinessApiConstants.uuid, result.getUuid());
        params.put(SysBusinessApiConstants.business_code,nowSysBusiness);
		return params;
	}
	
	@Override
	public Map<String,Object> updateSysBusiness(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String uuid = MapUtils.getString(params, SysBusinessApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("获取数据异常");
		}
		SysBusinessEntity entity = sysBusinessRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("未找到数据，系统异常", "findByUuid");
		}
		entity.mapCoverToEntity(params);
		//查找当前操作用户
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));

		//组装保存的entity
		entity.setUpdateTime(new Date());
		entity.setUpdateUser(currentUser.getLoginName());
		SysBusinessEntity result=sysBusinessRepository.saveAndFlush(entity);
		params.put(SysBusinessApiConstants.uuid, result.getUuid());
		params.put(SysBusinessApiConstants.business_association_code, result.getBusinessAssociationCode());
		return params;
	}
	
	@Override
	public void deleteSysBusiness(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysBusinessApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		SysBusinessEntity entity = new SysBusinessEntity();
		entity.setUuid(uuid);
		entity=sysBusinessMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(SysBusinessApiConstants.DEL_IND_1); // 逻辑删除标识
		sysBusinessRepository.save(entity);
	}

	@Override
	public SysBusinessEntity findByBussinessCode(String bussinessCode) {
		return sysBusinessRepository.findByBusinessCode(bussinessCode);
	}


}


