package cn.fjut.gmxx.campusclub.common;
/**
 * @author : shenjindui
 * @date : 2019-12-30 20:34
 **/
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class BaseAppAction {
    @Autowired
    private HttpServletRequest request;
    private String viewPrefix;
    public String grid = "grid";

    public BaseAppAction() {
        this.setViewPrefix(this.defaultViewPrefix());
    }

    public void setViewPrefix(String viewPrefix) {
        if(viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }

        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return this.viewPrefix;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = (RequestMapping) AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        if(requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if(StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = "page";
        }

        return currentViewPrefix;
    }

    protected ModelAndView initPublicView(String src) {
        ModelAndView view = new ModelAndView("public");
        view.addObject("jsp", src);
        view.addObject("footer", "template/public_footer.jsp");
        return view;
    }

    protected ModelAndView initDialogView(String src) {
        ModelAndView view = new ModelAndView("dialog");
        view.addObject("jsp", src);
        return view;
    }

    protected ModelAndView initCenterView(String src) {
        ModelAndView view = new ModelAndView("public");
        view.addObject("jsp", "center.jsp");
        view.addObject("rightJsp", src);
        view.addObject("footer", "template/center_footer.jsp");
        return view;
    }

    protected ModelAndView initCommonView(String src) {
        ModelAndView view = new ModelAndView("public");
        view.addObject("jsp", src);
        view.addObject("footer", "template/center_footer.jsp");
        return view;
    }

    protected ModelAndView initPublicView() {
        return new ModelAndView("public");
    }

    protected ModelAndView initLoginView() {
        return new ModelAndView("login");
    }

    protected ModelAndView initDialogView() {
        return new ModelAndView("dialog");
    }

    protected ResponseVO successResponse(Object data) {
        return this.successResponse(data, (String)null);
    }

    protected ResponseVO successResponse(String description) {
        return this.successResponse((Object)null, description);
    }

    protected ResponseVO successResponse(Object data, String description) {
        return this.createResponse(data, Integer.valueOf(200), description);
    }

    protected ResponseVO errorResponse(Object data) {
        return this.errorResponse(data, (String)null);
    }

    protected ResponseVO errorResponse(String description) {
        return this.errorResponse((Object)null, description);
    }

    protected ResponseVO errorResponse(Object data, String description) {
        return this.createResponse(data, Integer.valueOf(400), description);
    }

    protected ResponseVO warnResponse(Object data) {
        return this.warnResponse(data, (String)null);
    }

    protected ResponseVO warnResponse(String description) {
        return this.warnResponse((Object)null, description);
    }

    protected ResponseVO warnResponse(Object data, String description) {
        return this.createResponse(data, Integer.valueOf(300), description);
    }

    protected ResponseVO createResponse(Object data, Integer status, String description) {
        ResponseVO response = new ResponseVO();
        if(null != data && data instanceof PageInfo) {
            Map<String, Object> result = new HashMap();
            result.put(this.grid, data);
            response.setData(result);
        } else {
            response.setData(data);
        }

        response.setStatus(status.intValue());
        response.setDescription(description);
        return response;
    }

    protected Map<String, String> getParameterMap(HttpServletRequest request) {
        Map properties = request.getParameterMap();
        Map<String, String> returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        String key = "";

        for(String value = ""; entries.hasNext(); returnMap.put(key, value)) {
            Map.Entry entry = (Map.Entry)entries.next();
            key = (String)entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj) {
                value = "";
            } else if(!(valueObj instanceof String[])) {
                value = valueObj.toString();
            } else {
                String[] values = (String[])((String[])valueObj);

                for(int i = 0; i < values.length; ++i) {
                    value = values[i] + ",";
                }

                value = value.substring(0, value.length() - 1);
            }
        }

        return returnMap;
    }

    /**
     * @功能描述 <pre>校验对象是否为空</pre>
     * @param vali 验证对象
     * @return
     */
    protected void validateNull(Object vali) {
        if(!ObjectUtil.isNotNull(vali)){
            throw ExceptionFactory.getBizException("对象为空");
        }
    }
    /**
     * @功能描述 <pre>校验对象是否为空</pre>
     * @param vali 验证对象数组
     * @return
     */
    protected void validateNull(Object[] vali) {
        for(int i = 0; i < vali.length; i ++){
            if(!ObjectUtil.isNotNull(vali[i])){
                throw ExceptionFactory.getBizException("对象为空");
            }
        }
    }

    protected String getIp() {
        String ipAddress = null;
        ipAddress = this.request.getHeader("X-Forwarded-For");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.request.getHeader("X-Real-IP");
        }

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.request.getHeader("Proxy-Client-IP");
        }

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.request.getHeader("WL-Proxy-Client-IP");
        }

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = this.request.getRemoteAddr();
        }

        if(ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }
}
