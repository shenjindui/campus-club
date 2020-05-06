package cn.fjut.gmxx.campusclub.sysuser.api.impl;

import cn.fjut.gmxx.campusclub.baseclubactivity.api.IBaseClubActivityApi;
import cn.fjut.gmxx.campusclub.baseclubfunds.api.IBaseClubFundsApi;
import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubmember.api.IBaseClubMemberApi;
import cn.fjut.gmxx.campusclub.baseclubmessage.api.IBaseClubMessageApi;
import cn.fjut.gmxx.campusclub.baseclubnews.api.IBaseClubNewsApi;
import cn.fjut.gmxx.campusclub.baseclubnotice.api.IBaseClubNoticeApi;
import cn.fjut.gmxx.campusclub.baseddct.common.DdctUtils;
import cn.fjut.gmxx.campusclub.baseddct.service.IBaseDdctService;
import cn.fjut.gmxx.campusclub.basefilersc.service.IBaseFileRscService;
import cn.fjut.gmxx.campusclub.common.ExcetionType;
import cn.fjut.gmxx.campusclub.common.SysResultConstants;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysbusiness.entity.SysBusinessEntity;
import cn.fjut.gmxx.campusclub.sysbusiness.service.ISysBusinessService;
import cn.fjut.gmxx.campusclub.sysloginlog.service.ISysLoginLogService;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysmenu.service.ISysMenuService;
import cn.fjut.gmxx.campusclub.sysrole.api.SysRoleApiConstants;
import cn.fjut.gmxx.campusclub.sysrole.service.ISysRoleService;
import cn.fjut.gmxx.campusclub.sysuser.api.ISysUserApi;
import cn.fjut.gmxx.campusclub.sysuser.api.SysUserApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.service.ISysUserService;
import cn.fjut.gmxx.campusclub.sysuserrolerel.api.SysUserRoleRelApiConstants;
import cn.fjut.gmxx.campusclub.sysuserrolerel.service.ISysUserRoleRelService;
import cn.fjut.gmxx.campusclub.utlis.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("sysUserApiImpl")
public class SysUserApiImpl implements ISysUserApi {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	ISysRoleService sysRoleService;

	@Autowired
    ISysUserRoleRelService sysUserRoleRelService;

	@Autowired
	ISysMenuService sysMenuService;

	@Autowired
	IBaseDdctService iBaseDdctService;

	@Autowired
	RedisUtils redisUtils;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IBaseFileRscService baseFileRscService;

    @Autowired
    private IBaseDdctService baseDdctService;

    @Autowired
    private ISysBusinessService sysBusinessService;

    @Autowired
    private ISysLoginLogService sysLoginLogService;

    @Autowired
    private IBaseClubInfoApi baseClubInfoApi;

	@Autowired
	DdctUtils dctUtils;

    @Autowired
    private IBaseClubNoticeApi baseClubNoticeApi;

    @Autowired
    private IBaseClubNewsApi baseClubNewsApi;

    @Autowired
    private IBaseClubMemberApi baseClubMemberApi;

    @Autowired
    private IBaseClubFundsApi baseClubFundsApi;

    @Autowired
    private IBaseClubActivityApi baseClubActivityApi;

    @Autowired
    private IBaseClubMessageApi baseClubMessageApi;

    @Autowired
    private ExcetionMsg excetionMsg;


    @Override
	public PageInfo<Map<String, Object>> findSysUserPage(Map<String, Object> params) {
		String value=dctUtils.getValue("statusCd","statusCd1");
		if(params!=null&&params.get("paramsTime")!=null){ //如果时间控件的值不为空
			List<String> paramsTimeList=(List<String>)params.get("paramsTime");
			String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
			String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
			params.put("startTime",startTime);
			params.put("endTime",endTime);
		}
		//是否是工作流中的编号
		String isWorkFlow=MapUtils.getString(params,"isWorkFlow");
		if(isWorkFlow!=null){
            SysBusinessEntity sysBusinessEntity=sysBusinessService.findByBussinessCode(
                    MapUtils.getString(params,"businessCode"));
            params.put("userCode",sysBusinessEntity.getApproverUserCode());
        }

		PageInfo<Map<String, Object>> page = sysUserService.findSysUserPage(params);
        List<Map<String, Object>> pageList = page.getList();
        for(Map<String, Object> map:pageList){
            String sexCd=MapUtils.getString(map,"sexCd");
            if("0".equals(sexCd)){
                map.put("sexName","女");
            }else if("1".equals(sexCd)){
                map.put("sexName","男");
            }
            //查询个人头像信息
            Map<String, Object> queryMap=new HashMap<>();
            queryMap.put("filePurpose","4");//文件用途为4表示为头像
            queryMap.put("stCd",MapUtils.getString(map,"userCode"));//这里的stCd字段存放的为文件所属的对象包括个人和社团
            queryMap.put("createUserId",MapUtils.getString(map,"userCode"));
            PageInfo<Map<String, Object>> fileMaps=baseFileRscService.findBaseFileRscPage(queryMap);
            //前端文件格式转换
            if(fileMaps!=null&&fileMaps.getSize()>0){
                map.put("headPortrait",UrlUtils.getTrueUrl(fileMaps.getList()));
            }
        }
		page.setTotal(sysUserService.findBaseUserCount(params));
		return page;
	}

	/**
	 * 登陆时候获取个人的信息
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> getSysUserMap(Map<String, Object> params) {
		//String msg = excetionMsg.getProperty("sys-00002","3168907225");

        String value=dctUtils.getValue("statusCd","statusCd1");
        HttpServletRequest httpServletRequest=(HttpServletRequest)params.get("request");
		Map<String, Object> resultMap=new HashMap<>();
		SysUserEntity oneUser = sysUserService.getSysUserByMapWithJpa(params);
		//设置下时间格式
		//oneUser.setLastLoginTime();
        String  LastLoginTime=null;
		if(oneUser!=null&&oneUser.getUserCode()!=null){  //如果用户不为空，继续查询他的默认角色
			//更新登陆成功时间new Date()
            Date date=new Date();
            LastLoginTime=new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(date);
            oneUser.setLastLoginTime(new Date());
 			sysUserService.updateSysUserLoginSuccessOrFailCount(oneUser.getUserCode(),1,LastLoginTime);
            oneUser.setLastLoginTime(date);
            //查询个人头像信息
            Map<String, Object> queryMap=new HashMap<>();
            queryMap.put("filePurpose","4");//文件用途为4表示为头像
            queryMap.put("stCd",oneUser.getUserCode());//这里的stCd字段存放的为文件所属的对象包括个人和社团
            queryMap.put("createUserId",oneUser.getUserCode());
            PageInfo<Map<String, Object>> fileMaps=baseFileRscService.findBaseFileRscPage(queryMap);
            //前端文件格式转换
            //resultMap.put("headPortrait",UrlUtils.getTrueUrl(fileMaps.getList()));
			resultMap.put("headPortrait",fileMaps.getList());

			//在Redis中设置Token值 key为userCode ,value
			//根据用户编号和用户名生成Token值
			String token=jwtUtils.createToken(oneUser.getUserCode(),oneUser.getLoginName());

			boolean result=redisUtils.set(oneUser.getUserCode(),token);
			if(result==false){
				throw ExceptionFactory.getBizException("Redis设置异常，请联系管理员");
			}
			//设置Token值
			resultMap.put("token",token);
			//设置地址
            Map<String,Object> addressResult= AddressUtils.getAddressMap(params);
            resultMap.put("addressResult",addressResult);

            resultMap.put("userInfo",oneUser);

			params.clear();//查询的参数
			String defaultRole=MapUtils.getString(params,"defaultRole");
			if(defaultRole==null){//如果角色参数没传，默认查默认角色的
				params.put("defaultRole","1");
			}
			params.put(SysUserApiConstants.USER_CODE,oneUser.getUserCode());//设置用户编码
			params.put(SysUserApiConstants.DEL_IND,"0");//非删除的数据
			params.put(SysUserApiConstants.STATUS_CD,"1");//生效的数据
			//获取该用户默认的角色的信息
			Map<String, Object> userRoleRelMaps=sysUserRoleRelService.getSysUserRoleRelMap(params);
			//如果没有查询到默认角色信息报错
			if(userRoleRelMaps==null||userRoleRelMaps.size()==0){
				// SYSROLE00001("sysrole-00001","默认角色信息不存在，请联系管理员处理");
               throw ExceptionFactory.getBizException(ExcetionType.SYSROLE00001.getErrorMessage(), "params");
			}
			params.clear();
			params.put(SysRoleApiConstants.role_code,userRoleRelMaps.get(SysUserRoleRelApiConstants.role_code));
			params.put(SysRoleApiConstants.DEL_IND,"0");//非删除的数据
			params.put(SysRoleApiConstants.STATUS_CD,"1");//生效的数据
			//获取到该角色的信息
			Map<String, Object> roleMaps=sysRoleService.getSysRoleMap(params);
			resultMap.put("roleInfo",roleMaps);

			//获取该角色的菜单集合
			params.clear();
			//首先获取到第一层菜单
			params.put("init","init");
			params.put(SysMenuApiConstants.DEL_IND,"0");//非删除的数据
			params.put(SysMenuApiConstants.STATUS_CD,"1");//生效的数据
			params.put(SysMenuApiConstants.initRolcode,roleMaps.get(SysRoleApiConstants.role_code));//的数据
			params.put(SysMenuApiConstants.parent_menu_code,"0");//o表示该菜单为一级菜单
			PageInfo<Map<String, Object>> menuMaps=sysMenuService.findSysMenuPage(params);
			List<Map<String, Object>> firstMenuList=menuMaps.getList();
			//获取到第二层菜单
			params.clear();
			params.put(SysMenuApiConstants.DEL_IND,"0");//非删除的数据
			params.put(SysMenuApiConstants.STATUS_CD,"1");//生效的数据
			params.put(SysMenuApiConstants.initRolcode,roleMaps.get(SysRoleApiConstants.role_code));//的数据
			for (int i=0;i<firstMenuList.size();i++){
				Map<String, Object> tempMap=firstMenuList.get(i);
				params.put(SysMenuApiConstants.parent_menu_code,tempMap.get(SysMenuApiConstants.menu_code));//o表示该菜单为一级菜单
				tempMap.put("secondMenuList",sysMenuService.findSysMenuPage(params).getList());
			}
			resultMap.put("menuInfo",firstMenuList);

			//初始化部分字典数据
			params.clear();
			params.put(SysMenuApiConstants.DEL_IND,"0");//非删除的数据
			params.put(SysMenuApiConstants.STATUS_CD,"1");//生效的数据
			params.put("dctKey","statusCd");
			resultMap.put("dctKeyInfo",iBaseDdctService.getBaseDctListByKey(params));

			//操作成功后需求向登陆日志表插入一条记录
            Map<String, Object> saveMap=new HashMap<>();
            saveMap.put("loginName",oneUser.getLoginName());
            saveMap.put("email",oneUser.getEmail());
            saveMap.put("mobile",oneUser.getMobile());
            saveMap.put("realname",oneUser.getRealname());
            saveMap.put("userNum",oneUser.getUserCode());
            saveMap.put("request",httpServletRequest);
            sysLoginLogService.saveSysLoginLog(saveMap);

		}else{ //失败时
			sysUserService.updateSysUserLoginSuccessOrFailCount(params.get("loginName").toString(),0,LastLoginTime);
		}
        //throw  ExceptionFactory.getBizException(msg);
		//throw ExceptionFactory.getBizException(ExcetionType.SYS00002.getErrorMessage(), "params");
		return resultMap;
	}

	@Override
	public Map<String, Object> saveSysUserMap(Map<String, Object> params) {
		if(MapUtils.getString(params,"uuid")!=null){ //当id不为空时，则为更新
			sysUserService.updateSysUser(params);
		}else{
			//否则则为添加用户信息
			if(params.get("statusCd")==null){   //用户状态，注册时默认为生效的
               params.put("statusCd","1");
			}
			if(params.get("delInd")==null){   //用户，注册时默认为0的
				params.put("delInd","0");
			}
			if(params.get("createTime")==null){//创建时间
				params.put("createTime",new Date());
			}
			if(params.get("createUser")==null){
				params.put("createUser",params.get("loginName"));
			}
			if(params.get("updateUser")==null){
				params.put("updateUser",params.get("loginName"));
			}
			if(params.get("updateTime")==null){
				params.put("updateTime",new Date());
			}
            Map<String, Object> map=sysUserService.saveSysUser(params);
			if(map!=null){
                //分配默认角色
                sysUserRoleRelService.saveSysUserRoleRel(params);
            }
		}
		return params;
	}

	@Override
	public Map<String, Object> forgotSysUserMap(Map<String, Object> params) {
		SysUserEntity oneUser = sysUserService.forgotSysUserByMapWithJpa(params);
		params.clear();
		if(oneUser!=null){
			params.put(SysResultConstants.QUERY_RESULT,oneUser);
		}
		return params;
	}

	@Override
	public Map<String, Object> getSysUserDetail(Map<String, Object> params) {
		SysUserEntity oneUser = sysUserService.getSysUserDetailByMapWithJpa(params);
		params.clear();
		if(oneUser!=null){
			params.put(SysResultConstants.QUERY_RESULT,oneUser);
		}
		return params;
	}

	@Override
	public Map<String, Object> getSysUserByOneMap(Map<String, Object> params) {
		return sysUserService.getSysUserByMapWithMybatis(params);
	}

	@Override
	public Map<String, Object> updateSysUserByOneMap(Map<String, Object> params) {
		int result = sysUserService.updateSysUserByMapWithJap(params);
		params.clear();
		params.put(SysResultConstants.QUERY_RESULT,result);
		return params;
	}

	@Override
	public Map<String, Object> deleteSysUserByMap(Map<String, Object> params) {
		SysUserEntity sysMenuEntity=sysUserService.deleteOrNotSysUser(params);
		params.clear();
		params.put("result",sysMenuEntity);
		return params;
	}

    @Override
    public Map<String, Object> getSysUsersMap(Map<String, Object> params) {
        Map<String, Object> sysUserMap = sysUserService.getSysUserMap(params);
        return sysUserMap;
    }

	@Override
	public Map<String, Object> initUserMap(Map<String, Object> params) {
        Map<String, Object> resultMap=new HashMap<>();
	    //获取系统社团列表
        List<Map<String,Object>> clubList=baseClubInfoApi.findBaseClubInfo(null);
        resultMap.put("stList",clubList);
        resultMap.put("stLength",clubList==null?0:clubList.size());
        //获取社团公告数量
        List<Map<String,Object>> clubNoticeList = baseClubNoticeApi.findBaseClubNoticeAll(null);
        resultMap.put("clubNoticeList",clubNoticeList);
        resultMap.put("clubNoticeListLength",clubNoticeList==null?0:clubNoticeList.size());
        //获取社团新闻数量
        List<Map<String,Object>> clubNewsList = baseClubNewsApi.findBaseClubNewsAll(null);
        resultMap.put("clubNewsList",clubNewsList);
        resultMap.put("clubNewsListLength",clubNewsList==null?0:clubNewsList.size());
        // 社长 获取社团社员数量
        Map<String, Object> queryMap=new HashMap<>();
        params.put("stChargeSno",MapUtils.getString(params,"jobNum"));
        Map<String, Object> result = baseClubInfoApi.getBaseClubInfo(params);
        params.remove("stChargeSno");
        queryMap.put("stCd",MapUtils.getString(result,"stCd"));
        List<Map<String,Object>> clubMemberList = baseClubMemberApi.findBaseClubMemberAll(queryMap);
        resultMap.put("clubMemberList",clubMemberList);
        resultMap.put("clubMemberListLength",clubMemberList==null?0:clubMemberList.size());
        //获取社团公告数量
        queryMap.put("noticeStCd",MapUtils.getString(result,"stCd"));
        List<Map<String,Object>> clubNoticeListByStCd = baseClubNoticeApi.findBaseClubNoticeAll(queryMap);
        resultMap.put("clubNoticeListByStCd",clubNoticeListByStCd);
        resultMap.put("clubNoticeListByStCdLength",clubNoticeListByStCd==null?0:clubNoticeListByStCd.size());
        //获取社团新闻数量
        queryMap.put("newsStCd",MapUtils.getString(result,"stCd"));
        List<Map<String,Object>> clubNewsListByStCd = baseClubNewsApi.findBaseClubNewsAll(queryMap);
        resultMap.put("clubNewsListByStCd",clubNewsListByStCd);
        resultMap.put("clubNewsListByStCdLength",clubNewsListByStCd==null?0:clubNewsListByStCd.size());

        //我的代缴费用数量
        queryMap.clear();
        queryMap.put("fundsAssociationCode",MapUtils.getString(params,"jobNum"));
        List<Map<String,Object>> ClubFunds = baseClubFundsApi.findBaseClubFunds(queryMap);
        resultMap.put("clubFunds",ClubFunds);
        resultMap.put("clubFundsLength",ClubFunds==null?0:ClubFunds.size());

        //我的活动 TODO 展示获取为全部
        List<Map<String,Object>> clubActivity = baseClubActivityApi.findBaseClubActivityAll(null);
        resultMap.put("clubActivity",clubActivity);
        resultMap.put("clubActivityLength",clubActivity==null?0:clubActivity.size());

        //留言反馈数量
        queryMap.clear();
        queryMap.put("messageSno",MapUtils.getString(params,"jobNum"));
        List<Map<String,Object>> messageList = baseClubMessageApi.findBaseClubMessageAll(queryMap);
        resultMap.put("messageList",messageList);
        resultMap.put("messageListLength",messageList==null?0:messageList.size());

        //获取社团收支详情
        Map<String, Object> fundsParams = new HashMap<>();
        fundsParams.put("stCd",MapUtils.getString(result,"stCd"));
        Double total = baseClubFundsApi.countBaseClubFunds(fundsParams);
        fundsParams.put("amountType","+");
        Double inCome = baseClubFundsApi.countBaseClubFunds(fundsParams);
        fundsParams.put("amountType","-");
        Double results = baseClubFundsApi.countBaseClubFunds(fundsParams);
        Double outlay = results==null?0:results;
        Double inComePer = convert((inCome/total)*100);
        Double outlayPer =convert((outlay/total)*100);
        Double otherPer  = 100-inComePer-outlayPer;
        List<Double> stClubFundsPer = new ArrayList<>();
        stClubFundsPer.add(inComePer);
        stClubFundsPer.add(outlayPer);
        stClubFundsPer.add(otherPer);
        resultMap.put("stClubFundsPer",stClubFundsPer);
        resultMap.put("stClubFundsPerLength",stClubFundsPer==null?0:stClubFundsPer.size());

        //获取社团新闻访问量
        Map<String, Object> newsParams = new HashMap<>();
        newsParams.put("newsStCd",MapUtils.getString(result,"stCd"));
        newsParams.put("dateFlag","dateFlag");
        List<Map<String, Object>> stNewsList = baseClubNewsApi.countBaseClubNews(newsParams);
        resultMap.put("stNewsList",stNewsList);
        resultMap.put("stNewsListLength",stNewsList==null?0:stNewsList.size());
        //获取社团公告访问量
        Map<String, Object> noticeParams = new HashMap<>();
        noticeParams.put("noticeStCd",MapUtils.getString(result,"stCd"));
        noticeParams.put("dateFlag","dateFlag");
        List<Map<String, Object>> stNoticeList = baseClubNoticeApi.countBaseClubNotice(noticeParams);
        resultMap.put("stNoticeList",stNoticeList);
        resultMap.put("stNoticeListLength",stNoticeList==null?0:stNoticeList.size());

        //代办事项
        Map<String, Object> sysBusinessParams = new HashMap<>();
        sysBusinessParams.put("approverUserCode",MapUtils.getString(params,"userCode"));
        List<Map<String, Object>> sysBusinessList = sysBusinessService.findSysBusinessNoPage(sysBusinessParams);
        resultMap.put("sysBusinessList",sysBusinessList);
        resultMap.put("sysBusinessListLength",sysBusinessList==null?0:sysBusinessList.size());
		return resultMap;
	}

	private static double convert(double value){
        long l1 = Math.round(value*100); //四舍五入
        double ret = l1/100.0; //注意:使用 100.0 而不是 100
        return ret;
    }
}

