package cn.fjut.gmxx.campusclub.baseclubmember.api;

import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
* @类名称 IBaseClubMemberApi
* @类描述 <pre></pre>
* @作者 shenjindui V1
* @创建时间 2020-02-22
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
public interface IBaseClubMemberApi {

	PageInfo<Map<String, Object>> findBaseClubMemberPage(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubMember(Map<String, Object> params);

	Map<String, Object> getBaseClubMemberMap(Map<String, Object> params);

	Map<String, Object> saveBaseClubMemberTrans(Map<String, Object> params);

	void deleteBaseClubMemberTrans(Map<String, Object> params);

	List<Map<String, Object>> findBaseClubMemberAll(Map<String, Object> params);
}