package cn.fjut.gmxx.campusclub.config;

import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.sysuser.api.ISysUserApi;
import cn.fjut.gmxx.campusclub.utlis.JwtUtils;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import com.auth0.jwt.interfaces.Claim;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author : shenjindui
 * @date : 2020-01-14 10:19
 **/
@Configuration
public class TokenCheckInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ISysUserApi sysUserApi;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //设置拦截器不需要拦截的路径
        List ignoreList = new ArrayList();
        ignoreList.add("/api/login");
        //如果是门户浏览，不需要添加拦截
        ignoreList.add("/api/hotActivitys");
        ignoreList.add("/api/fileListFace");
        ignoreList.add("/api/clubnoticeslists");
        ignoreList.add("/api/clubnewslists");
        ignoreList.add("/api/menhu/clubdetail");
        ignoreList.add("/api/menhu/clublists");
        ignoreList.add("/api/menhu/clubActivityListFace");

        ignoreList.add("/api/getDetail");
        ignoreList.add("/api/register");
        ignoreList.add("/api/exportExcel");

        //不拦截的请求地址
        ignoreList.add("/api/pay/alipay/return");
        ignoreList.add("/api/pay/alipay/notify");

        ignoreList.add("/api/download");
        ignoreList.add("/api/downloadStInfo");
        //邮件发送
        ignoreList.add("/api/forgetSendMail");
        ignoreList.add("/api/checkCode");

        // 用户完善基本信息时,不要进行检验
         ignoreList.add("/api/userPerfect");
        ignoreList.add("/api/resetPass");

        //token 校验
        TokenCheck tokenCheck;
        if (handler instanceof HandlerMethod) {
            tokenCheck = ((HandlerMethod) handler).getMethodAnnotation(TokenCheck.class);
        } else {
            return true;
        }
        //这里处理一些业务逻辑，比如
        //有authCheck注解的方法需要进行登录校验
        String authToken="";
        if (tokenCheck != null) {
            authToken = request.getHeader("token");//前端传入的token值
            //判断token值是否为 null， 是否为 ""，是否为空字符串(引号中间有空格)  如： "     "
            if (StringUtils.isBlank(authToken)) {
                //这里自定义的一个异常，与全局异常捕获配合
                throw ExceptionFactory.getBizException("权限受限");
            } else {//不为空
                /*验证token的时间合法性*/
                Map<String, Claim> map=jwtUtils.verifyToken(authToken);
                if(map==null){
                    throw ExceptionFactory.getBizException("凭证已过期，请重新登录");
                }else{
                    Map<String, Claim> tokenDetail=jwtUtils.parseToken(authToken);
                    for (Map.Entry<String, Claim> entry : tokenDetail.entrySet()) {
                        if (entry.getValue().asString() != null) {
                            if(entry.getKey().equals("userCode")){
                                Map<String,Object> queryParm=new HashMap();
                                queryParm.put("userCode",entry.getValue().asString());
                                Map<String, Object> result=sysUserApi.getSysUserByOneMap(queryParm);
                                this.checkUserInfo(result);
                                Object redisToken=redisUtils.get(entry.getValue().asString());
                                if(!redisToken.equals(authToken)){
                                    throw ExceptionFactory.getBizException("您已经在他处登陆，请重新登录",authToken);
                                }
                            }
                            // System.out.println(entry.getKey() + "===" + entry.getValue().asString());
                        } else {
                            // System.out.println(entry.getKey() + "===" + entry.getValue().asDate());
                        }
                    }
                    return true;
                }
                //这里查表
                //TokenCheck tokenCheck = xxService.query("authToken");
               /* TokenCheck searchToken=null;
                if (authToken == null) {
                    throw ExceptionFactory.getBizException("权限受限");
                }else{
                    return true;
                }*/
            }
        }else if(ignoreList.contains(request.getRequestURI())){
            return true;
        }
        return false;
    }

    private void checkUserInfo(Map<String, Object> parms){
        if(MapUtils.getString(parms,"jobNum")==null||MapUtils.getString(parms,"jobNum")==""){
            throw ExceptionFactory.getBizException("请先完善个人信息,再进行操作！");
        }
    }

}
