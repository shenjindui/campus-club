package cn.fjut.gmxx.campusclub.baseclubscore.strategyUtils;/**
 * Created by admin on 2020/3/28.
 */

import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-03-28 20:10
 **/

public interface  ClubScoreStrategy {

    public Map<String,Object> doSaveSocore(Map<String,Object> params);

}
