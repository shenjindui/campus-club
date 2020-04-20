package cn.fjut.gmxx.campusclub.controller.roleController;

import cn.fjut.gmxx.campusclub.common.*;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysrole.api.ISysRoleApi;
import cn.fjut.gmxx.campusclub.utlis.FieldUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
/**
 * @author : shenjindui
 * @version :V1.0
 * @date : 2020-01-12 12:47
 **/
@Api(tags = "角色服务接口")
@Controller
@RequestMapping(value = "/api")
public class SysRoleController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    ISysRoleApi iSysRoleApi;
    @Autowired
    FieldUtils fieldUtils;

    @SysOperationLog("角色分页列表")
    @TokenCheck
    @Limiter(frequency = 10)
    @ApiOperation(value = "角色分页列表", notes = "列表方法", httpMethod = "POST")
    @RequestMapping(value = "/rolelist", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO roleList(@RequestBody Map<String, Object> params){
        if(!fieldUtils.validateObjectisNotNull(params)){
            return errorResponse(Constant.SYSTEM_PARAMS_ERROR);
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=iSysRoleApi.findSysRolePage(params);
            if(fieldUtils.validateObjectisNotNull(resultMaps)){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("角色列表All")
    @TokenCheck
    @Limiter(frequency = 10)
    @ApiOperation(value = "角色列表All", notes = "角色列表All", httpMethod = "POST")
    @RequestMapping(value = "/rolelistAll", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO roleListNoPage(@RequestBody  Map<String, Object> params){
        if(!fieldUtils.validateObjectisNotNull(params)){
            return errorResponse(Constant.SYSTEM_PARAMS_ERROR);
        }
        try {
            List<Map<String, Object>> resultMap=iSysRoleApi.findSysRoleNoPage(params);
            PageInfo<Map<String, Object>> resultMaps=new PageInfo<>();
            resultMaps.setList(resultMap);
            resultMaps.setTotal(resultMap.size());
            if(fieldUtils.validateObjectisNotNull(resultMaps)){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("角色详情方法")
    @TokenCheck
    @Limiter(frequency = 10)
    @ApiOperation(value = "角色详情方法", notes = "详情法", httpMethod = "POST")
    @RequestMapping(value = "/roledetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO roleDetail(@RequestBody  Map<String, Object> params){
        if(!fieldUtils.validateObjectisNotNull(params)){
            return errorResponse(Constant.SYSTEM_PARAMS_ERROR);
        }
        try {
            Map<String, Object> resultMaps=iSysRoleApi.getSysRoleMap(params);
            if(fieldUtils.validateObjectisNotNull(resultMaps)){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("角色添加方法")
    @TokenCheck
    @Limiter(frequency = 10)
    @ApiOperation(value = "角色添加方法", notes = "添加方法", httpMethod = "POST")
    @RequestMapping(value = "/roleadd", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO roleAdd(@RequestBody Map<String, Object> params){
        if(!fieldUtils.validateObjectisNotNull(params)){
            return errorResponse(Constant.SYSTEM_PARAMS_ERROR);
        }
        try {
            Map<String, Object> resultMaps=iSysRoleApi.saveSysRoleTrans(params);
            if(fieldUtils.validateObjectisNotNull(resultMaps)){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("角色修改方法")
    @TokenCheck
    @Limiter(frequency = 10)
    @ApiOperation(value = "角色修改方法", notes = "角色修改方法", httpMethod = "POST")
    @RequestMapping(value = "/roleupdate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO roleUpdate(@RequestBody  Map<String, Object> params) {
        if(!fieldUtils.validateObjectisNotNull(params)){
            return errorResponse(Constant.SYSTEM_PARAMS_ERROR);
        }
        try {
            Map<String, Object> resultMaps=iSysRoleApi.saveSysRoleTrans(params);
            if(fieldUtils.validateObjectisNotNull(resultMaps)){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("角色删除(失效)方法")
    @TokenCheck
    @Limiter(frequency = 10)
    @ApiOperation(value = "角色删除(失效)方法", notes = "删除(失效)方法", httpMethod = "POST")
    @RequestMapping(value = "/roledelete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO roleDelete(@RequestBody  Map<String, Object> params) {
        if(!fieldUtils.validateObjectisNotNull(params)){
            return errorResponse(Constant.SYSTEM_PARAMS_ERROR);
        }
        try {
            Map<String, Object> resultMaps=iSysRoleApi.deleteSysRoleTrans(params);
            if(fieldUtils.validateObjectisNotNull(resultMaps)){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

}
