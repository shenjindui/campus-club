package cn.fjut.gmxx.campusclub.baseclubscore.strategyUtils;

import cn.fjut.gmxx.campusclub.baseclubinfo.service.IBaseClubInfoService;
import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import cn.fjut.gmxx.campusclub.baseclubscore.repository.BaseClubScoreRepository;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.MapTrunPojo;
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
@Service("StrategyByRoleStSl")
public class StrategyByRoleStSl implements ClubScoreStrategy {

    @Autowired
    private BaseClubScoreRepository baseClubScoreRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    IBaseClubInfoService baseClubInfoService;
    @Override
    public Map<String, Object> doSaveSocore(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw ExceptionFactory.getBizException("campus-club-00003", "params");
        }
        SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));

        Map<String, Object> queryParams=new HashMap<>();
        //查询所有的社团信息
        List<Map<String, Object> > stList = baseClubInfoService.findBaseClubInfo(null);

        for(Map<String, Object> map:stList){
            BaseClubScoreEntity reusltEntity=baseClubScoreRepository.findBySchoolYearAndEmestersAndRatersAssociationCode(MapUtils.getString(params,"schoolYear"),
                    MapUtils.getString(params,"emesters"),MapUtils.getString(map,"stCd"));
            if(reusltEntity==null){
                BaseClubScoreEntity entity = (BaseClubScoreEntity) MapTrunPojo.map2Object(params,BaseClubScoreEntity.class);
                entity.setCreateTime(new Date());//设置时间
                entity.setCreateUser(currentUser.getLoginName());
                entity.setUpdateTime(new Date());
                entity.setUpdateUser(currentUser.getLoginName());
                entity.setDelInd("0");//设置默认不删除
                entity.setStatusCd(1);//设置为生效
                entity.setVersion(1);
                entity.mapCoverToEntity(params);
                entity.setRatersAssociationCode(MapUtils.getString(map,"stCd"));
                entity.setRatersAssociationName(MapUtils.getString(map,"stName"));
                entity.setRatersCode(currentUser.getJobNum());
                entity.setRatersName(currentUser.getLoginName());
                entity.setRatersPsccd("ratersPsccd0");//进度
                entity.setRatersType("ratersType2");//社联评分社团
                entity.setStCd(MapUtils.getString(map,"stCd"));
                baseClubScoreRepository.save(entity);
            }
        }
        return params;
    }
}
