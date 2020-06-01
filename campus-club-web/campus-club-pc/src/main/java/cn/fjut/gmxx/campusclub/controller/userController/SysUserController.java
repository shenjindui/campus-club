package cn.fjut.gmxx.campusclub.controller.userController;

import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.sysuser.api.ISysUserApi;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "登陆服务 接口")
@Controller
@RequestMapping(value = "/api")
public class SysUserController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    private ISysUserApi sysUserApi;
    @Autowired
    RedisUtils redisUtils;

    //登陆方法
    //@TokenCheck
    @SysOperationLog("系统用户登陆系统")
    @ApiOperation(value = "user login", notes = "用户登陆方法", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO login(@RequestBody @Valid Map<String, Object> params, BindingResult result,HttpServletRequest request)throws Exception {
        //System.out.print(params.get("loginName"));
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(Constant.PARMS_ERROR);
            }
        }
        try {
            params.put("request",request);
            Map<String, Object> resultMaps=sysUserApi.getSysUserMap(params);
            if(!resultMaps.isEmpty()){
                return successResponse(resultMaps,Constant.LOGIN_SUCCESS);
            }else{
                return errorResponse(Constant.LOGIN_ERROR);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "user reg", notes = "用户注册方法", httpMethod = "POST")
    @RequestMapping(value = "/register",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO save(@RequestBody @Valid Map<String, Object> saveParam/*SysUserEntity sysUserEntity*/) throws Exception {
        Map<String, Object> baseSysUser = sysUserApi.saveSysUserMap(saveParam);
        if(!baseSysUser.isEmpty()){
            return successResponse(baseSysUser,Constant.REGISTER_SUCCESS);
        }else{
            return errorResponse(Constant.REGISTER_ERROR);
        }
    }

    @ApiOperation(value = "user  forget", notes = "用户找回密码方法", httpMethod = "POST")
    @RequestMapping(value = Constant.FORGOT,method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO forgot(@RequestBody @Valid Map<String, Object> saveParam) throws Exception {
        Map<String, Object> baseSysUser = sysUserApi.forgotSysUserMap(saveParam);
        if(!baseSysUser.isEmpty()){
            return successResponse(baseSysUser,Constant.SAVE_SUCCESS);
        }else{
            return errorResponse(Constant.SAVE_ERROR);
        }
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public ResponseVO get( ) throws Exception {
        int[] arr = {1, 2, 3};//元素只有3个
        System.out.println(arr[4]); //明显的数据越界异常
        return new ResponseVO(400, "明显的数据越界异常");
    }

    @ApiOperation(value = "user getDetail", notes = "用户详细方法", httpMethod = "POST")
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO getSysUserByParams(@RequestBody @Valid Map<String, Object> params, BindingResult result) {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(Constant.PARMS_ERROR);
            }
        }
        Map<String, Object> resultMaps=sysUserApi.getSysUserDetail(params);
        return successResponse(resultMaps,Constant.GET_SUCCESS);
    }
    @ApiOperation(value = "user getDetail", notes = "重置密码方法", httpMethod = "POST")
    @RequestMapping(value = "/resetPass", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO resetSysUserByParams(@RequestBody @Valid Map<String, Object> params, BindingResult result) {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(Constant.PARMS_ERROR);
            }
        }
        Map<String, Object> resultMaps=sysUserApi.updateSysUserByOneMap(params);
        return successResponse(resultMaps,Constant.GET_SUCCESS);
    }

    @SysOperationLog("用户列表")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "用户列表", notes = "用户列表方法", httpMethod = "POST")
    @RequestMapping(value = "/userList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userList(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=sysUserApi.findSysUserPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("用户详情方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "用户详情方法", notes = "用户详情法", httpMethod = "POST")
    @RequestMapping(value = "/userdetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userDetail(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysUserApi.getSysUserDetail(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("用户删除(失效)方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "用户删除(失效)方法", notes = "用户删除(失效)方法", httpMethod = "POST")
    @RequestMapping(value = "/userdelete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userDelete(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysUserApi.deleteSysUserByMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("用户添加方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "用户添加方法", notes = "添加方法", httpMethod = "POST")
    @RequestMapping(value = "/useradd", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userAdd(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysUserApi.saveSysUserMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("用户添加方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "用户添加方法", notes = "添加方法", httpMethod = "POST")
    @RequestMapping(value = "/userupdate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userUpdate(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysUserApi.saveSysUserMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("用户信息完善更新方法")
    //@TokenCheck
    @ApiOperation(value = "用户信息完善更新方法", notes = "用户信息完善更新方法", httpMethod = "POST")
    @RequestMapping(value = "/userPerfect", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userPerfect(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String,Object> queryParm=new HashMap();
            queryParm.put("userCode",MapUtils.getString(params,"userCode"));
            Map<String, Object> initMaps=sysUserApi.getSysUserByOneMap(queryParm);
            if(initMaps==null&& MapUtils.getString(initMaps,"uuid")==null){
                return errorResponse("用户基本系统初始化失败");
            }
            params.put("uuid",MapUtils.getString(initMaps,"uuid"));
            //保存的预校验
            this.savePreCheck(params);

            Map<String, Object> resultMaps=sysUserApi.saveSysUserMap(params);
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
     * 保存基本信息校验
     * @param params
     */
    private void savePreCheck(Map<String,Object> params){
        Map<String,Object> queryParm=new HashMap();
        //校验工号唯一性
        queryParm.put("jobNum",MapUtils.getString(params,"jobNum"));
        Map<String, Object> initMaps=sysUserApi.getSysUserByOneMap(queryParm);
        String uuid=MapUtils.getString(params,"uuid");
        if(uuid!=null){ //修改的时候
            if(initMaps!=null && !MapUtils.getString(initMaps,"uuid").equals(uuid)){
                throw ExceptionFactory.getBizException("该工号["+MapUtils.getString(params,"jobNum")+"]系统已存在");
            }
        }else{ //添加时候
            if(initMaps!=null){
                throw ExceptionFactory.getBizException("该工号["+MapUtils.getString(params,"jobNum")+"]系统已存在");
            }
        }
        queryParm.clear();
        queryParm.put("mobile",MapUtils.getString(params,"mobile"));
        Map<String, Object> resultMobile=sysUserApi.getSysUserByOneMap(queryParm);
        if(uuid!=null){ //修改的时候
            if(resultMobile!=null && !MapUtils.getString(resultMobile,"uuid").equals(uuid)){
                throw ExceptionFactory.getBizException("该手机号["+MapUtils.getString(params,"mobile")+"]系统已存在");
            }
        }else{ //添加时候
            if(resultMobile!=null){
                throw ExceptionFactory.getBizException("该手机号["+MapUtils.getString(params,"mobile")+"]系统已存在");
            }
        }
    }

    @SysOperationLog("详情方法")
    @TokenCheck
    @ApiOperation(value = "详情方法", notes = "详情方法", httpMethod = "POST")
    @RequestMapping(value = "/userdetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO userDetails(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysUserApi.getSysUsersMap(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }


    @SysOperationLog("首页用户初始化数据")
    @TokenCheck
    @ApiOperation(value = "首页用户初始化数据", notes = "首页用户初始化数据", httpMethod = "POST")
    @RequestMapping(value = "/initUserInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO initUserInfo(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=sysUserApi.initUserMap(params);
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
