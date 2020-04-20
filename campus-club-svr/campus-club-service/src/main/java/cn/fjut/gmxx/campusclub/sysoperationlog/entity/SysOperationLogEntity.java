package cn.fjut.gmxx.campusclub.sysoperationlog.entity;

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
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
@Entity
@Table(name = "sys_operation_log")
@SequenceGenerator(name="seq", allocationSize=1, initialValue=1, sequenceName="sys_operation_log")
public class SysOperationLogEntity extends BaseEntity implements Serializable{

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
    * 操作编号
    */
    @Column(name = "OPERATION_CODE",columnDefinition = "varchar(32) COMMENT '操作编号'")
    private String operationCode;


    /**
    * 操作用户编号
    */
    @Column(name = "USER_CODE",columnDefinition = "varchar(32) COMMENT '操作用户编号'")
    private String userCode;

    /**
    * 操作日志访问地址
    */
    @Column(name = "CLASS_NAME",columnDefinition = "varchar(128) COMMENT '请求操作类名'")
    private String className;

    /**
    * ip
    */
    @Column(name = "METHOD_NAME",columnDefinition = "varchar(32) COMMENT '请求操作方法名'")
    private String methodName;


    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("operationCode")) {
                this.operationCode = MapUtils.getString(entityMap, "operationCode");
            }
            if (entityMap.containsKey("userCode")) {
                this.userCode = MapUtils.getString(entityMap, "userCode");
            }
            if (entityMap.containsKey("className")) {
                this.className = MapUtils.getString(entityMap, "className");
            }
            if (entityMap.containsKey("methodName")) {
                this.methodName = MapUtils.getString(entityMap, "methodName");
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
