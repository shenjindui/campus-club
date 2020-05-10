package cn.fjut.gmxx.campusclub.controller.clubInfoController;/**
 * Created by admin on 2020/1/18.
 */

import cn.fjut.gmxx.campusclub.baseclubinfo.api.IBaseClubInfoApi;
import cn.fjut.gmxx.campusclub.baseclubmember.api.IBaseClubMemberApi;
import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : shenjindui
 * @date : 2020-01-18 20:28
 **/
@Api(tags = "社团接口")
@Controller
@RequestMapping(value = "/api")
public class ClubInfoController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    IBaseClubInfoApi baseClubInfoApi;
    @Autowired
    private IBaseClubMemberApi baseClubMemberApi;


    @SysOperationLog("社团列表")
    @TokenCheck
    @ApiOperation(value = "社团列表", notes = "社团列表方法", httpMethod = "POST")
    @RequestMapping(value = "/clublist", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clublist(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=baseClubInfoApi.findBaseClubInfoPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("社团添加")
    @TokenCheck
    @ApiOperation(value = "社团添加", notes = "社团添加方法", httpMethod = "POST")
    @RequestMapping(value = "/clubAdd", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubAdd(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=baseClubInfoApi.saveBaseClubInfoTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("社团详情方法")
    @TokenCheck
    @ApiOperation(value = "社团详情方法", notes = "社团详情法", httpMethod = "POST")
    @RequestMapping(value = "/clubdetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubDetail(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=baseClubInfoApi.getBaseClubInfoMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("申请理由保存方法")
    @TokenCheck
    @ApiOperation(value = "申请理由保存方法", notes = "申请理由法", httpMethod = "POST")
    @RequestMapping(value = "/clubSetOpinion", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubSetOpinion(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=baseClubInfoApi.saveBaseClubOpinionTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }


    @ApiOperation(value = "门户社团详情方法", notes = "门户社团详情方法", httpMethod = "POST")
    @RequestMapping(value = "/menhu/clubdetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubDetails(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            params.put("menhu","menhu");
            Map<String, Object> resultMaps=baseClubInfoApi.getBaseClubInfoMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }


    @ApiOperation(value = "门户社团列表", notes = "门户社团列表", httpMethod = "POST")
    @RequestMapping(value = "/menhu/clublists", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clublists(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=baseClubInfoApi.findBaseClubInfoPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("人员申请加入社团方法")
    @TokenCheck
    @ApiOperation(value = "人员申请加入社团方法", notes = "人员申请加入神团方法", httpMethod = "POST")
    @RequestMapping(value = "/joinclub", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubJoin(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=baseClubInfoApi.joinBaseClubInfoMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("停启用社团的方法")
    @TokenCheck
    @ApiOperation(value = "停启用社团的方法", notes = "停启用社团的方法", httpMethod = "POST")
    @RequestMapping(value = "/uodateStatus", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO uodateStatus(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            String isStatus= MapUtils.getString(params,"isStatus");
            if(isStatus!=null){
                if(isStatus.equals("true")){ //如果是停用的情况
                    params.put("statusCd","2");  //状态至为失效
                }else if(isStatus.equals("false")){
                    params.put("statusCd","1");   //生效
                }
            }
            Map<String, Object> resultMaps=baseClubInfoApi.saveBaseClubInfoTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("下载社团详情的方法")
    //@TokenCheck
    @ApiOperation(value = "下载社团详情的方法", notes = "停启用社团的方法", httpMethod = "POST")
    @RequestMapping(value = "/downloadStInfo", method = RequestMethod.GET)
    public void downloadWord(@RequestParam  Map<String, Object> params, HttpServletResponse response) throws Exception {
        //测试社团编号为st-00001
        params.put("stCd","st-00001");
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/msword");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=违纪处分通知.doc");
            Map<String, Object> dataMap = this.getData(params);
            //创建配置实例对象
            Configuration configuration = new Configuration();
            //设置编码
            configuration.setDefaultEncoding("UTF-8");
            //加载需要装填的模板
            configuration.setClassForTemplateLoading(this.getClass(), "/");
            //设置对象包装器
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            //设置异常处理器
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            //获取ftl模板对象
            Template template = configuration.getTemplate("export.ftl");
            StringBuilder fileName = new StringBuilder("");
            String uuid = UUID.randomUUID().toString();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            fileName.append(uuid).append("_").append(sdf1.format(new Date())).append("违纪处分").append(".doc");
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.toString().getBytes("GBK"), "ISO-8859-1"));
                response.setCharacterEncoding("utf-8");//处理乱码问题
                //生成Word文档
                Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
                template.process(dataMap, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Map<String, Object> getData(Map<String, Object> params) {
        //获取社团详情
        Map<String, Object> reusltInfo=baseClubInfoApi.getBaseClubInfoMap(params);
        Map<String, Object> dataMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //基础数据
        dataMap.put("STNAME", MapUtils.getString(reusltInfo,"stName")
                !=null?MapUtils.getString(reusltInfo,"stName"):"sysauto");//社团名称
        dataMap.put("STCD", MapUtils.getString(reusltInfo,"stCd")
                !=null?MapUtils.getString(reusltInfo,"stCd"):"sysauto");
        dataMap.put("COLLEGENO", MapUtils.getString(reusltInfo,"collegeNo")
                !=null?MapUtils.getString(reusltInfo,"collegeNo"):"sysauto");
        dataMap.put("SCHOOLNO", MapUtils.getString(reusltInfo,"schoolNo")
                !=null?MapUtils.getString(reusltInfo,"schoolNo"):"sysauto");
        dataMap.put("STCHARGENAME", MapUtils.getString(reusltInfo,"stChargeName")
                !=null?MapUtils.getString(reusltInfo,"stChargeName"):"sysauto");
        dataMap.put("STNATURE", MapUtils.getString(reusltInfo,"stNature")
                !=null?MapUtils.getString(reusltInfo,"stNature"):"sysauto");
        dataMap.put("CREATETIME", MapUtils.getString(reusltInfo,"createTime")
                !=null?MapUtils.getString(reusltInfo,"createTime"):"sysauto");

        Map<String, Object> queryParams =new HashMap<>();
        queryParams.put("stCd","st-00001");
        //表格数据
        List<Map<String, Object>> users = baseClubMemberApi.findBaseClubMemberAll(queryParams);
        dataMap.put("userList", users);
        return dataMap;
    }

    @SysOperationLog("社团列表社团社员")
    @TokenCheck
    @ApiOperation(value = "社团列表社团社员", notes = "社团列表社团社员方法", httpMethod = "POST")
    @RequestMapping(value = "/clublistByStsy", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clublistByStsy(@RequestBody  Map<String, Object> params) {
        try {
            List<Map<String, Object>> resultMaps=baseClubInfoApi.getBaseClubInfoByStsy(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("社团添加初始化")
    @TokenCheck
    @ApiOperation(value = "社团添加初始化", notes = "社团添加初始化", httpMethod = "POST")
    @RequestMapping(value = "/clubAddInit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubAddInit(@RequestBody  Map<String, Object> params) {
        try {
            Map<String, Object> resultMaps=baseClubInfoApi.addInitBaseClubInfoMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
}
