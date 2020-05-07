package cn.fjut.gmxx.campusclub.baseclubscore.api.impl;

import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.baseclubscore.api.BaseClubScoreApiConstants;
import cn.fjut.gmxx.campusclub.baseclubscore.api.IBaseClubScoreApi;
import cn.fjut.gmxx.campusclub.baseclubscore.service.IBaseClubScoreService;
import cn.fjut.gmxx.campusclub.baseddct.common.DdctUtils;
import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.baseddct.service.IBaseDdctService;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysrole.entity.SysRoleEntity;
import cn.fjut.gmxx.campusclub.sysrole.repository.RoleRepository;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.sysuserrolerel.entity.SysUserRoleRelEntity;
import cn.fjut.gmxx.campusclub.sysuserrolerel.repository.UserRoleRelRepository;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("baseClubScoreApi")
public class BaseClubScoreApiImpl implements IBaseClubScoreApi {

	@Autowired
	private IBaseClubScoreService baseClubScoreService;

    @Autowired
    private IBaseClubInfoApi baseClubInfoApi;

    @Autowired
    private IBaseDdctService baseDdctService;

    @Autowired
    private DdctUtils ddctUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRelRepository userRoleRelRepository;

    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  IBaseClubInfoService baseClubInfoService;

    @Override
	public PageInfo<Map<String, Object>> findBaseClubScorePage(Map<String, Object> params) {
        if(MapUtils.getString(params,"ratersTypes")!=null){
            String[] list=MapUtils.getString(params,"ratersTypes").split(",");
            params.put("ratersTypes",list);
        }
	    if(MapUtils.getString(params,"ratersPsccd")!=null&&
                MapUtils.getString(params,"ratersPsccd").equals("0")){
               params.put("ratersPsccd","ratersPsccd0");
        }else{
            params.put("ratersPsccd","ratersPsccd1");
        }
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        BaseClubInfoEntity baseClubInfoEntity=baseClubInfoService.finBaseClubInfoByStChargeSno(currentUser.getJobNum());
        if(baseClubInfoEntity!=null){
            params.put("stCd",baseClubInfoEntity.getStCd());
        }
		PageInfo<Map<String, Object>> page = baseClubScoreService.findBaseClubScorePage(params);
        List<Map<String, Object>> pageList=page.getList();
        for (Map<String, Object> map:pageList) {
            map.put("schoolYearDdct", ddctUtils.getValue("schoolYear",  //学年
                    MapUtils.getString(map,"schoolYear")));
            map.put("semestersDdct", ddctUtils.getValue("semesters",     //学期
                    MapUtils.getString(map,"semesters")));
            map.put("ratersPsccdDdct", ddctUtils.getValue("ratersPsccd",  //状态
                    MapUtils.getString(map,"ratersPsccd")));
            map.put("ratersTypeDdct", ddctUtils.getValue("ratersType",    //类型
                    MapUtils.getString(map,"ratersType")));
        }
        page.setTotal(baseClubScoreService.findBaseClubCount(params));
        page.setList(pageList);
        return page;
	}

	@Override
	public Map<String, Object> getBaseClubScoreMap(Map<String, Object> params) {
		Map<String, Object> baseClubScoreMap = baseClubScoreService.getBaseClubScoreMap(params);
		return baseClubScoreMap;
	}

	@Override
	public Map<String, Object> saveBaseClubScoreTrans(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseClubScoreApiConstants.uuid);
		if (null == uuid) {
			return baseClubScoreService.saveBaseClubScore(params);
		} else {
            return baseClubScoreService.updateBaseClubScore(params);
		}
	}

	@Override
	public Map<String, Object> baseClubScoreInit(Map<String, Object> params) {
        Map<String, Object> resultMap=new HashMap<>();
        //社团列表
        List<Map<String, Object> > stList = baseClubInfoApi.findBaseClubInfo(null);
        resultMap.put("stList",stList);
        //学年列表
        Map<String, Object> queryParams=new HashMap<>();
        queryParams.put("dctKey","schoolYear");
        List<BaseDdctEntity> reusltSchoolYear =  baseDdctService.getBaseDctListByKey(queryParams);
        resultMap.put("schoolYearList",reusltSchoolYear);
        queryParams.clear();
        queryParams.put("dctKey","semesters");
        List<BaseDdctEntity> reusltSemesters=  baseDdctService.getBaseDctListByKey(queryParams);
        resultMap.put("semestersList",reusltSemesters);

        queryParams.clear();
        queryParams.put("dctKey","ratersPsccd");
        List<BaseDdctEntity> reusltRatersPsccd=  baseDdctService.getBaseDctListByKey(queryParams);
        resultMap.put("ratersPsccdList",reusltRatersPsccd);

        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        //根据用户编号查询当前用户的角色信息
        SysUserRoleRelEntity sysUserRoleRelEntity= userRoleRelRepository.findByUserCodeAndDefaultRole(
                currentUser.getUserCode(),"1");
        SysRoleEntity sysRoleEntity=roleRepository.findByRoleCode(sysUserRoleRelEntity.getRoleCode());
        if(sysRoleEntity.getRoleCode().equals("role-00005")){  //社长
            resultMap.put("isShowStList",false);
        }else{
            //resultMap.put("isShowStList",true);
             resultMap.put("isShowStList",false);//这里改造为都不展示社团下拉
        }
        return resultMap;
	}

	@Override
	public void deleteBaseClubScoreTrans(Map<String, Object> params) {
		baseClubScoreService.deleteBaseClubScore(params);
	}

}

