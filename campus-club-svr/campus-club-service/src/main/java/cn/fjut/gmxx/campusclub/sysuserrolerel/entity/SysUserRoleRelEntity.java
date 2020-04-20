package cn.fjut.gmxx.campusclub.sysuserrolerel.entity;/**
 * Created by admin on 2020/1/10.
 */

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-01-10 22:34
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_user_role_rel")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRoleRelEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 16)
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    /**
     * 角色编号
     */
    @Column(name = "ROLE_CODE", columnDefinition = "varchar(32) COMMENT '角色编号'")
    private String roleCode;

    /**
     * 用户编号
     */
    @Column(name = "USER_CODE", columnDefinition = "varchar(32) COMMENT '用户编号'")
    private String userCode;

    @Column(name = "DEFAULT_ROLE", columnDefinition = "varchar(1) default 0 COMMENT '是否为默认角色编号'")
    private String defaultRole;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("roleCode")) {
                this.roleCode = MapUtils.getString(entityMap, "roleCode");
            }
            if (entityMap.containsKey("userCode")) {
                this.userCode = MapUtils.getString(entityMap, "userCode");
            }
            if (entityMap.containsKey("userCode")) {
                this.userCode = MapUtils.getString(entityMap, "userCode");
            }
            if (entityMap.containsKey("defaultRole")) {
                this.defaultRole = MapUtils.getString(entityMap, "defaultRole");
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
        }
    }
}

