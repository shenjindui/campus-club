package cn.fjut.gmxx.campusclub.exception;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/*
*
 * @author : shenjindui
 * @date : 2020-01-03 15:43
 **/

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    private String NullPointerExceptionStr="空指针异常";
    private String ArrayIndexOutOfBoundsStr="数组越界异常";
    private String ClassCastExceptionStr="类型转换异常";
    private String OtherExceptionStr="其他类型转换异常";
    private int ERROR_CODE = 400;

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public ResponseVO nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(ERROR_CODE, new Exception(NullPointerExceptionStr));
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ResponseVO classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(ERROR_CODE,  new Exception(ClassCastExceptionStr));
    }


    //数组越界异常
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseVO ArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
        return resultFormat(ERROR_CODE, new Exception(ArrayIndexOutOfBoundsStr));
    }

    //其他错误
    @ExceptionHandler({Exception.class})
    public ResponseVO exception(Exception ex) {
        return resultFormat(ERROR_CODE, new Exception(OtherExceptionStr));
    }

    private <T extends Throwable> ResponseVO resultFormat(Integer code, T ex) {
        ex.printStackTrace();
        return new ResponseVO(code, ex.getMessage());
    }
}
