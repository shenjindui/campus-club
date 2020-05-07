package cn.fjut.gmxx.campusclub.baseclubfunds.service.impl;


import cn.fjut.gmxx.campusclub.baseclubfunds.api.BaseClubFundsApiConstants;
import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import cn.fjut.gmxx.campusclub.baseclubfunds.mapper.IBaseClubFundsMapper;
import cn.fjut.gmxx.campusclub.baseclubfunds.repository.BaseClubFundsRepository;
import cn.fjut.gmxx.campusclub.baseclubfunds.service.IBaseClubFundsService;
import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import cn.fjut.gmxx.campusclub.utlis.RandomUtils;
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
* @类名称 IBaseClubFundsService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-21
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-21 新建
* ----------------------------------------------
*
*/
@Service("baseClubFundsService")
public class BaseClubFundsServiceImpl implements IBaseClubFundsService {

	@Autowired
	private IBaseClubFundsMapper baseClubFundsMapper;

	@Autowired
	private BaseClubFundsRepository baseClubFundsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
	IBaseClubInfoService baseClubInfoService;

    @Autowired
    IBaseClubMemberService baseClubMemberService;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubFundsPage(Map<String, Object> params) {
		//FieldUtils.validateNull(params,"参数params不能为空");
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams=new HashMap<>();
		MapTrunPojo.mapCopy(params,queryParams);
		queryParams= PageHelp.setPageParms(params);
		BaseClubFundsEntity entity=new BaseClubFundsEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubFundsEntity> example = Example.of(entity,matcher);
		queryParams.put("total",baseClubFundsRepository.count(example));
        queryParams.put(BaseClubFundsApiConstants.DEL_IND, BaseClubFundsApiConstants.DEL_IND_0);
		return new PageInfo<>(baseClubFundsMapper.findBaseClubFundsList(queryParams),queryParams);
	}

	@Override
	public Map<String, Object> getBaseClubFundsMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> baseClubFundsPage = this.findBaseClubFundsPage(params);
		long total = baseClubFundsPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = baseClubFundsPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> saveBaseClubFunds(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        BaseClubFundsEntity entity = (BaseClubFundsEntity) MapTrunPojo.map2Object(params,BaseClubFundsEntity.class);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(currentUser.getLoginName());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.mapCoverToEntity(params);
        entity.setFundsAssociationCode(currentUser.getJobNum());
        String maxFundsCode=baseClubFundsRepository.findFundsMaxCd();
        String nowFundsCode=null;
        if(maxFundsCode==null){
            nowFundsCode="funds-00001";
        }else{
            nowFundsCode= EncodeUtils.getConteactNo("funds-",Integer.parseInt(maxFundsCode.split("-")[1]));
        }
        entity.setFundsCd(nowFundsCode);
        entity.setFundsAssociationFundscode("sysauto");//社长新增的时候关联财务编号为系统自动
        entity.setFundsPsccd("1");//新增完成自动标识为完成
		entity.setOrderId(RandomUtils.time());//系统订单号
		entity.setThirdOrderId("sysauto");//社长的第三方交易号为sysauto
		BaseClubFundsEntity result = baseClubFundsRepository.save(entity);
		if(result!=null){
            //处理社团社员的财务数据
            Map<String, Object> queryParams=new HashMap<>();
            if(MapUtils.getString(params,"stCd")==null||MapUtils.getString(params,"stCd")==""){
                BaseClubInfoEntity baseClubInfoEntity=baseClubInfoService.finBaseClubInfoByStCd(currentUser.getJobNum());
                queryParams.put("stCd",baseClubInfoEntity.getStCd());
            }else{
                queryParams.put("stCd",MapUtils.getString(params,"stCd"));
            }
            List<Map<String, Object>> memberList=baseClubMemberService.findBaseClubMemberPage(queryParams).getList();
            for(Map<String, Object> map:memberList){
                BaseClubFundsEntity entity1 = (BaseClubFundsEntity) MapTrunPojo.map2Object(params,BaseClubFundsEntity.class);
                entity1.setCreateTime(new Date());//设置时间
                entity1.setCreateUser(currentUser.getLoginName());
                entity1.setUpdateTime(new Date());
                entity1.setUpdateUser(currentUser.getLoginName());
                entity1.setDelInd("0");//设置默认不删除
                entity1.setStatusCd(1);//设置为生效
                entity1.setVersion(1);
                entity1.mapCoverToEntity(params);
                entity1.setAmountType("-");
                entity1.setFundsAssociationCode(MapUtils.getString(map,"memberSno"));
                String maxFundsCode1=baseClubFundsRepository.findFundsMaxCd();
                String nowFundsCode1=null;
                if(maxFundsCode1==null){
                    nowFundsCode1="funds-00001";
                }else{
                    nowFundsCode1= EncodeUtils.getConteactNo("funds-",Integer.parseInt(maxFundsCode1.split("-")[1]));
                }
                entity1.setFundsCd(nowFundsCode1);
                entity1.setFundsAssociationFundscode(result.getFundsCd());
                entity1.setFundsPsccd("0");//新增完成自动标识为未完成
                entity1.setOrderId(RandomUtils.time());//系统订单号
                entity1.setThirdOrderId(null);//第三方交易号为空
                BaseClubFundsEntity result1 = baseClubFundsRepository.save(entity1);
            }
        }
		return params;
	}

	@Override
	public Map<String,Object> updateBaseClubFunds(Map<String, Object> params) {
		String id = MapUtils.getString(params, BaseClubFundsApiConstants.uuid);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubFundsEntity entity = baseClubFundsRepository.findByUuid(id);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.mapCoverToEntity(params);
        //查找当前操作用户
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        //组装保存的entity
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUser.getLoginName());
        BaseClubFundsEntity result = baseClubFundsRepository.saveAndFlush(entity);
        params.put(SysMenuApiConstants.uuid, result.getUuid());
        return params;
	}

	@Override
	public BaseClubFundsEntity deleteBaseClubFunds(Map<String, Object> params) {
		String id = MapUtils.getString(params, BaseClubFundsApiConstants.uuid);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseClubFundsEntity entity = baseClubFundsRepository.findByUuid(id);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(BaseClubFundsApiConstants.DEL_IND_1);
		return baseClubFundsRepository.save(entity);
	}

	@Override
	public List<Map<String, Object>> findBaseClubFunds(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		return baseClubFundsMapper.findBaseClubFunds(params);
	}

	@Override
	public Map<String, Object> saveBaseClubFundsByOrderId(Map<String, Object> params) {
        BaseClubFundsEntity baseClubFundsEntity=baseClubFundsRepository.findByOrderId(
                MapUtils.getString(params,"orderId"));
        baseClubFundsEntity.setThirdOrderId(MapUtils.getString(params,"thirdOrderId"));//第三方交易号
        baseClubFundsEntity.setFundsPsccd("1");//已支付
        //params.clear();
        params.put("result",baseClubFundsRepository.save(baseClubFundsEntity));
        return params;
	}

	@Override
	public long findBaseClubCount(Map<String, Object> params) {
		//查询匹配器
		BaseClubFundsEntity entity = new BaseClubFundsEntity();
		if(MapUtils.getString(params,"stCd")!=null){
			entity.setStCd(MapUtils.getString(params,"stCd"));
		}
		String fundsAssociationCode= MapUtils.getString(params,"fundsAssociationCode");
		entity.setFundsAssociationCode(fundsAssociationCode);
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("ratersPsccd",
				ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("ratersAssociationCode"
				,ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("ratersType",
				ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("stCd",
				ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseClubFundsEntity> example = Example.of(entity,matcher);
		return baseClubFundsRepository.count(example);
	}

	@Override
	public Double countBaseClubCount(Map<String, Object> params) {
		return baseClubFundsMapper.countBaseClubFunds(params);
	}

}


