package cn.fjut.gmxx.campusclub.common;/**
 * Created by admin on 2020/1/3.
 */

/**
 * @author : shenjindui
 * @date : 2020-01-03 14:49
 **/
public interface Constant{
    //api 说明
    //
    String API= "/api";

    String AJAXLOGIN ="ajaxLogin";

    String SAVE = "register";

    String FORGOT = "forgot";

    String SUCCESS = "操作成功";

    String FAIL = "操作失败";

    //说明
    String LOGIN_SUCCESS = "登陆成功";

    String PARMS_ERROR = "参数错误";

    String LOGIN_ERROR= "账号或密码错误";

    String SAVE_SUCCESS= "保存成功";

    String SAVE_ERROR= "保存失败";

    String GET_SUCCESS = "获取详情成功";

    String GET_ERROR = "获取详情失败";

    String REGISTER_SUCCESS= "注册成功";

    String REGISTER_ERROR= "注册失败";

    String SYSTEM_PARAMS_ERROR = "请求异常【参数对象不能为空】";

    /**
     * 请求头参数
     */
    public static final String REQUEST_HEADERS_TOKEN = "Token";

}
