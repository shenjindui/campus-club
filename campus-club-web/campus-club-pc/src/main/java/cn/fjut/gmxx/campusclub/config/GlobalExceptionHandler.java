package cn.fjut.gmxx.campusclub.config;
/**
 * @author : shenjindui
 * 统一异常处理
 * @date : 2020-01-15 17:53
 **/

import cn.fjut.gmxx.campusclub.common.ResponseVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseVO domainExceptionHandler(HttpServletRequest req,
                                             RuntimeException e) throws Exception {
        e.printStackTrace();
        ResponseVO result = new ResponseVO(500, e.getMessage(),null);
        return result;
    }

}
