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
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-21 新建
* ----------------------------------------------
*
*/
public interface IBaseFileRscService {

	/**
	 * 分页查询文件列表
	 * @param params
	 * @return
	 */
	PageInfo<Map<String, Object>> findBaseFileRscPage(Map<String, Object> params);

	/**
	 * 根据 参数获取文件信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseFileRscMap(Map<String, Object> params);

	/**
	 * 保存文件信息
	 * @param params
	 * @return
	 */
	Map<String,Object> saveBaseFileRsc(Map<String, Object> params);

	/**
	 * 更新文件信息
	 * @param params
	 */
	void updateBaseFileRsc(Map<String, Object> params);

	/**
	 * 删除文件信息
	 * @param params
	 * @return
	 */
	BaseFileRscEntity deleteBaseFileRsc(Map<String, Object> params);

	/**
	 * 获取文件信息列表
	 * @return
	 */
	List<BaseFileRscEntity> fileList();

	/**
	 * 根据fileId获取文件信息
	 * @param fileId
	 * @return
	 */
	BaseFileRscEntity findBaseFileRscEntityByFileId(String fileId);
}

