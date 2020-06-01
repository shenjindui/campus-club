package cn.fjut.gmxx.campusclub.baseclubmember.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.baseclubmember.api.BaseClubMemberApiConstants;
import cn.fjut.gmxx.campusclub.baseclubmember.api.IBaseClubMemberApi;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.baseddct.common.DdctUtils;
import cn.fjut.gmxx.campusclub.basefilersc.service.IBaseFileRscService;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.service.ISysUserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseClubMemberApiImpl")
public class BaseClubMemberApiImpl implements IBaseClubMemberApi {

	@Autowired
	private IBaseClubMemberService baseClubMemberService;

	@Autowired
	private IBaseClubInfoService baseClubInfoService;

	@Autowired
	private IBaseFileRscService baseFileRscService;

	@Autowired
	private DdctUtils dctUtils;

    @Autowired
    private ISysUserService sysUserService;

	@Autowired
	private ExcetionMsg excetionMsg;

	@Override
	public PageInfo<Map<String, Object>> findBaseClubMemberPage(Map<String, Object> params) {
		//根据社团负责人 学号获取社团编号
		PageInfo<Map<String, Object>> page=null;
		//查询社团列表
		PageInfo<Map<String, Object>> pageList=baseClubInfoService.findBaseClubInfoPage(params);
		if(pageList==null||pageList.getList().size()<1){
			return page;
		}
		//查询社团成员 如果是社长的话取第一个可以，若是superadmin则是全部
		Map<String,Object> resultMap=pageList.getList().get(0);
		params.put("stCd",MapUtils.getString(params,"stChargeSno")!=null?
                MapUtils.getString(resultMap,"stCd"):null);
		params.remove("pageSize");
		params.remove("currentPage");
		page = baseClubMemberService.findBaseClubMemberPage(params);
		List<Map<String, Object>> list=page.getList();
		for (Map<String, Object> map:list) {
			Map<String, Object> queryMap=new HashMap<>();
			queryMap.put("filePurpose",4);
            SysUserEntity sysUserEntity = sysUserService.findByJobNum(MapUtils.getString(map,"memberSno"));
			queryMap.put("stCd",sysUserEntity.getUserCode());
			List<Map<String, Object>> resultmap=baseFileRscService.findBaseFileRscPage(queryMap).getList();
			Map<String, Object> fileMap=new HashMap<>();
			if(resultmap!=null&&resultmap.size()>0){
				fileMap=resultmap.get(0);
				/*map.put("fileRte", UrlUtils.getTrueUrlByString(MapUtils.getString(fileMap,"fileRte")));*/
				map.put("fileRte", MapUtils.getString(fileMap,"fileRte"));
			}
		}
		return page;
	}

	@Override
	public List<Map<String, Object>> findBaseClubMember(Map<String, Object> params) {
		return baseClubMemberService.findBaseClubMemberPage(params).getList();
	}

	@Override
	public Map<String, Object> getBaseClubMemberMap(Map<String, Object> params) {
		Map<String, Object> baseClubMemberMap = baseClubMemberService.getBaseClubMemberMap(params);
		return baseClubMemberMap;
	}

	@Override
	public Map<String, Object> saveBaseClubMemberTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubMemberApiConstants.UUID);
		if (null == uuid) {
			return baseClubMemberService.saveBaseClubMember(params);
		} else {
			baseClubMemberService.updateBaseClubMember(params);
		}
		return null;
	}

	@Override
	public void deleteBaseClubMemberTrans(Map<String, Object> params) {
		baseClubMemberService.deleteBaseClubMember(params);
	}

	@Override
	public List<Map<String, Object>> findBaseClubMemberAll(Map<String, Object> params) {
		List<Map<String, Object>> reusltList=baseClubMemberService.findBaseClubMemberAll(params);
		return reusltList;
	}
}

