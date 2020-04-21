package cn.fjut.gmxx.campusclub.pagehelper;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-01-12 14:30
 **/
public class PageHelp {

    public static Map<String,Object> setPageParms(Map<String,Object> params){
        //分页准备数据
        Integer currentPage = 1;//默认数据
        Integer pageSize = 5; //默认页面大小
        if(params.get("pageSize")!=null){
            params.put("pageSize", MapUtils.getInteger(params,"pageSize"));
        }else{
            params.put("pageSize", pageSize);
        }
        if(params.get("currentPage")!=null){
            params.put("startIndex",(MapUtils.getInteger(params,"currentPage")-1)*
                    MapUtils.getInteger(params,"pageSize"));
        }else{
            params.put("startIndex",(currentPage-1)*MapUtils.getInteger(params,"pageSize"));
            params.put("currentPage",currentPage);
        }
        return params;
    }
}
