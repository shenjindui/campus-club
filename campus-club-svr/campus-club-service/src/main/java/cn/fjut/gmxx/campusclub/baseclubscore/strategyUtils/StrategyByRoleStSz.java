package cn.fjut.gmxx.campusclub.baseclubscore.strategyUtils;

import cn.fjut.gmxx.campusclub.baseclubinfo.entity.BaseClubInfoEntity;
import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.baseclubmember.service.IBaseClubMemberService;
import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import cn.fjut.gmxx.campusclub.baseclubscore.repository.BaseClubScoreRepository;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-03-28 20:11
 **/
@Service("StrategyByRoleStSz")
public class StrategyByRoleStSz implements ClubScoreStrategy {
    @Autowired
    private BaseClubScoreRepository baseClubScoreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IBaseClubMemberService baseClubMemberService;

    @Autowired
    IBaseClubInfoService baseClubInfoService;
    @Override
    public Map<String, Object> doSaveSocore(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw ExceptionFactory.getBizException("campus-club-00003", "params");
        }
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
        Map<String, Object> queryParams=new HashMap<>();
        BaseClubInfoEntity baseClubInfoEntity=null;
        if(MapUtils.getString(params,"stCd")==null||MapUtils.getString(params,"stCd")==""){
             baseClubInfoEntity=baseClubInfoService.finBaseClubInfoByStChargeSno(currentUser.getJobNum());
            queryParams.put("stCd",baseClubInfoEntity.getStCd());
        }else{
            queryParams.put("stCd",MapUtils.getString(params,"stCd"));
        }
        //查询社团成员并添加评分信息
        List<Map<String, Object>> memberList=baseClubMemberService.findBaseClubMemberPage(queryParams).getList();
        for(Map<String, Object> map:memberList){
            BaseClubScoreEntity reusltEntity=baseClubScoreRepository.findBySchoolYearAndEmestersAndRatersAssociationCode(MapUtils.getString(params,"schoolYear"),
                    MapUtils.getString(params,"emesters"),MapUtils.getString(map,"memberSno"));
            if(reusltEntity==null){
                BaseClubScoreEntity entity = new BaseClubScoreEntity();
                entity.setCreateTime(new Date());//设置时间
                entity.setCreateUser(currentUser.getLoginName());
                entity.setUpdateTime(new Date());
                entity.setUpdateUser(currentUser.getLoginName());
                entity.setDelInd("0");//设置默认不删除
                entity.setStatusCd(1);//设置为生效
                entity.setVersion(1);
                entity.mapCoverToEntity(params);
                entity.setRatersAssociationCode(MapUtils.getString(map,"memberSno"));
                entity.setRatersAssociationName(MapUtils.getString(map,"memberName"));
                entity.setRatersCode(currentUser.getJobNum());
                entity.setRatersName(currentUser.getLoginName());
                entity.setRatersPsccd("ratersPsccd0");
                entity.setRatersType("ratersType1");
                entity.setStCd(baseClubInfoEntity.getStCd());
                baseClubScoreRepository.save(entity);
            }
        }
        return params;
    }
}
