package cn.fjut.gmxx.campusclub.controller.clubActivityController;

import cn.fjut.gmxx.campusclub.baseclubactivity.api.BaseClubActivityApiConstants;
import cn.fjut.gmxx.campusclub.baseclubactivity.api.IBaseClubActivityApi;
import cn.fjut.gmxx.campusclub.common.*;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.DateUtils;
import cn.fjut.gmxx.campusclub.utlis.ExportExcelByMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "社团活动接口")
@Controller
@RequestMapping(value = "/api")
public class BaseClubActivityController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    private IBaseClubActivityApi baseClubActivityApi;

    @SysOperationLog("社团活动列表")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "社团活动列表", notes = "社团活动列表方法", httpMethod = "POST")
    @RequestMapping(value = "/clubActivityList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO listBaseClubActivity(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=baseClubActivityApi.findBaseClubActivityPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("社团活动添加")
    @TokenCheck
    @ApiOperation(value = "社团活动添加", notes = "社团活动添加", httpMethod = "POST")
    @RequestMapping(value = "/clubActivityAdd", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO clubActivityAdd(@RequestBody  Map<String, Object> params) {
        super.validateNull(params);
        try {
            Map<String, Object> resultMaps=baseClubActivityApi.saveBaseClubActivityTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @RequestMapping(value = "/get")
    @ResponseBody
    public ResponseVO get(@RequestObjectParam Map<String, Object> requestParam) throws Exception {
        Integer id = MapUtils.getInteger(requestParam, BaseClubActivityApiConstants.uuid);
        if (null != id) {
            return successResponse( baseClubActivityApi.getBaseClubActivityMap(requestParam));
        } else {
            return successResponse("查询条件不存在");
        }
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseVO save(@RequestObjectParam Map<String, Object> requestParam) throws Exception {
        Map<String, Object> baseBaseClubActivity = baseClubActivityApi.saveBaseClubActivityTrans(requestParam);
        if (StringUtils.isNotBlank(MapUtils.getString(requestParam, BaseClubActivityApiConstants.uuid))) {
            return successResponse("修改成功");
        } else {
            return successResponse(baseBaseClubActivity, "保存成功");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseVO delete(@RequestObjectParam Map<String, Object> requestParam) {
        Integer id = MapUtils.getInteger(requestParam, BaseClubActivityApiConstants.uuid);
        if (null == id) {
            return errorResponse("id不能为空");
        }
        //调用删除方法
        baseClubActivityApi.deleteBaseClubActivityTrans(requestParam);
        return successResponse("删除成功");
    }

   // @TokenCheck
    @ApiOperation(value = "hotActivitys ", notes = "首页展示热门活动的方法,限制为4", httpMethod = "POST")
    @RequestMapping(value = "/hotActivitys", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO getHotActivitys(@RequestBody @Valid Map<String, Object> params, BindingResult result,HttpServletRequest request)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(Constant.PARMS_ERROR);
            }
        }
        try {
            params.put("limit","1");
            PageInfo<Map<String, Object>> resultMaps = baseClubActivityApi.findBaseClubActivityPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.LOGIN_SUCCESS);
            }else{
                return errorResponse(Constant.LOGIN_ERROR);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

   // @TokenCheck
    @ApiOperation(value = "export", notes = "export", httpMethod = "POST")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void   exportExcel(@RequestParam  Map<String, Object> params, HttpServletResponse response)throws Exception {
        PageInfo<Map<String, Object>> resultMaps = baseClubActivityApi.findBaseClubActivityPage(params);
        List<Map<String, Object>> list = resultMaps.getList();
        String sheetName="社团活动信息列表-"+ DateUtils.dateTime();
        String[] headers=new String[]{"活动编号","活动名称","活动内容","活动地点",
                "主办社团编号","预计活动开始时间","预计结束开始时间"};
        String[] headersField=new String[]{"activityId","activityName",
                "activityDsc","activitySpace","hostStCd","startTime","endTime"};
        String pattern="yyyy-MM-dd HH:mm:ss";
        ExportExcelByMap.exportExcels(sheetName,headers,headersField,list,pattern,response);
    }

    @ApiOperation(value = "社团活动列表", notes = "社团活动列表方法", httpMethod = "POST")
    @RequestMapping(value = "/menhu/clubActivityListFace", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO listBaseClubActivityFace(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=baseClubActivityApi.findBaseClubActivityPage(params);
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
