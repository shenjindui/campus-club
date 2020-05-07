package cn.fjut.gmxx.campusclub.baseclubscore.strategyUtils;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-03-28 20:10
 **/

public interface  ClubScoreStrategy {

    /**
     * 保存社团得分信息
     * @param params
     * @return
     */
    public Map<String,Object> doSaveSocore(Map<String,Object> params);

}
