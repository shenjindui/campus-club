package cn.fjut.gmxx.campusclub.baseddct.service;/**
 * Created by admin on 2020/3/22.
 */

import cn.fjut.gmxx.campusclub.baseddct.mapper.IBaseDdctMapper;
import cn.fjut.gmxx.campusclub.baseddct.repository.BaseDdctRepository;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : shenjindui
 * @date : 2020-03-22 18:05
 **/
@Service("dictionaryService")
public class CacheDictService {
    @Autowired
    private IBaseDdctMapper baseDdctDao;

    @Autowired
    BaseDdctRepository baseDdctRepository;
    /**
     *  类型与字典值
     */
    public static Map<String,Map<String,String>> dictMap = new HashMap<String, Map<String,String>>();
    //public static List<String> codetypeList = new ArrayList<String>();

    public void getCacheDict(){
        List<Map<String,Object>> dictList = baseDdctDao.findBaseDdctList(null);
        //清空缓存数据
        CacheDictService.dictMap.clear();

        Set<String> codetypeSet = new TreeSet<String>();
        for (Map<String, Object> map : dictList) {
            codetypeSet.add(MapUtils.getString(map,"codetype"));
        }

        for (String codetype : codetypeSet) {
            Map<String,String> codeValMap = new HashMap<String,String>();
            for (Map<String, Object> map : dictList) {
                if(codetype.equals(map.get("codetype"))){
                    codeValMap.put(MapUtils.getString(map,"idcode"),MapUtils.getString(map,"idname"));
                }
            }
            dictMap.put(codetype, codeValMap);
        }
        System.out.println("字典数据大小：  "+dictMap.size());
    }

}