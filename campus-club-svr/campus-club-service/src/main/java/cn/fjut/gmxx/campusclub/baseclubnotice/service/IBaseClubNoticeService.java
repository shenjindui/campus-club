package cn.fjut.gmxx.campusclub.baseclubnotice.service;


import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeEntity;
import cn.fjut.gmxx.campusclub.baseclubnotice.entity.BaseClubNoticeVo;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;



/**
* @类名称 IBaseClubNoticeService
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
public interface IBaseClubNoticeService {

	PageInfo<Map<String, Object>> findBaseClubNoticePage(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubNoticeAll(Map<String, Object> params);

	Map<String, Object> getBaseClubNoticeMap(Map<String, Object> params);

	Map<String,Object> saveBaseClubNotice(Map<String, Object> params);

	Map<String,Object> updateBaseClubNotice(Map<String, Object> params);

	BaseClubNoticeEntity deleteBaseClubNotice(Map<String, Object> params);

	long findBaseClubNoticeCount(Map<String, Object> params);

	List<BaseClubNoticeVo> countBaseClubNotice(Map<String, Object> params);

}

