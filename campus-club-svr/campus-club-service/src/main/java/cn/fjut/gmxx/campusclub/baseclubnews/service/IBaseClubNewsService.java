package cn.fjut.gmxx.campusclub.baseclubnews.service;


import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsEntity;
import cn.fjut.gmxx.campusclub.baseclubnews.entity.BaseClubNewsVo;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseClubNewsService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-24
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-24 新建
* ----------------------------------------------
*
*/
public interface IBaseClubNewsService {

	PageInfo<Map<String, Object>> findBaseClubNewsPage(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubNewsAll(Map<String, Object> params);

	Map<String, Object> getBaseClubNewsMap(Map<String, Object> params);

	Map<String,Object> saveBaseClubNews(Map<String, Object> params);

	Map<String, Object> updateBaseClubNews(Map<String, Object> params);

	BaseClubNewsEntity deleteBaseClubNews(Map<String, Object> params);

	long findBaseClubNewsCount(Map<String, Object> params);

	List<BaseClubNewsVo> countBaseClubNews(Map<String, Object> params);

}

