package cn.fjut.gmxx.campusclub.baseclubMail.api;

import java.util.Map;

/**
 * Created by admin on 2020/4/25.
 */
public interface BaseclubMailApi {
    /**
     * 发送HTML邮件，方便用户点击附带的code用来验证激活账户
     * @param params
     */
    void sendHtmlMail(Map<String, Object> params);
}