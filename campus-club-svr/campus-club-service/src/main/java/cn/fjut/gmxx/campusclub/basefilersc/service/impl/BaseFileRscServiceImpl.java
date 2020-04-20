package cn.fjut.gmxx.campusclub.basefilersc.service.impl;


import cn.fjut.gmxx.campusclub.basefilersc.api.BaseFileRscApiConstants;
import cn.fjut.gmxx.campusclub.basefilersc.entity.BaseFileRscEntity;
import cn.fjut.gmxx.campusclub.basefilersc.mapper.IBaseFileRscMapper;
import cn.fjut.gmxx.campusclub.basefilersc.repository.FileSrcRepository;
import cn.fjut.gmxx.campusclub.basefilersc.service.IBaseFileRscService;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageHelp;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.SysMenuApiConstants;
import cn.fjut.gmxx.campusclub.sysuser.entity.SysUserEntity;
import cn.fjut.gmxx.campusclub.sysuser.repository.UserRepository;
import cn.fjut.gmxx.campusclub.utlis.EncodeUtils;
import cn.fjut.gmxx.campusclub.utlis.UrlUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
@Service("baseFileRscService")
public class BaseFileRscServiceImpl implements IBaseFileRscService {
	protected final static Logger logger = LoggerFactory.getLogger(BaseFileRscServiceImpl.class);
	@Autowired
	private IBaseFileRscMapper baseFileRscMapper;

	@Autowired
	FileSrcRepository fileSrcRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public PageInfo<Map<String, Object>> findBaseFileRscPage(Map<String, Object> params) {
		// 判断当前参数params是否为空，则为默认查询
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		//进行分页参数设置
		Map<String, Object> queryParams= PageHelp.setPageParms(params);
		//查询总数
		BaseFileRscEntity entity=new BaseFileRscEntity();
		entity.setDelInd("0");
		//查询匹配器
		ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("statusCd").withIgnorePaths("version");
		Example<BaseFileRscEntity> example = Example.of(entity,matcher);
		queryParams.put("total",fileSrcRepository.count(example));
		queryParams.put(SysMenuApiConstants.DEL_IND, SysMenuApiConstants.DEL_IND_0);
		return new PageInfo<>(baseFileRscMapper.findBaseFileRscList(queryParams),queryParams);
	}
	
	@Override
	public Map<String, Object> getBaseFileRscMap(Map<String, Object> params) {
		//默认调用分页查询方法。
		PageInfo<Map<String, Object>> baseFileRscPage = this.findBaseFileRscPage(params);
		//判断是否存在数据
		long total = baseFileRscPage.getTotal();
		if (0 < total) {
			//获取查询结果列表
			List<Map<String, Object>> list = baseFileRscPage.getList();
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> saveBaseFileRsc(Map<String, Object> params) {
		// 组装方法要判空
		if (params == null || params.isEmpty()) {
			throw ExceptionFactory.getBizException("campus-club-00003", "params");
		}
		//获取当前文件对象
		MultipartFile file = (MultipartFile)params.get("file");
		//查找当前操作用户
		SysUserEntity currentUser=userRepository.findByUserCode(MapUtils.getString(params,"userCode"));
		logger.error(file.toString());
		BaseFileRscEntity entity = new BaseFileRscEntity();
		//设置文件Id
		String maxFileId=fileSrcRepository.findSysFileMaxFileId();
		String nowFileId=null;
		//如果暂时不存在文件，则设置为默认的file-00001
		if(maxFileId==null){
			 nowFileId="file-00001";
		}else{
			 nowFileId= EncodeUtils.getConteactNo("file-",Integer.parseInt(maxFileId.split("-")[1]));
		}
		entity.setFileId(nowFileId);
		//设置文件名
		entity.setFileNm(file.getOriginalFilename());
		//设置文件用途
		entity.setFilePurpose(params.get("fileurpose").toString());
		//设置创建时间
		entity.setCreateTime(new Date());
		//设置创建人
		entity.setCreateUser(currentUser.getLoginName());
		//设置更新时间
		entity.setUpdateTime(new Date());
		//设置更新人
		entity.setUpdateUser(currentUser.getLoginName());
		//设置默认不删除
		entity.setDelInd("0");
		//设置为生效
		entity.setStatusCd(1);
		//设置版本号
		entity.setVersion(1);
        //设置创建人Code
		entity.setCreateUserId(currentUser.getUserCode());
		//设置文件扩展名
		String fileName=file.getOriginalFilename();
		String filetype="."+fileName.substring(fileName.indexOf(".")+1,fileName.length());
		entity.setFileExpdNm(filetype);
		//设置文件路径
		entity.setFileRte(params.get("url").toString());
		//文件大小
		entity.setFileSize(BigDecimal.valueOf(file.getSize()));
		//设置文件类型
		String originalFilename=file.getOriginalFilename();
		entity.setFileTpCd(originalFilename.substring(originalFilename.indexOf(".")+1,originalFilename.length()));
		//判断是否有社团编号
		if(MapUtils.getString(params,"stCd")==null){
           throw ExceptionFactory.getBizException("请完善社团信息后上传文件");
		}else{
			entity.setStCd(MapUtils.getString(params,"stCd"));
		}
		//保存到数据库
		BaseFileRscEntity result = fileSrcRepository.save(entity);
		if(result!=null){//如果保存成功
            params.clear();
            result.setFileRte(UrlUtils.getTrueUrlByString(result.getFileRte()));
			params.put("result",result);
		}
		return params;
	}
	
	@Override
	public void updateBaseFileRsc(Map<String, Object> params) {
		//update要先根据ID获取BO对象，然后在拷贝map里面的值
		String id = MapUtils.getString(params, BaseFileRscApiConstants.UUID);
		if (id == null) {
			throw ExceptionFactory.getBizException("campus-club-00002");
		}
		BaseFileRscEntity entity = new BaseFileRscEntity();
		entity.setUuid(id);
		entity=baseFileRscMapper.selectById(entity);
		if (entity == null) {
			throw ExceptionFactory.getBizException("campus-club-00003", "findOne");
		}
		//MapToEntityUtils.map2Entity(params, entity);

		fileSrcRepository.save(entity);
	}
	
	@Override
	public BaseFileRscEntity deleteBaseFileRsc(Map<String, Object> params) {
		String uuid = MapUtils.getString(params, BaseFileRscApiConstants.UUID);
		if (uuid == null) {
			throw ExceptionFactory.getBizException("uuid参数不能为空");
		}
		BaseFileRscEntity entity = fileSrcRepository.findByUuid(uuid);
		if (entity == null) {
			throw ExceptionFactory.getBizException("未找到该文件", "findOne");
		}
		entity.setDelInd(BaseFileRscApiConstants.DEL_IND_1); // 逻辑删除标识
        return fileSrcRepository.save(entity);
	}

	@Override
	public List<BaseFileRscEntity> fileList() {
		//Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		return null ;
	}

	@Override
	public BaseFileRscEntity findBaseFileRscEntityByFileId(String fileId) {
		return fileSrcRepository.findByFileId(fileId);
	}


}


