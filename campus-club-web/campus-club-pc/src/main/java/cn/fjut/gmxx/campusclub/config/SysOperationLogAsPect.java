package cn.fjut.gmxx.campusclub.config;
import cn.fjut.gmxx.campusclub.sysoperationlog.api.ISysOperationLogApi;
import cn.fjut.gmxx.campusclub.utlis.JwtUtils;
import com.auth0.jwt.interfaces.Claim;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-03-15 09:43
 * @description 系统操作日志切面日志配置
 **/
@Aspect
@Component
public class SysOperationLogAsPect {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(SysOperationLogAsPect.class);
    @Autowired
    private ISysOperationLogApi sysOperationLogApi;

    @Autowired
    private JwtUtils jwtUtils;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(cn.fjut.gmxx.campusclub.config.SysOperationLog)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        long beginTime = System.currentTimeMillis();
        try {
            log.info("此方法在目标方法之前执行！");
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point,endTime-beginTime);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    //插入日志
    private void insertLog(ProceedingJoinPoint point ,long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        Map<String,Object> saveMap=new HashMap<String,Object>();

        SysOperationLog userAction = method.getAnnotation(SysOperationLog.class);
        if (userAction != null) {
            // 注解上的描述
            saveMap.put("remark",userAction.value());
        }
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        saveMap.put("className",className);
        // 请求的方法名
        String methodName = signature.getName();
        saveMap.put("methodName",methodName);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String token = request.getHeader("token");
       String  userCode=null;
        Map<String, Claim> map = jwtUtils.parseToken(token);
        for (Map.Entry<String, Claim> entry : map.entrySet()) {
            if (entry.getValue().asString() != null) {
                if(entry.getKey().equals("userCode")){
                    userCode=entry.getValue().asString();
                    break;
                }
            } else {
                continue;
            }
        }
        saveMap.put("userCode",userCode);
        sysOperationLogApi.saveSysOperationLogTrans(saveMap);
    }
}