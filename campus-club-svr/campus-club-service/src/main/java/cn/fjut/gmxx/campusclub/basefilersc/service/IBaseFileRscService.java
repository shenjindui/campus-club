package cn.fjut.gmxx.campusclub.basefilersc.service;


import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseFileRscService
* @类描述 <pre>请填写</pre>
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
public interface IBaseFileRscService {

	PageInfo<Map<String, Object>> findBaseFileRscPage(Map<String, Object> params);

	Map<String, Object> getBaseFileRscMap(Map<String, Object> params);

	Map<String,Object> saveBaseFileRsc(Map<String, Object> params);

	void updateBaseFileRsc(Map<String, Object> params);

	BaseFileRscEntity deleteBaseFileRsc(Map<String, Object> params);

	List<BaseFileRscEntity> fileList();

	BaseFileRscEntity findBaseFileRscEntityByFileId(String fileId);



}

