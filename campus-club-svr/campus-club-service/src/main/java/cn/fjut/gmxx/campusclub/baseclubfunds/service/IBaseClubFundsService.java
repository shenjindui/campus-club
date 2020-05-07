package cn.fjut.gmxx.campusclub.baseclubfunds.service;

import cn.fjut.gmxx.campusclub.baseclubfunds.entity.BaseClubFundsEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseClubFundsService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-03-21
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-03-21 新建
* ----------------------------------------------
*
*/
public interface IBaseClubFundsService {

	/**
	 * 分页查询社团财务信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseClubFundsPage(Map<String, Object> params);

	/**
	 * 根据参数获取社团财务信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseClubFundsMap(Map<String, Object> params);

	/**
	 * 保存社团财务信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseClubFunds(Map<String, Object> params);

	/**
	 * 更新社团财务信息
	 * @param params
	 * @return
	 */
	Map<String,Object> updateBaseClubFunds(Map<String, Object> params);

	/**
	 * 删除社团财务信息
	 * @param params
	 * @return
	 */
	BaseClubFundsEntity deleteBaseClubFunds(Map<String, Object> params);

	/**
	 * 查询社团财务列表
	 * @param params
	 * @return
	 */
    List<Map<String, Object>> findBaseClubFunds(Map<String, Object> params);

	/**
	 * 根据orderId保存社团财务信息
	 * @param params
	 * @return
	 */
	Map<String, Object> saveBaseClubFundsByOrderId(Map<String, Object> params);

	/**
	 * 根据参数计算财务信息条数
	 * @param params
	 * @return
	 */
	long findBaseClubCount(Map<String, Object> params);

	/**
	 * 根据参数计算财务信息条数
	 * @param params
	 * @return
	 */
    Double countBaseClubCount(Map<String, Object> params);
}

