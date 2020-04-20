package cn.fjut.gmxx.campusclub.common;
/**
 * Created by shenjindui on 2020/1/10.
 */
public enum ExcetionType {
    SYS00001("sys-00001","数据转换异常"),

    SYS00002("sys-00002","id不存在"),

    SYSROLE00001("sysrole-00001","默认角色信息不存在，请联系管理员处理");

    private String errorCode;
    private String errorMessage;

    public  String getErrorMessage(String errorCode) {
        return this.errorMessage;
    }

    private ExcetionType(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
