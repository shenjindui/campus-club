package cn.fjut.gmxx.campusclub.sysloginlog.service.impl;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysloginlog.api.SysLoginLogApiConstants;
import cn.fjut.gmxx.campusclub.sysloginlog.entity.SysLoginLogEntity;
import cn.fjut.gmxx.campusclub.sysloginlog.mapper.ISysLoginLogMapper;
import cn.fjut.gmxx.campusclub.sysloginlog.repository.SysLoginLogRepository;
import cn.fjut.gmxx.campusclub.sysloginlog.service.ISysLoginLogService;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.utlis.AddressUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysLoginLogService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-14
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-14 新建
* ----------------------------------------------
*
*/
@Service("sysLoginLogService")
public class SysLoginLogServiceImpl implements ISysLoginLogService {
	
	@Autowired
	private ISysLoginLogMapper sysLoginLogMapper;

	@Autowired
	private SysLoginLogRepository sysLoginLogRepository;

    @Autowired
    private ExcetionMsg excetionMsg;

	@Override
	public PageInfo<Map<String, Object>> findSysLoginLogPage(Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        queryParams= PageHelp.setPageParms(params);
        SysLoginLogEntity entity=new SysLoginLogEntity();
        entity.setDelInd("0");
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<SysLoginLogEntity> example = Example.of(entity,matcher);
        queryParams.put("total",sysLoginLogRepository.count(example));
        queryParams.put(SysMenuApiConstants.DEL_IND, SysMenuApiConstants.DEL_IND_0);
        List<Map<String, Object>> list=sysLoginLogMapper.findSysLoginLogList(queryParams);
		return new PageInfo<>(list,queryParams);
	}
	
	@Override
	public Map<String, Object> getSysLoginLogMap(Map<String, Object> params) {
		PageInfo<Map<String, Object>> sysLoginLogPage = this.findSysLoginLogPage(params);
		long total = sysLoginLogPage.getTotal();
		if (0 < total) {
			List<Map<String, Object>> list = sysLoginLogPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysLoginLog(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
        HttpServletRequest httpServletRequest=(HttpServletRequest)params.get("request");
        String loginIp = AddressUtils.getIP(httpServletRequest);
        String ua = httpServletRequest.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
		SysLoginLogEntity entity = new SysLoginLogEntity();
        entity.setOsVersion(os.getName());
        entity.setMac(AddressUtils.getMacInWindows(loginIp));
        entity.setBrowserVersion(browser.getVersion(httpServletRequest.getHeader("User-Agent")).getVersion());
        entity.setOsName(os.getName());
        entity.setBrowserName(browser.getName());
        entity.setLoginIp(loginIp);
        entity.setCreateTime(new Date());//设置时间
        entity.setCreateUser(MapUtils.getString(params,"loginName"));
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(MapUtils.getString(params,"loginName"));
        entity.setDelInd("0");//设置默认不删除
        entity.setStatusCd(1);//设置为生效
        entity.setVersion(1);
        entity.mapCoverToEntity(params);
		SysLoginLogEntity result = sysLoginLogRepository.save(entity);
		params.put(SysLoginLogApiConstants.UUID, result.getUuid());
		return params;
	}

    @Override
    public SysLoginLogEntity deleteSysLogin(Map<String, Object> params) {
        String uuid = params.get(SysMenuApiConstants.uuid).toString();
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus_club-00002");
        }
        SysLoginLogEntity entity = sysLoginLogRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
        }
        entity.setDelInd(SysMenuApiConstants.DEL_IND_1);
        return sysLoginLogRepository.save(entity);
    }

    @Override
	public Map<String,Object> updateSysLoginLog(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysLoginLogApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		SysLoginLogEntity entity = new SysLoginLogEntity();
		entity.setUuid(uuid);
		entity=	sysLoginLogMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		sysLoginLogRepository.save(entity);
		return params;
	}
}


