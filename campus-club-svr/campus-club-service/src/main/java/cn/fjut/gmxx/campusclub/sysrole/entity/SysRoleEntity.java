package cn.fjut.gmxx.campusclub.sysrole.entity;
import cn.fjut.gmxx.campusclub.data.BaseEntity;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
/**
 * @类名称 SysRoleEntity
 * @类描述 <pre>系统角色</pre>
 * @作者 shenjindui
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
@Table(name = "sys_role")
public class SysRoleEntity extends BaseEntity implements Serializable {

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
     * 角色名
     */
    @Column(name = "ROLE_NAME", columnDefinition = "varchar(50) COMMENT '角色名'")
    private String roleName;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 参数Map对应实体属性的转换
     * @param entityMap
     */
    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("roleCode")) {
                this.roleCode = MapUtils.getString(entityMap, "roleCode");
            }
            if (entityMap.containsKey("roleName")) {
                this.roleName = MapUtils.getString(entityMap, "roleName");
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





