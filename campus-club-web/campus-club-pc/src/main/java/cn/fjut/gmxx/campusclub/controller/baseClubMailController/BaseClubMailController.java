package cn.fjut.gmxx.campusclub.controller.baseClubMailController;/**
 * Created by admin on 2020/4/25.
 */

import cn.fjut.gmxx.campusclub.baseclubMail.api.BaseclubMailApi;
import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.utlis.RandomUtils;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-04-25 14:09
 **/
@Api(tags = "社团邮件接口")
@Controller
@RequestMapping(value = "/api")
public class BaseClubMailController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    @Autowired
    BaseclubMailApi baseclubMailApi;
    @Autowired
    RedisUtils redisUtils;

    //@SysOperationLog("忘记密码发送邮件方法")
    @ApiOperation(value = "忘记密码发送邮件方法", notes = "忘记密码发送邮件方法", httpMethod = "POST")
    @RequestMapping(value = "/forgetSendMail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO forgetSendMail(@RequestBody Map<String, Object> params) {
        super.validateNull(params);
        try {
            // loginName to subject  code context
            Map<String,Object> parms=new HashMap<>();
            parms.put("loginName",MapUtils.getString(params,"loginName"));
            parms.put("to",MapUtils.getString(params,"to"));
            parms.put("subject","来自校园社团管理系统的安全验证邮件");
            String code= RandomUtils.stringWithNumber(6);
            String context = "欢迎使用校园社团管理系统,您的验证码是:<a>"+code+"</a>,本验证码60秒内有效!";
            parms.put("content",context);
            redisUtils.set(MapUtils.getString(params,"loginName"),code,60);
            baseclubMailApi.sendHtmlMail(parms);
            return successResponse("发送成功,请及时查收!", Constant.SUCCESS);
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    //@SysOperationLog("redis验证验证码是否过期")
    @ApiOperation(value = "redis验证验证码是否过期", notes = "redis验证验证码是否过期", httpMethod = "POST")
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO checkCode(@RequestBody Map<String, Object> params) {
        super.validateNull(params);
        try {
            Object object = redisUtils.get(MapUtils.getString(params,"loginName"));
            if(object!=null&&object.toString().equals(MapUtils.getString(params,"verificationCode"))){
                redisUtils.del(MapUtils.getString(params,"loginName"));
                return successResponse("验证成功!", Constant.SUCCESS);
            }else{
                return errorResponse("验证码已过期，请重新获取");
            }

        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
}
