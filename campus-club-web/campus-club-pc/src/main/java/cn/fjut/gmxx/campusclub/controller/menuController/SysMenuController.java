package cn.fjut.gmxx.campusclub.controller.menuController;
import cn.fjut.gmxx.campusclub.common.*;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.ISysMenuApi;
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

import java.util.Map;
/**
 * @author : shenjindui
 * @date : 2020-01-12 12:47
 **/
@Api(tags = "菜单服务接口")
@Controller
@RequestMapping(value = "/api")
public class SysMenuController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);

    @Autowired
    ISysMenuApi sysMenuApi;

    @ApiOperation(value = "菜单列表", notes = "菜单列表方法", httpMethod = "POST")
    @Limiter(frequency = 10)
    @SysOperationLog("系统菜单列表")
    @TokenCheck
    @RequestMapping(value = "/menulist", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuList(@RequestBody  Map<String, Object> params){
        super.validateNull(params);
        try {
            PageInfo<Map<String, Object>> resultMaps=sysMenuApi.findSysMenuPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
              return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    /**
     *系统菜单添加
     * @param params
     * @return
     * @throws Exception
     */
    //@NoRepeatSubmit  //表单重复添加校验
    @SysOperationLog("系统菜单添加")
    @TokenCheck
    @ApiOperation(value = "菜单添加方法", notes = "菜单添加方法", httpMethod = "POST")
    @RequestMapping(value = "/menuedit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuAdd(@RequestBody  Map<String, Object> params) {
        super.validateNull(params);
        try {
            Map<String, Object> resultMaps=sysMenuApi.saveSysMenuTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("系统菜单删除")
    @TokenCheck
    @ApiOperation(value = "菜单删除方法", notes = "菜单删除方法", httpMethod = "POST")
    @RequestMapping(value = "/menudelete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuDelete(@RequestBody  Map<String, Object> params){
        super.validateNull(params);
        try {
            Map<String, Object> resultMaps=sysMenuApi.deleteSysMenuTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("系统菜单详情方法")
    @TokenCheck
    @ApiOperation(value = "菜单详情方法", notes = "菜单详情法", httpMethod = "POST")
    @RequestMapping(value = "/menudetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuDetail(@RequestBody  Map<String, Object> params){
        super.validateNull(params);
        try {
            Map<String, Object> resultMaps=sysMenuApi.getSysMenuMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("系统菜单修改")
    @TokenCheck
    @ApiOperation(value = "菜单修改方法", notes = "菜单修改方法", httpMethod = "POST")
    @RequestMapping(value = "/menuupdate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuUpdate(@RequestBody  Map<String, Object> params){
        super.validateNull(params);
        try {
            Map<String, Object> resultMaps=sysMenuApi.saveSysMenuTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("系统菜单分配角色")
    @TokenCheck
    @ApiOperation(value = "菜单分配角色方法", notes = "菜单分配角色法", httpMethod = "POST")
    @RequestMapping(value = "/menutorole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuToRole(@RequestBody  Map<String, Object> params)throws Exception {
        super.validateNull(params);
        try {
            Map<String, Object> resultMaps=sysMenuApi.SysMenuToRoleTrans(params);
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