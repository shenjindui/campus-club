package cn.fjut.gmxx.campusclub.sysuser.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import cn.fjut.gmxx.campusclub.utlis.MD5Utils;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
* @类名称 SysUserEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui 1
* @创建时间 2019-12-28
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2019-12-28 新建
* ----------------------------------------------
*
*/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_user")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SysUserEntity extends BaseEntity implements Serializable{

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
    @Column(name = "USER_CODE",columnDefinition="varchar(32) COMMENT '用户编号'")
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
    * 用户名
    */
    @Column(name = "REALNAME",columnDefinition="varchar(50) COMMENT '真实姓名'")
    private String realname;

    public String getRealname() {
    	return realname;
    }
    public void setRealname(String realname) {
    	this.realname = realname;
    }
    /**
    * 用户登录名
    */
    @Column(name = "LOGIN_NAME",columnDefinition="varchar(32) COMMENT '用户登录名'")
    private String loginName;

    public String getLoginName() {
    	return loginName;
    }
    public void setLoginName(String loginName) {
    	this.loginName = loginName;
    }
    /**
    * 密码
    */
    @Column(name = "PASSWORD",columnDefinition="varchar(50) COMMENT '密码'")
    private String password;

    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    /**
    * 盐
    */
    @Column(name = "SALT",columnDefinition="varchar(50) COMMENT '盐'")
    private String salt;

    public String getSalt() {
    	return salt;
    }
    public void setSalt(String salt) {
    	this.salt = salt;
    }
    /**
    * 角色ID
    */
    /*@Column(name = "ROLE_ID",columnDefinition="varchar(32) COMMENT '角色ID'")
    private String roleId;

    public String getRoleId() {
    	return roleId;
    }
    public void setRoleId(String roleId) {
    	this.roleId = roleId;
    }*/
    /**
    * 所属部门编号
    */
    @Column(name = "DEPART_CODE",columnDefinition="varchar(32) COMMENT '所属部门编号'")
    private String departCode;

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    /**
    * 状态STATUS:无效0/有效1 (是否通过审核)
    */
    /*@Column(name = "STATUS_CD",columnDefinition="varchar(1) COMMENT '状态STATUS:无效0/有效1 (是否通过审核)'")
    private String statusCd;*//*

    public String getStatusCd() {
    	return statusCd;
    }
    public void setStatusCd(String statusCd) {
    	this.statusCd = statusCd;
    }*/
    /**
    * 性别(SEX:男1\女2)
    */
    @Column(name = "SEX_CD",columnDefinition="varchar(1) COMMENT '性别(SEX:男1女2)'")
    private String sexCd;

    public String getSexCd() {
    	return sexCd;
    }
    public void setSexCd(String sexCd) {
    	this.sexCd = sexCd;
    }
    /**
    * 工号（比如学号，等等）
    */
    @Column(name = "JOB_NUM",columnDefinition="varchar(32) COMMENT '工号'")
    private String jobNum;

    public String getJobNum() {
    	return jobNum;
    }
    public void setJobNum(String jobNum) {
    	this.jobNum = jobNum;
    }
    /**
    * QQ
    */
    @Column(name = "QQ",columnDefinition="varchar(32) COMMENT 'QQ'")
    private String qq;

    public String getQq() {
    	return qq;
    }
    public void setQq(String qq) {
    	this.qq = qq;
    }
    /**
    * 微信
    */
    @Column(name = "WECHAT",columnDefinition="varchar(32) COMMENT '微信'")
    private String wechat;

    public String getWechat() {
    	return wechat;
    }
    public void setWechat(String wechat) {
    	this.wechat = wechat;
    }
    /**
    * 手机号
    */
    @Column(name = "MOBILE",columnDefinition="varchar(11) COMMENT '手机号'")
    private String mobile;

    public String getMobile() {
    	return mobile;
    }
    public void setMobile(String mobile) {
    	this.mobile = mobile;
    }
    /**
    * 邮箱
    */
    @Column(name = "EMAIL",columnDefinition="varchar(50) COMMENT '邮箱'")
    private String email;

    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    /**
    * 密码错误次数
    */
    @Column(name = "PASS_ERROR_COUNT",columnDefinition="int(10) default 0 COMMENT '密码错误次数'")
    private Integer passErrorCount;

    public Integer getPassErrorCount() {
    	return passErrorCount;
    }
    public void setPassErrorCount(Integer passErrorCount) {
    	this.passErrorCount = passErrorCount;
    }
    /**
    * 登录成功次数
    */
    @Column(name = "LOGIN_SUCC_COUNT",columnDefinition="int(10) default 0 COMMENT '登录成功次数'")
    private Integer loginSuccCount;

    public Integer getLoginSuccCount() {
    	return loginSuccCount;
    }
    public void setLoginSuccCount(Integer loginSuccCount) {
    	this.loginSuccCount = loginSuccCount;
    }
    /**
    * 锁定时间
    */
    @Column(name = "LOCK_TIME",columnDefinition="datetime default null COMMENT '锁定时间'")
    private java.util.Date lockTime;

    public java.util.Date getLockTime() {
    	return lockTime;
    }
    public void setLockTime(java.util.Date lockTime) {
    	this.lockTime = lockTime;
    }
    /**
    * 备注
    */
    /*@Column(name = "REMARK",columnDefinition="varchar(100) COMMENT '备注'")
    private String remark;

    public String getRemark() {
    	return remark;
    }
    public void setRemark(String remark) {
    	this.remark = remark;
    }*/
    /**
    * 地址
    */
    @Column(name = "ADDRESS",columnDefinition="varchar(100) COMMENT '地址'")
    private String address;

    public String getAddress() {
    	return address;
    }
    public void setAddress(String address) {
    	this.address = address;
    }

    /**
     * 地址
     */
    @Column(name = "URL",columnDefinition="varchar(128) COMMENT '图片地址'")
    private String url;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    /**
    * 上次登录时间
    */
    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(name = "LAST_LOGIN_TIME",columnDefinition="datetime default null COMMENT '上次登录时间'")
    private Date lastLoginTime;

    public Date getLastLoginTime() {
    	return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
    	this.lastLoginTime = lastLoginTime;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("url")) {
                this.url = MapUtils.getString(entityMap, "url");
            }
            if (entityMap.containsKey("loginName")) {
                this.loginName = MapUtils.getString(entityMap, "loginName");
            }
            if (entityMap.containsKey("jobNum")) {
                this.jobNum = MapUtils.getString(entityMap, "jobNum");
            }
            if (entityMap.containsKey("realname")) {
                this.realname = MapUtils.getString(entityMap, "realname");
            }
            if (entityMap.containsKey("realName")) {
                this.realname = MapUtils.getString(entityMap, "realName");
            }
            if (entityMap.containsKey("mobile")) {
                this.mobile = MapUtils.getString(entityMap, "mobile");
            }
            if (entityMap.containsKey("email")) {
                this.email = MapUtils.getString(entityMap, "email");
            }
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("password")) {
                this.password = MD5Utils.stringToMD5(MapUtils.getString(entityMap, "password"));
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
            if (entityMap.containsKey("remark")) {
                this.remark = MapUtils.getString(entityMap, "remark");
            }
            if (entityMap.containsKey("qq")) {
                this.qq = MapUtils.getString(entityMap, "qq");
            }
            if (entityMap.containsKey("wechat")) {
                this.wechat = MapUtils.getString(entityMap, "wechat");
            }
            if (entityMap.containsKey("sexCd")) {
                this.sexCd = MapUtils.getString(entityMap, "sexCd");
            }
        }
    }
}
