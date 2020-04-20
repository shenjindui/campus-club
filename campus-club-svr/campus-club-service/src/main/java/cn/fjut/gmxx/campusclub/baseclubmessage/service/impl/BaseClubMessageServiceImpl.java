package cn.fjut.gmxx.campusclub.baseclubmessage.service.impl;


import cn.fjut.gmxx.campusclub.baseclubmessage.api.BaseClubMessageApiConstants;
import cn.fjut.gmxx.campusclub.baseclubmessage.entity.BaseClubMessageEntity;
import cn.fjut.gmxx.campusclub.baseclubmessage.mapper.IBaseClubMessageMapper;
import cn.fjut.gmxx.campusclub.baseclubmessage.repository.BaseClubMessageRepository;
import cn.fjut.gmxx.campusclub.baseclubmessage.service.IBaseClubMessageService;
import cn.fjut.gmxx.campusclub.baseclubnews.api.BaseClubNewsApiConstants;
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
* @类名称 IBaseClubMessageService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V10
* @创建时间 2020-03-26
* @版本 vV10
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V10 shenjindui 2020-03-26 新建
* ----------------------------------------------
*
*/
@Service("baseClubMessageService")
public class BaseClubMessageServiceImpl implements IBaseClubMessageService {
	
	@Autowired
	private IBaseClubMessageMapper baseClubMessageMapper;

	@Autowired
	private BaseClubMessageRepository baseClubMessageRepository;

    @Autowired
    UserRepository userRepository;
	@Override
	public PageInfo<Map<String, Object>> findBaseClubMessagePage(Map<String, Object> params) {
        // 判断当前参数params是否为空，则为默认查询
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        //进行分页参数设置
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        queryParams= PageHelp.setPageParms(params);
        //查询总数
        BaseClubMessageEntity entity=new BaseClubMessageEntity();
        entity.setDelInd("0");
        //查询匹配器
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<BaseClubMessageEntity> example = Example.of(entity,matcher);
        queryParams.put("total",baseClubMessageRepository.count(example));
        queryParams.put(BaseClubNewsApiConstants.DEL_IND, BaseClubNewsApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubMessageMapper.findBaseClubMessageList(queryParams),queryParams);
	}

    @Override
    public List<Map<String, Object>> findBaseClubMessageAll(Map<String, Object> params) {
        return baseClubMessageMapper.findBaseClubMessageAll(params);
    }

    @Override
	public Map<String, Object> getBaseClubMessageMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> baseClubMessagePage = this.findBaseClubMessagePage(params);
		//判断是否存在数据
		long total = baseClubMessagePage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = baseClubMessagePage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseClubMessage(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
		BaseClubMessageEntity entity = new BaseClubMessageEntity();
        entity.mapCoverToEntity(params);
        entity.setMessagSno(currentUser.getJobNum());
        entity.setMessageName(currentUser.getLoginName());
        String maxCd=baseClubMessageRepository.findMaxMessageCd();
        String nowCd=null;
        if(maxCd==null){
            nowCd="messages-00001";
        }else{
            nowCd= EncodeUtils.getConteactNo("messages-",Integer.parseInt(maxCd.split("-")[1]));
        }
        entity.setMessageCd(nowCd);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.setRemark("sysauto");
        entity.setMessagePsccd("0");//保存的时候状态为 处理中 提交中
		BaseClubMessageEntity result = baseClubMessageRepository.save(entity);
		params.put(BaseClubMessageApiConstants.uuid, result.getUuid());
		return params;
	}
	
	@Override
	public Map<String,Object> updateBaseClubMessage(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
        String uuid = MapUtils.getString(params, BaseClubMessageApiConstants.uuid);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubMessageEntity entity = baseClubMessageRepository.findByUuid(uuid);
        //查找当前操作用户
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        entity.mapCoverToEntity(params);
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setMessagePscCode(currentUser.getUserCode());
        entity.setMessagePscName(currentUser.getLoginName());
        BaseClubMessageEntity result = baseClubMessageRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}
	
	@Override
	public BaseClubMessageEntity deleteBaseClubMessage(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, BaseClubMessageApiConstants.uuid);
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus-club-00002");
        }
        BaseClubMessageEntity entity = baseClubMessageRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubMessageApiConstants.DEL_IND_1); // 逻辑删除标识
        return baseClubMessageRepository.save(entity);
	}
	
	
	
}


