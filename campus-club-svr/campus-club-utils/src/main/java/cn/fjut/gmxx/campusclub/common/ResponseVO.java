package cn.fjut.gmxx.campusclub.common;/**
 * Created by admin on 2020/1/1.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : shenjindui
 * @date : 2020-01-01 11:32
 **/
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(description = "通用接口返回对象",value = "ResponseVO")
@RestControllerAdvice(basePackages = "cn.fjut.gmxx.campusclub.controller")
public class ResponseVO {
    public static final int OK = 200;
    public static final int ERROR = 100;
    public static final int NO_LOGIN = 101;
    public static final int WARN = 300;

    @ApiModelProperty(value = "返回状态码")
    private int status;
    @ApiModelProperty(value = "返回描述")
    private String description;
    @ApiModelProperty(value = "返回数据")
    private Object data;

    public ResponseVO() {
    }

    public ResponseVO(int status, String description, Object data) {
        this.status = status;
        this.description = description;
        this.data = data;
    }

    public ResponseVO(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
