package cn.fjut.gmxx.campusclub.workflow.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.api.SysBusinessApiConstants;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import cn.fjut.gmxx.campusclub.sysbusiness.service.ISysBusinessService;
import cn.fjut.gmxx.campusclub.workflow.api.ISysWorkflowBusinessApi;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowApiConstants;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowBusinessApiConstants;
import cn.fjut.gmxx.campusclub.workflow.api.SysWorkflowLinkApiConstants;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowEntity;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowLinkEntity;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowBusinessService;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowLinkService;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysWorkflowBusinessApi")
public class SysWorkflowBusinessApiImpl implements ISysWorkflowBusinessApi {

	@Autowired
	private ISysWorkflowBusinessService sysWorkflowBusinessService;

	@Autowired
	private ISysWorkflowService sysWorkflowService;

    @Autowired
    private ISysWorkflowLinkService sysWorkflowLinkService;

    @Autowired
    ISysBusinessService sysBusinessService;

    @Autowired
    IBaseClubInfoService baseClubInfoService;

	@Override
	public PageInfo<Map<String, Object>> findSysWorkflowBusinessPage(Map<String, Object> params) {
		PageInfo<Map<String, Object>> page = sysWorkflowBusinessService.findSysWorkflowBusinessPage(params);
		List<Map<String,Object>> pageList=page.getList();
		for (Map<String,Object> map:pageList){
			WorkflowEntity workflowEntity=sysWorkflowService.findByWorkflowCode(MapUtils.getString(map,
					SysWorkflowApiConstants.work_flow_code));
			map.put(SysWorkflowApiConstants.work_flow_name,workflowEntity.getWorkFlowName());

            WorkflowLinkEntity workflowLinkEntity=sysWorkflowLinkService.findByWorkflowCodeAndNodeCode(
            MapUtils.getString(map, SysWorkflowLinkApiConstants.work_flow_code),
                    MapUtils.getString(map, SysWorkflowBusinessApiConstants.work_flow_node_code));
            map.put(SysWorkflowLinkApiConstants.work_flow_link_name,workflowLinkEntity.getWorkFlowLinkName());

            SysBusinessEntity sysBusinessEntity = sysBusinessService.findByBussinessCode(MapUtils.getString(map,SysWorkflowBusinessApiConstants.business_code));
            if(sysBusinessEntity!=null&&sysBusinessEntity.getBusinessCategory().equals("2")){
                map.put("isBaseInfo","yes");
            }
            map.put(SysBusinessApiConstants.business_association_code,sysBusinessEntity.getBusinessAssociationCode());
		}

		return page;
	}

	@Override
	public Map<String, Object> getSysWorkflowBusinessMap(Map<String, Object> params) {
		Map<String, Object> sysWorkflowBusinessMap = sysWorkflowBusinessService.getSysWorkflowBusinessMap(params);
		return sysWorkflowBusinessMap;
	}

	@Override
	public Map<String, Object> saveSysWorkflowBusinessTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, SysWorkflowBusinessApiConstants.uuid);
		//新增
		if (null == uuid) {
			return sysWorkflowBusinessService.saveSysWorkflowBusiness(params);
		} else {
			//修改
            return sysWorkflowBusinessService.updateSysWorkflowBusiness(params);
		}
	}

	@Override
	public void deleteSysWorkflowBusinessTrans(Map<String, Object> params) {
		sysWorkflowBusinessService.deleteSysWorkflowBusiness(params);
	}

	//拒绝
    @Override
    public Map<String, Object> refuseSysWorkflowBusinessTrans(Map<String, Object> params) {
        String uuid = MapUtils.getString(params, SysWorkflowBusinessApiConstants.uuid);
        if(uuid==null){
            throw ExceptionFactory.getBizException("参数异常");
        }
        //更新工作流表数据
        Map<String, Object> resultMap= sysWorkflowBusinessService.updateSysWorkflowBusiness(params);
        if(resultMap==null){
            throw ExceptionFactory.getBizException("操作失败，更新工作流表数据失败");
        }
        //根据BusinessCode获取系统该条系统业务对象
        SysBusinessEntity sysBusinessEntity=sysBusinessService.findByBussinessCode(
                MapUtils.getString(resultMap,SysWorkflowBusinessApiConstants.business_code));
        params.put("businessState","1");//设置系统业务流程结束
        params.put("uuid",sysBusinessEntity.getUuid());//设置该条数据的uuid
        ///resultMap.clear();
        resultMap=sysBusinessService.updateSysBusiness(params);

        //更新社团信息
        if(MapUtils.getString(params,"isBaseInfo")==null){
            Map<String, Object> parm=new HashMap<>();
            BaseClubInfoEntity BaseClubInfoEntity=baseClubInfoService.finBaseClubInfoByStCd(
                    MapUtils.getString(resultMap,"businessAssociationCode"));
            parm.put("uuid",BaseClubInfoEntity.getUuid());
            parm.put("workflowCd","3");
            parm.put("userCode",MapUtils.getString(params,"userCode"));
            parm.put("stCd",BaseClubInfoEntity.getStCd());
            resultMap= baseClubInfoService.updateBaseClubInfo(parm);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> saveToNextSysWorkflowBusinessTrans(Map<String, Object> params) {
            return sysWorkflowBusinessService.saveToNextSysWorkflowBusiness(params);
    }

    @Override
    public Map<String, Object> saveNextSysWorkflowBusinessTrans(Map<String, Object> params) {
        return sysWorkflowBusinessService.saveNextSysWorkflowBusiness(params);
    }

}

