package cn.fjut.gmxx.campusclub.baseddct.service;

import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
* @类名称 IBaseDdctService
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-01-12
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-01-12 新建
* ----------------------------------------------
*
*/
public interface IBaseDdctService {

	/**
	 * 分页查询数据字典信息
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseDdctPage(Map<String, Object> params);

	/**
	 * 查询数据字典信息（所有）
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findBaseDdctNoPage(Map<String, Object> params);

	/**
	 * 根据参数获取数据字典信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseDdctMap(Map<String, Object> params);

	/**
	 * 保存数据字典
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseDdct(Map<String, Object> params);

	/**
	 * 更新数据字典
	 * @param params
	 * @return
	 */
	Map<String, Object> updateBaseDdct(Map<String, Object> params);

	/**
	 * 删除数据字典
	 * @param params
	 * @return
	 */
	BaseDdctEntity deleteBaseDdct(Map<String, Object> params);

	/**
	 * 根据key获取数据字典列表
	 * @param params
	 * @return
	 */
	List<BaseDdctEntity> getBaseDctListByKey(Map<String, Object> params);

	/**
	 *  根据ddctKey，ddctVal获取数据字典信息
	 * @param ddctKey
	 * @param ddctVal
	 * @return
	 */
	BaseDdctEntity getBaseDdctDetail(String ddctKey,String ddctVal);

	/**
	 * 根据ddctKey，dctTpCd获取数据字典信息
	 * @param ddctKey
	 * @param dctTpCd
	 * @return
	 */
	BaseDdctEntity getBaseDetail(String ddctKey,String dctTpCd);
}

