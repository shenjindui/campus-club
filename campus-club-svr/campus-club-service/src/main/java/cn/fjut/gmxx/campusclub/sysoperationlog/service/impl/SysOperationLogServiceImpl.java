package cn.fjut.gmxx.campusclub.sysoperationlog.service.impl;


import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysoperationlog.api.SysOperationLogApiConstants;
import cn.fjut.gmxx.campusclub.sysoperationlog.entity.SysOperationLogEntity;
import cn.fjut.gmxx.campusclub.sysoperationlog.mapper.ISysOperationLogMapper;
import cn.fjut.gmxx.campusclub.sysoperationlog.repository.SysOperationLogRepository;
import cn.fjut.gmxx.campusclub.sysoperationlog.service.ISysOperationLogService;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @类名称 ISysOperationLogService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-15
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-15 新建
* ----------------------------------------------
*
*/
@Service("sysOperationLogService")
public class SysOperationLogServiceImpl implements ISysOperationLogService {
	
	@Autowired
	private ISysOperationLogMapper sysOperationLogMapper;
    @Autowired
    private SysOperationLogRepository sysOperationLogRepository;

	@Override
	public PageInfo<Map<String, Object>> findSysOperationLogPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
        //进行分页参数设置
        Map<String, Object> queryParams=new HashMap<>();
        MapTrunPojo.mapCopy(params,queryParams);
        queryParams= PageHelp.setPageParms(params);
        //查询总数
        SysOperationLogEntity entity=new SysOperationLogEntity();
        entity.setDelInd("0");
        //查询匹配器
        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
        Example<SysOperationLogEntity> example = Example.of(entity,matcher);
        queryParams.put("total",sysOperationLogRepository.count(example));
        queryParams.put(SysMenuApiConstants.DEL_IND, SysMenuApiConstants.DEL_IND_0);
        List<Map<String, Object>> list=sysOperationLogMapper.findSysOperationLogList(queryParams);
        return new PageInfo<>(list,queryParams);
	}
	
	@Override
	public Map<String, Object> getSysOperationLogMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> sysOperationLogPage = this.findSysOperationLogPage(params);
		//判断是否存在数据
		long total = sysOperationLogPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = sysOperationLogPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveSysOperationLog(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
		SysOperationLogEntity entity = new SysOperationLogEntity();
		entity.setCreateTime(new Date());//设置时间
		entity.setCreateUser(MapUtils.getString(params,"userCode"));
		entity.setUpdateTime(new Date());
		entity.setUpdateUser(MapUtils.getString(params,"userCode"));
		entity.setDelInd("0");//设置默认不删除
		entity.setStatusCd(1);//设置为生效
		entity.setVersion(1);
        String maxCode=sysOperationLogRepository.findSysOperationLogMaxCode();
        String nowCode=null;
        //如果暂时不存在文件，则设置为默认的file-00001
        if(maxCode==null){
            nowCode="OperationLog-00001";
        }else{
            nowCode= EncodeUtils.getConteactNo("OperationLog-",Integer.parseInt(maxCode.split("-")[1]));
        }
        entity.setOperationCode(nowCode);
		entity.mapCoverToEntity(params);
		SysOperationLogEntity result = sysOperationLogRepository.save(entity);
		params.put(SysOperationLogApiConstants.UUID, result.getUuid());

		return params;
	}

	@Transactional()
    @Override
    public SysOperationLogEntity deleteSysOperation(Map<String, Object> params) {
        String uuid = params.get(SysMenuApiConstants.uuid).toString();
        if (uuid == null) {
            throw ExceptionFactory.getBizException("campus_club-00002");
        }
        SysOperationLogEntity entity = sysOperationLogRepository.findByUuid(uuid);
        if (entity == null) {
            throw ExceptionFactory.getBizException("campus_club-00003", "findOne");
        }
        entity.setDelInd(SysOperationLogApiConstants.DEL_IND_1);
        return sysOperationLogRepository.save(entity);
    }

    @Override
	public void updateSysOperationLog(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String id = MapUtils.getString(params, SysOperationLogApiConstants.UUID);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		SysOperationLogEntity entity = new SysOperationLogEntity();
		entity.setUuid(id);
		entity=	sysOperationLogMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		//MapToEntityUtils.map2Entity(params, entity);

		sysOperationLogRepository.save(entity);
	}
	
	@Override
	public void deleteSysOperationLog(Map<String, Object> params) {
		String id = MapUtils.getString(params, SysOperationLogApiConstants.UUID);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		SysOperationLogEntity entity = new SysOperationLogEntity();
		entity.setUuid(id);
		entity=	sysOperationLogMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		entity.setDelInd(SysOperationLogApiConstants.DEL_IND_1); // 逻辑删除标识
		sysOperationLogRepository.save(entity);
	}
	
	
	
}


