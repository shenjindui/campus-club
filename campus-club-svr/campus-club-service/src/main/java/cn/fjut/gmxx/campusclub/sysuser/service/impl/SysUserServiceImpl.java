package cn.fjut.gmxx.campusclub.sysuser.service.impl;

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.api.SysUserApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.mapper.UserMapper;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.sysuser.service.ISysUserService;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MD5Utils;
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
* @类名称 ISysUserService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 1
* @创建时间 2019-12-28
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2019-12-28 新建
* ----------------------------------------------
*
*/
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	UserMapper userMapper;
	@Override
	public SysUserEntity getSysUserByMapWithJpa(Map<String, Object> params) {
		String loginName=MapUtils.getString(params, SysUserApiConstants.LOGIN_NAME);
		String passWord=MapUtils.getString(params,SysUserApiConstants.PASSWORD);
		String md5PpassWord = MD5Utils.stringToMD5(passWord);
        SysUserEntity oneUser = userRepository.findByLoginNameAndPassword(loginName,md5PpassWord);
		//throw ExceptionFactory.getBizException(ExcetionType.SYS00002.getErrorMessage(), "params");
		return oneUser;
	}

	@Override
	public SysUserEntity saveSysUserByMapWithJpa(Map<String, Object> params) {
		SysUserEntity sysUserEntity=(SysUserEntity)MapTrunPojo.map2Object(params,SysUserEntity.class);
		String maxUserNum = userRepository.findSysUserMaxUserCode();//获取最大的用户编码
		if (maxUserNum != null) {
			String[] split = maxUserNum.split("-");
			String userNum = EncodeUtils.getConteactNo("user-", Integer.valueOf(split[1]) + 1);
			sysUserEntity.setUserCode(userNum);
		} else {
			//如果编码为空，那为1
			String userNum = EncodeUtils.getConteactNo("user-", 1);
			sysUserEntity.setUserCode(userNum);
		}
		return userRepository.save(sysUserEntity);
	}

	@Override
	public SysUserEntity forgotSysUserByMapWithJpa(Map<String, Object> params) {
		String loginName=MapUtils.getString(params,SysUserApiConstants.LOGIN_NAME);
		String jobNum=MapUtils.getString(params,SysUserApiConstants.JOB_NUM);
		String mobile=MapUtils.getString(params,SysUserApiConstants.MOBILE);
		return userRepository.findByLoginNameAndJobNumAndMobile(loginName,jobNum,mobile);
	}

	@Override
	public SysUserEntity getSysUserDetailByMapWithJpa(Map<String, Object> params) {
		return userRepository.findByLoginName(MapUtils.getString(params,SysUserApiConstants.LOGIN_NAME));
	}

	@Override
	public Map<String, Object> getSysUserByMapWithMybatis(Map<String, Object> params) {
		List<Map<String, Object>> list=userMapper.findSysUserListAll(params);
		if(list!=null&&list.size()>0){
			return userMapper.findSysUserListAll(params).get(0);
		}
		return null;
	}

	@Override
	public int updateSysUserByMapWithJap(Map<String, Object> params) {
		String loginName=MapUtils.getString(params,SysUserApiConstants.LOGIN_NAME);
		String password=MapUtils.getString(params,SysUserApiConstants.PASSWORD);
		return userRepository.resetSysUserPass(loginName,password);
	}

	@Override
	public PageInfo<Map<String, Object>> findSysUserPage(Map<String, Object> params) {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		Map<String, Object> queryParams= PageHelp.setPageParms(params);
		SysUserEntity entity=new SysUserEntity();
		entity.setDelInd("0");
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<SysUserEntity> example = Example.of(entity,matcher);
		queryParams.put("total",userRepository.count(example));
		queryParams.put(SysMenuApiConstants.DEL_IND, SysMenuApiConstants.DEL_IND_0);
		return new PageInfo<>(userMapper.findSysUserList(queryParams),queryParams);
	}

	@Override
	public Map<String, Object> getSysUserMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> sysMenuPage = this.findSysUserPage(params);
		long total = sysMenuPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = sysMenuPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> saveSysUser(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus_club-00003", "params");
		}

		SysUserEntity entity = (SysUserEntity) MapTrunPojo.map2Object(params,SysUserEntity.class);
		entity.mapCoverToEntity(params);
        //获取当前用户的最大编码
        String maxUserCode=userRepository.findSysUserMaxUserCode();
        String nowUserCode=null;
        if(maxUserCode==null){
            nowUserCode="user-00001";
        }else{
            nowUserCode= EncodeUtils.getConteactNo("user-",Integer.parseInt(maxUserCode.split("-")[1]));
        }
        entity.setUserCode(nowUserCode);
        //MapToEntityUtils.map2Entity(params, entity);
 		SysUserEntity result = userRepository.save(entity);
		params.put(SysUserApiConstants.UUID, result.getUuid());
		params.put(SysUserApiConstants.USER_CODE,result.getUserCode());
		return params;
	}

	@Override
	public Map<String, Object> updateSysUser(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysUserApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus_club-00002");
		}
		SysUserEntity entity = userRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
		}
		entity.mapCoverToEntity(params);
		entity.setUpdateTime(new Date());
		//entity= (SysUserEntity) MapTrunPojo.map2Object(params,SysUserEntity.class);
		//MapToEntityUtils.map2Entity(params, entity);
		SysUserEntity result = userRepository.save(entity);
		params.put(SysMenuApiConstants.uuid, result.getUuid());
		return params;
		//userMapper.update(entity);
	}

	@Override
	public SysUserEntity updateSysUserByJpa(SysUserEntity params) {
		return userRepository.save(params);
	}

	@Override
	public SysUserEntity deleteOrNotSysUser(Map<String, Object> params) {
		String uuid = params.get(SysMenuApiConstants.uuid).toString();
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus_club-00002");
		}
		SysUserEntity entity = userRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
		}
		if(MapUtils.getString(params,"flag").equals("1")){ //获取标志，生效
            entity.setStatusCd(1);//设置为失效
        }
        if(MapUtils.getString(params,"flag").equals("0")){ //获取标志，失效
            entity.setStatusCd(0);//设置为失效
        }
		//entity.setDelInd(SysMenuApiConstants.DEL_IND_1); // 逻辑删除标识
		return userRepository.save(entity);
	}

	@Override
	public SysUserEntity getUserDetail(String userCode) {
		return userMapper.selectByUserCode( userCode);
	}

	@Override
	public int updateSysUserLoginSuccessOrFailCount(String loginName, int flag,String time) {
		if(flag==1){ //登陆成功时
			return  userRepository.sysUserLoginSuucess(loginName);
		}else{
			return userRepository.sysUserLoginFail(loginName);
		}
	}

    @Override
    public long findBaseUserCount(Map<String, Object> params) {
        SysUserEntity entity = new SysUserEntity();
        String userCode= MapUtils.getString(params,"userCode");
        entity.setUserCode(userCode);
        ExampleMatcher matcher=ExampleMatcher.matching()
                .withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<SysUserEntity> example = Example.of(entity,matcher);
        return userRepository.count(example);
    }
}


