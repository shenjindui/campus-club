package cn.fjut.gmxx.campusclub.baseddct.common;

import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.baseddct.service.IBaseDdctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * @date : 2020-03-22 21:09
 **/
@Component
public class DdctUtils {
    @Autowired IBaseDdctService baseDdctService;
    public  String getValue(String ddctKey,String dctTpCd){
        return baseDdctService.getBaseDetail(ddctKey,dctTpCd).getDctVal();
    }
    public BaseDdctEntity getDdctEntity(String ddctKey, String dctTpCd){
        return baseDdctService.getBaseDetail(ddctKey,dctTpCd);
    }
}
