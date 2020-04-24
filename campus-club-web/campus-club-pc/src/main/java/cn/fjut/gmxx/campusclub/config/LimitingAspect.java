package cn.fjut.gmxx.campusclub.config;
import cn.fjut.gmxx.campusclub.common.Limiter;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.utlis.AddressUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author : shenjindui
 * 接口防刷
 * @date : 2020-03-20 09:02
 **/
@Aspect
@Component
public class LimitingAspect {
    private static final String LIMITING_KEY = "limiting:%s:%s";
    private static final String LIMITING_BEGINTIME = "beginTime";
    private static final String LIMITING_EXFREQUENCY = "exFrequency";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(limiter)")
    public void pointcut(Limiter limiter) {
    }

    @Around("pointcut(limiter)")
    public Object around(ProceedingJoinPoint pjp, Limiter limiter) throws Throwable {
        //获取请求的ip和方法
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String ipAddress = AddressUtils.getIP(request);
        String methodName = pjp.getSignature().toLongString();

        //获取方法的访问周期和频率
        long cycle = limiter.cycle();
        int frequency = limiter.frequency();
        long currentTime = System.currentTimeMillis();

        //获取redis中周期内第一次访问方法的时间和执行的次数
        Long beginTimeLong = (Long) redisTemplate.opsForHash().get(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_BEGINTIME);
        Integer exFrequencyLong = (Integer) redisTemplate.opsForHash().get(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_EXFREQUENCY);

        long beginTime = beginTimeLong == null ? 0L : beginTimeLong;
        int exFrequency = exFrequencyLong == null ? 0 : exFrequencyLong;

        //如果当前时间减去周期内第一次访问方法的时间大于周期时间，则正常访问
        //并将周期内第一次访问方法的时间和执行次数初始化
        if (currentTime - beginTime > cycle) {
            redisTemplate.opsForHash().put(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_BEGINTIME, currentTime);
            redisTemplate.opsForHash().put(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_EXFREQUENCY, 1);
            redisTemplate.expire(String.format(LIMITING_KEY, ipAddress, methodName), limiter.expireTime(), TimeUnit.SECONDS);
            return pjp.proceed();
        } else {
            //如果在周期时间内，执行次数小于频率，则正常访问
            //并将执行次数加一
            if (exFrequency < frequency) {
                redisTemplate.opsForHash().put(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_EXFREQUENCY, exFrequency + 1);
                redisTemplate.expire(String.format(LIMITING_KEY, ipAddress, methodName), limiter.expireTime(), TimeUnit.SECONDS);
                return pjp.proceed();
            } else {
                //否则抛出访问频繁异常
                throw ExceptionFactory.getBizException(limiter.message());
            }
        }
    }
}