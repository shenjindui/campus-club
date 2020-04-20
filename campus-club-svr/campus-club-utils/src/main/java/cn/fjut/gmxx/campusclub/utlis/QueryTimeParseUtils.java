package cn.fjut.gmxx.campusclub.utlis;
import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-04-01 21:16
 **/
//主要用于查询时传入时间的处理
public class QueryTimeParseUtils {
    public static Map<String,Object> parseQueryTime(Map<String,Object> params){
        if(params!=null&&MapUtils.getString(params,"paramsTime")!=null){ //如果时间控件的值不为空 ,则处理时间
            List<String> paramsTimeList=(List<String>)params.get("paramsTime");
            String startTime= DateUtils.dealDateFormats(paramsTimeList.get(0));
            String endTime=DateUtils.dealDateFormats(paramsTimeList.get(1));
            params.put("startsTime",startTime);
            params.put("endsTime",endTime);
        }
        return params;
    }
}
