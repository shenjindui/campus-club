package cn.fjut.gmxx.campusclub.baseddct.service;


import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseDdctService
* @类描述 <pre>请填写</pre>
* @作者 sjd V
* @创建时间 2020-01-12
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V sjd 2020-01-12 新建
* ----------------------------------------------
*
*/
public interface IBaseDdctService {

	PageInfo<Map<String, Object>> findBaseDdctPage(Map<String, Object> params);

	Map<String, Object> getBaseDdctMap(Map<String, Object> params);

	Map<String,Object> saveBaseDdct(Map<String, Object> params);

	Map<String, Object> updateBaseDdct(Map<String, Object> params);

	BaseDdctEntity deleteBaseDdct(Map<String, Object> params);

	List<BaseDdctEntity> getBaseDctListByKey(Map<String, Object> params);

	BaseDdctEntity getBaseDdctDetail(String ddctKey,String ddctVal);

	BaseDdctEntity getBaseDetail(String ddctKey,String dctTpCd);

}

