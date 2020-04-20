package cn.fjut.gmxx.campusclub.utlis;/**
 * Created by admin on 2020/2/25.
 */

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;

import java.util.List;

/**
 * @author : shenjindui
 * @date : 2020-02-25 19:35
 **/
public class ListUtils {
public static <T> List<T> getListIndex(List<T> list,int index) {
    if(index-1>list.size()||index<0){
        throw ExceptionFactory.getBizException("参数下表异常报错");
    }
    List<T> newList ;
    if(index-1>=list.size()){
        newList=list;//取前四条数据
    }else{
        newList=list.subList(0,index);//取前四条数据
    }
    return newList;//返回新的list
  }

}
