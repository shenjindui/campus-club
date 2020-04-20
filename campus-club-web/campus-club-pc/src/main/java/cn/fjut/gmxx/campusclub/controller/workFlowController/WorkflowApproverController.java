package cn.fjut.gmxx.campusclub.controller.workFlowController;/**
 * Created by admin on 2020/1/31.
 */

import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.workflow.api.ISysWorkflowApproverApi;
import io.swagger.annotations.*;
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

/**
 * @author : shenjindui
 * @date : 2020-01-31 20:29
 **/
@Api(tags = "工作流服务接口")
@Controller
@RequestMapping(value = "/api")
public class WorkflowApproverController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);

    @Autowired
    ISysWorkflowApproverApi sysWorkflowApproverApi;
    @SysOperationLog("社团审核人用户列表")
    @TokenCheck
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "社团审核人用户列表",required = true,dataType = "String",paramType = "path",example = "blues")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
*/
    @ApiOperation(value = "社团审核人用户列表", notes = "社团审核人用户列表", httpMethod = "POST")
    @RequestMapping(value = "/stApproverList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO stApproverList(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=sysWorkflowApproverApi.findSysWorkflowApproverPage(params);
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
