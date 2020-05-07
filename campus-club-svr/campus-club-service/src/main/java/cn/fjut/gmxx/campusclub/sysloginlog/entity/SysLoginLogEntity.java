package cn.fjut.gmxx.campusclub.sysloginlog.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import org.apache.commons.collections.MapUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
* @类名称 BaseClubMemberEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1
* @创建时间 2020-02-22
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
@Entity
@Table(name = "sys_login_log")
@SequenceGenerator(name="seq", allocationSize=1, initialValue=1, sequenceName="sys_login_log")
public class SysLoginLogEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
    * 用户编号
    */
    @Column(name = "USER_NUM",columnDefinition = "varchar(32) COMMENT '用户编号'")
    private String userNum;


    /**
    * 用户名
    */
    @Column(name = "REALNAME",columnDefinition = "varchar(32) COMMENT '用户名'")
    private String realname;

    /**
    * 用户登录名
    */
    @Column(name = "LOGIN_NAME",columnDefinition = "varchar(32) COMMENT '用户登录名'")
    private String loginName;

    /**
    * 手机号
    */
    @Column(name = "MOBILE",columnDefinition = "varchar(32) COMMENT '手机号'")
    private String mobile;

    /**
    * 邮箱
    */
    @Column(name = "EMAIL",columnDefinition = "varchar(32) COMMENT '邮箱'")
    private String email;

    /**
     * 登录IP
     */
    @Column(name = "LOGIN_IP",columnDefinition = "varchar(32) COMMENT '登录IP'")
    private String loginIp;

    /**
     * MAC地址
     */
    @Column(name = "MAC",columnDefinition = "varchar(32) COMMENT 'MAC地址'")
    private String mac;

    /**
     * 浏览器名称
     */
    @Column(name = "BROWSER_NAME",columnDefinition = "varchar(32) COMMENT '浏览器名称'")
    private String browserName;

    /**
     * 浏览器版本
     */
    @Column(name = "BROWSER_VERSION",columnDefinition = "varchar(32) COMMENT '浏览器版本'")
    private String browserVersion;

    /**
     * 操作系统名称
     */
    @Column(name = "OS_NAME",columnDefinition = "varchar(32) COMMENT '操作系统名称'")
    private String osName;

    /**
     * 操作系统版本
     */
    @Column(name = "OS_VERSION",columnDefinition = "varchar(32) COMMENT '操作系统版本'")
    private String osVersion;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("browserName")) {
                this.browserName = MapUtils.getString(entityMap, "browserName");
            }
            if (entityMap.containsKey("browserVersion")) {
                this.browserVersion = MapUtils.getString(entityMap, "browserVersion");
            }
            if (entityMap.containsKey("osName")) {
                this.osName = MapUtils.getString(entityMap, "osName");
            }
            if (entityMap.containsKey("osVersion")) {
                this.osVersion = MapUtils.getString(entityMap, "osVersion");
            }
            if (entityMap.containsKey("loginIp")) {
                this.loginIp = MapUtils.getString(entityMap, "loginIp");
            }
            if (entityMap.containsKey("mac")) {
                this.mac = MapUtils.getString(entityMap, "mac");
            }
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("userNum")) {
                this.userNum = MapUtils.getString(entityMap, "userNum");
            }
            if (entityMap.containsKey("realname")) {
                this.realname = MapUtils.getString(entityMap, "realname");
            }
            if (entityMap.containsKey("loginName")) {
                this.loginName = MapUtils.getString(entityMap, "loginName");
            }
            if (entityMap.containsKey("mobile")) {
                this.mobile = MapUtils.getString(entityMap, "mobile");
            }
            if (entityMap.containsKey("email")) {
                this.email = MapUtils.getString(entityMap, "email");
            }
            if (entityMap.containsKey("statusCd")) {
                this.statusCd = MapUtils.getInteger(entityMap, "statusCd");
            }
            if (entityMap.containsKey("remark")) {
                this.remark = MapUtils.getString(entityMap, "remark");
            }
            if (entityMap.containsKey("createTime")) {
                this.createTime = new Date(MapUtils.getString(entityMap, "createTime"));
            }
            if (entityMap.containsKey("updateTime")) {
                this.updateTime = new Date(MapUtils.getString(entityMap, "updateTime"));
            }
            if (entityMap.containsKey("createUser")) {
                this.createUser = MapUtils.getString(entityMap, "createUser");
            }
            if (entityMap.containsKey("updateUser")) {
                this.updateUser = MapUtils.getString(entityMap, "updateUser");
            }
            if (entityMap.containsKey("delInd")) {
                this.delInd = MapUtils.getString(entityMap, "delInd");
            }
            if (entityMap.containsKey("version")) {
                this.version = MapUtils.getInteger(entityMap, "version");
            }
        }
    }
}
