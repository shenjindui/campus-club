package cn.fjut.gmxx.campusclub.controller.baseClubMemberController;/**
 * Created by admin on 2020/2/27.
 */

import cn.fjut.gmxx.campusclub.baseclubmember.api.IBaseClubMemberApi;
import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date : 2020-02-27 16:14
 **/

@Api(tags = "社团成员接口")
@Controller
@RequestMapping(value = "/api")
public class baseClubMemberAction extends BaseAppAction{
    @Autowired
    private IBaseClubMemberApi baseClubMemberApi;

    @SysOperationLog("社团成员列表")
    @TokenCheck
    @ApiOperation(value = "社团成员列表", notes = "社团成员方法", httpMethod = "POST")
    @RequestMapping(value = "/clubMemberList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO listBaseClubNews(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            params.put("init","init");
            PageInfo<Map<String, Object>> resultMaps=baseClubMemberApi.findBaseClubMemberPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps, Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
    @SysOperationLog("成员详情方法")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "成员详情方法", notes = "菜单详情法", httpMethod = "POST")
    @RequestMapping(value = "/clubMemberdetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO menuDetail(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=baseClubMemberApi.getBaseClubMemberMap(params);
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
