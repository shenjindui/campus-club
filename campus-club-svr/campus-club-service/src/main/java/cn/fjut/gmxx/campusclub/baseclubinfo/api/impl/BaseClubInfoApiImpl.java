package cn.fjut.gmxx.campusclub.baseclubinfo.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.api.BaseClubInfoApiConstants;
import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.baseddct.service.IBaseDdctService;
import cn.fjut.gmxx.campusclub.basefilersc.service.IBaseFileRscService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.service.ISysBusinessService;
import cn.fjut.gmxx.campusclub.utlis.UrlUtils;
import cn.fjut.gmxx.campusclub.workflow.entity.WorkflowBusinessEntity;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowApproverService;
import cn.fjut.gmxx.campusclub.workflow.service.ISysWorkflowBusinessService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseClubInfoApi")
public class BaseClubInfoApiImpl implements IBaseClubInfoApi {

	@Autowired
	private IBaseClubInfoService baseClubInfoService;
	@Autowired
	private IBaseFileRscService baseFileRscService;
    @Autowired
    private IBaseClubMemberService baseClubMemberService;
    @Autowired
    private IBaseDdctService baseDdctService;
    @Autowired
    private ISysWorkflowBusinessService sysWorkflowBusinessService;
    @Autowired
    ISysBusinessService sysBusinessService;
    @Autowired
    ISysWorkflowApproverService sysWorkflowApproverService;


	@Override
	public PageInfo<Map<String, Object>> findBaseClubInfoPage(Map<String, Object> params) {
        // 多客户类型时转换多客户的结果集
        String statusCds = MapUtils.getString(params, "statusCds");
        if (StringUtils.isNotEmpty(statusCds) && StringUtils.indexOf(statusCds, ",") != -1) {
            params.put("statusCds", Arrays.asList(StringUtils.split(statusCds, ",")));
        }
		PageInfo<Map<String, Object>> page = baseClubInfoService.findBaseClubInfoPage(params);
        Map<String, Object> queryMap=new HashMap<>();
        queryMap.put("filePurpose",6);
        Map<String, Object> reqsultMap=baseFileRscService.getBaseFileRscMap(queryMap);
        //前端文件格式转换
        List<Map<String, Object>> list=page.getList();
        if(reqsultMap!=null){
            for (Map<String, Object> map:list){
                map.put("fileRte", UrlUtils.getTrueUrlByString(MapUtils.getString(reqsultMap,"fileRte")));
            }
        }
        //设置人员是否已加入某个社团
        queryMap.clear();
        for (Map<String, Object> map:list){
            queryMap.put("approverUserCode",MapUtils.getString(params,"userCode"));
            queryMap.put("businessAssociationCode",MapUtils.getString(map,"stCd"));
            queryMap.put("businessCategory","2");
            PageInfo<Map<String, Object>>resMaps= sysBusinessService.findSysBusinessPage(queryMap);
            if(resMaps!=null&&resMaps.getList().size()>0){
                Map<String, Object> maps=resMaps.getList().get(0);
                WorkflowBusinessEntity resultEntity=sysWorkflowBusinessService.findBySysBussinessCode(MapUtils.getString(maps,"businessCode"));
                if(resultEntity!=null){
                    map.put("pcsStCode", resultEntity.getPcsStCode());
                }
                map.put("stState", MapUtils.getString(maps,"businessState"));
            }
        }
		return page;
	}

	@Override
	public Map<String, Object> getBaseClubInfoMap(Map<String, Object> params) {
		String isMenhu=MapUtils.getString(params,"menhu");
		if(isMenhu!=null){
			Map<String, Object> baseClubInfoMap = baseClubInfoService.getBaseClubInfoMap(params);
			if(baseClubInfoMap!=null){
               //获取社团简介文件信息
                Map<String, Object> queryMap=new HashMap<>();
                queryMap.put("filePurpose",6);
                queryMap.put("stCd",MapUtils.getString(params,"stCd"));
                Map<String, Object> reqsultMap=baseFileRscService.getBaseFileRscMap(queryMap);
                if(reqsultMap!=null){
                    baseClubInfoMap.put("fileRte", UrlUtils.getTrueUrlByString(MapUtils.getString(reqsultMap,"fileRte")));
                }
                //获取最佳前5社员
                queryMap.clear();
                queryMap.put("stCd",MapUtils.getString(params,"stCd"));
                List<Map<String, Object>> stBestMembers=baseClubMemberService.findBaseClubMemberPage(queryMap).getList();
                if(baseClubInfoMap!=null){
                    baseClubInfoMap.put("stBestMembers",stBestMembers);
                }
            }
            BaseDdctEntity result=baseDdctService.getBaseDdctDetail("schoolCd",
                    MapUtils.getString(baseClubInfoMap,"schoolNo"));
            baseClubInfoMap.put("schoolName",result.getDctTpNm());
            BaseDdctEntity result2=baseDdctService.getBaseDdctDetail("collegeCd",
                    MapUtils.getString(baseClubInfoMap,"collegeNo"));
            baseClubInfoMap.put("collegeName",result2.getDctTpNm());
			return baseClubInfoMap;
		}else{
			Map<String, Object> baseClubInfoMap = baseClubInfoService.getBaseClubInfoMap(params);
            BaseDdctEntity result=baseDdctService.getBaseDdctDetail("schoolCd",
                    MapUtils.getString(baseClubInfoMap,"schoolNo"));
            baseClubInfoMap.put("schoolName",result.getDctTpNm());
            BaseDdctEntity result2=baseDdctService.getBaseDdctDetail("collegeCd",
                    MapUtils.getString(baseClubInfoMap,"collegeNo"));
            baseClubInfoMap.put("collegeName",result2.getDctTpNm());
			return baseClubInfoMap;
		}

	}

	@Override
	public Map<String, Object> saveBaseClubInfoTrans(Map<String, Object> params) {
		String id = MapUtils.getString(params, BaseClubInfoApiConstants.UUID);
		//新增
		if (null == id) {
			return baseClubInfoService.saveBaseClubInfo(params);
		} else {
			//修改
			return baseClubInfoService.updateBaseClubInfo(params);
		}
	}

	@Override
	public Map<String, Object> saveBaseClubOpinionTrans(Map<String, Object> params) {
		return baseClubInfoService.saveBaseClubOpinion(params);
	}

	@Override
	public void deleteBaseClubInfoTrans(Map<String, Object> params) {
		baseClubInfoService.deleteBaseClubInfo(params);
	}

    @Override
    public Map<String, Object> joinBaseClubInfoMap(Map<String, Object> params) {
	    //系统业务保存
        Map<String, Object>  saveMap=new HashMap<>();
        saveMap.put("approverUserCode",MapUtils.getString(params,"userCode"));
        saveMap.put("businessAssociationCode",MapUtils.getString(params,"stCd"));
        saveMap.put("businessCategory","2");
        saveMap.put("businessDesc","人员申请加入社团");
        Map<String, Object>  resultMap=sysBusinessService.saveSysBusiness(saveMap);
	    //工作流设置
        params.put("workFlowCode","workflow-00002");
        Map<String, Object>  queryMap=new HashMap<>();
        queryMap.put("workFlowCode","workflow-00002");
        Map<String, Object>  approverMap=null;
        PageInfo<Map<String, Object>> pageInfo=sysWorkflowApproverService.findSysWorkflowApproverPage(queryMap);
        if(pageInfo!=null&&pageInfo.getSize()>0){
            approverMap=pageInfo.getList().get(0);
        }
        params.put("approverCode",MapUtils.getString(approverMap,"workFlowApproverCode"));
        params.put("workFlowNodeCode",MapUtils.getString(approverMap,"workFlowNodeCode"));
        params.put("userCode",MapUtils.getString(approverMap,"userCode"));
        params.put("businessCode",MapUtils.getString(resultMap,"businessCode"));
        params.put("isSt","N");//是否社团添加

        Map<String, Object> resMap=sysWorkflowBusinessService.saveSysWorkflowBusiness(params);
        return resMap;
    }

    @Override
    public List<Map<String, Object>> findBaseClubInfo(Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        return baseClubInfoService.findBaseClubInfo(params);
    }

}
