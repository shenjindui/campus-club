package cn.fjut.gmxx.campusclub.exception;
/**
 * @author : shenjindui
 * @date : 2020-01-10 13:44
 **/
public class BizException extends RuntimeException {
    private String errorCode;
    private static final long serialVersionUID = 1L;

    public BizException() {
        super("unfindedBizError");
        this.errorCode = "unfindedBizError";
    }

    public BizException(String errorCode, String... args) {
        super(ExceptionMessageUtils.getCodeMessage(errorCode, args));
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, Throwable cause, String... args) {
        super(ExceptionMessageUtils.getCodeMessage(errorCode, args), cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
