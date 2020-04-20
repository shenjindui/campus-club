package cn.fjut.gmxx.campusclub.controller.sysOperationLogController;

import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysoperationlog.api.ISysOperationLogApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Api(tags = "系统操作服务接口")
@Controller
@RequestMapping(value = "/api")
public class SysOperationLogController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    private ISysOperationLogApi sysOperationLogApi;
    @SysOperationLog("日志列表")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "日志列表", notes = "日志列表方法", httpMethod = "POST")
    @RequestMapping(value = "/operationLoglist", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO loginLoglist(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=sysOperationLogApi.findSysOperationLogPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("日志详情方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "日志详情方法", notes = "日志详情法", httpMethod = "POST")
    @RequestMapping(value = "/operationLogDetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO loginLogDetail(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysOperationLogApi.getSysOperationLogMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("日志删除方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "日志删除方法", notes = "日志删除方法", httpMethod = "POST")
    @RequestMapping(value = "/operationLogdelete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO sysLoginLogdelete(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysOperationLogApi.deleteOperationLogTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

}
