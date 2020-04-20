package cn.fjut.gmxx.campusclub.basefilersc.api;


import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.Map;

/**
* @类名称 IBaseFileRscApi
* @类描述 <pre></pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-21
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-21 新建
* ----------------------------------------------
*
*/
public interface IBaseFileRscApi {

	PageInfo<Map<String, Object>> findBaseFileRscPage(Map<String, Object> params);

	Map<String, Object> getBaseFileRscMap(Map<String, Object> params);

	Map<String, Object> saveBaseFileRscTrans(Map<String, Object> params);


	Map<String, Object> deleteBaseFileRscTrans(Map<String, Object> params);
}