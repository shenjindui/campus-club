package cn.fjut.gmxx.campusclub.controller.menuController;/**
 * Created by admin on 2020/1/12.
 */

import cn.fjut.gmxx.campusclub.common.*;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysmenu.api.ISysMenuApi;
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
 * @date : 2020-01-12 12:47
 **/
@Api(tags = "菜单服务接口")
@Controller
@RequestMapping(value = "/api")
public class SysMenuController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    ISysMenuApi sysMenuApi;
    //菜单列表方法

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名称")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @ApiOperation(value = "菜单列表", notes = "菜单列表方法", httpMethod = "POST")
    @Limiter(frequency = 10)//接口防刷限制在周期内只能访问3次
    @SysOperationLog("系统菜单列表")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @RequestMapping(value = "/menulist", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuList(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
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
     *
     * @param params
     * @param result
     * @return
     * @throws Exception
     */
    @SysOperationLog("系统菜单添加")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "菜单添加方法", notes = "菜单添加方法", httpMethod = "POST")
    @RequestMapping(value = "/menuedit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuAdd(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
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
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "菜单删除方法", notes = "菜单删除方法", httpMethod = "POST")
    @RequestMapping(value = "/menudelete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuDelete(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
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
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "菜单详情方法", notes = "菜单详情法", httpMethod = "POST")
    @RequestMapping(value = "/menudetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuDetail(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
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
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "菜单修改方法", notes = "菜单修改方法", httpMethod = "POST")
    @RequestMapping(value = "/menuupdate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuUpdate(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
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
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "菜单分配角色方法", notes = "菜单分配角色法", httpMethod = "POST")
    @RequestMapping(value = "/menutorole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuToRole(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
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
