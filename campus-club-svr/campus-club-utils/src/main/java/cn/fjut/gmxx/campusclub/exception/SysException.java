package cn.fjut.gmxx.campusclub.exception;/**
 * Created by admin on 2020/1/10.
 */

/**
 * @author : shenjindui
 * @date : 2020-01-10 13:44
 **/
public class SysException extends RuntimeException {
    private String errorCode;
    private static final long serialVersionUID = 1L;

    public SysException() {
        this.errorCode = "unfindedSysError";
    }

    public SysException(String errorCode, String... args) {
        super(ExceptionMessageUtils.getCodeMessage(errorCode, args));
        this.errorCode = errorCode;
    }

    public SysException(String errorCode, Throwable cause, String... args) {
        super(ExceptionMessageUtils.getCodeMessage(errorCode, args), cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}

