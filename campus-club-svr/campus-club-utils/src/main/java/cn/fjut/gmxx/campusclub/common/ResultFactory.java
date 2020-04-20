package cn.fjut.gmxx.campusclub.common;


import java.util.Map;

/**
 * Created by WJ on 2019/3/26 0026
 */
public class ResultFactory {

    public static ResponseVO buildSuccessResult(Map<String, Object> data) {
        return buidResult(ResultCode.LOGINSUCESS, "成功", data);
    }

    public static ResponseVO buildSuccessResult(String message,Map<String, Object> data) {
        return new ResponseVO(300,message,data);
    }


    public static ResponseVO buildFailResult(String message) {
        return buidResult(ResultCode.FAIL, message, null);
    }

    public static ResponseVO buidResult(ResultCode resultCode, String message, Map<String, Object> data) {
        return buidResult(resultCode.code, message, data);
    }

    public static ResponseVO buidResult(int resultCode, String message, Map<String, Object> data) {
        return new ResponseVO(resultCode, message, data);
    }
}
