package cn.fjut.gmxx.campusclub.baseclubnews.api;

/**
* @类名称 BaseClubNewsApiConstants
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
public interface BaseClubNewsApiConstants {

	String NEWS_CD = "newsCd";
	String NEWS_TITLE = "newsTitle";
	String NEWS_DESC = "newsDesc";
	String PUBLISHER = "publisher";
	String PUBLISH_TIME = "publishTime";
	String NEWS_STATUS = "newsStatus";
	String TRAFFIC_VOLUME = "trafficVolume";
	String NEWS_ST_CD = "newsStCd";
	String REMARK = "remark";
	String UUID = "uuid";
	String ID = "id";
	/**
	* 删除标识
	*/
	String DEL_IND = "delInd";
	/**
	* 租户ID
	*/
	String TENANT_ID = "tenantId";
	/**
	* 客户端ID
	*/
	String CLNTEND_ID = "clntendId";

	/**
	* 删除标志：0：已删除
	*/
	String DEL_IND_0 = "0";
	/**
	* 删除标志：1：未删除
	*/
	String DEL_IND_1 = "1";
}


