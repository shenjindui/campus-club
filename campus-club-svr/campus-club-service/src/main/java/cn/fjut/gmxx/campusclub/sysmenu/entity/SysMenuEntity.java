package cn.fjut.gmxx.campusclub.sysmenu.entity;
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
 * @date : 2020-01-10 22:23
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_menu")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuEntity extends BaseEntity implements Serializable {

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
     * 菜单编号
     */
    @Column(name = "MENU_CODE", columnDefinition = "varchar(32) COMMENT '菜单编号'")
    private String menuCode;

    /**
     * 菜单名
     */
    @Column(name = "MENU_NAME", columnDefinition = "varchar(50) COMMENT '菜单名'")
    private String menuName;

    /**
     * 父菜单编号
     */
    @Column(name = "PARENT_MENU_CODE", columnDefinition = "varchar(32) COMMENT '父菜单编号'")
    private String parentMenuCode;

    /**
     *
     */
    @Column(name = "URL", columnDefinition = "varchar(255) COMMENT '菜单URL'")
    private String url;

    /**
     *
     */
    @Column(name = "SORT", columnDefinition = "varchar(32) COMMENT '排序'")
    private String sort;

    /**
     *
     */
    @Column(name = "LEAF_FLAG_CD", columnDefinition = "varchar(1) COMMENT '叶子节点标识(非叶子节点0/叶子节点1)'")
    private String leafFlagCd;


    /**
     *  所属角色编号
     */
    @Column(name = "ROLE_CODE", columnDefinition = "varchar(256) COMMENT '菜单所属角色'")
    private String roleCode;

    /**
     *  菜单图标
     */
    @Column(name = "ICON", columnDefinition = "varchar(255) COMMENT '菜单图标'")
    private String icon;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getLeafFlagCd() {
        return leafFlagCd;
    }

    public void setLeafFlagCd(String leafFlagCd) {
        this.leafFlagCd = leafFlagCd;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @param entityMap
     */
    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("menuCode")) {
                this.menuCode = MapUtils.getString(entityMap, "menuCode");
            }
            if (entityMap.containsKey("menuName")) {
                this.menuName = MapUtils.getString(entityMap, "menuName");
            }
            if (entityMap.containsKey("parentMenuCode")) {
                this.parentMenuCode = MapUtils.getString(entityMap, "parentMenuCode");
            }
            if (entityMap.containsKey("url")) {
                this.url = MapUtils.getString(entityMap, "url");
            }
            if (entityMap.containsKey("sort")) {
                this.sort = MapUtils.getString(entityMap, "sort");
            }
            if (entityMap.containsKey("leafFlagCd")) {
                this.leafFlagCd = MapUtils.getString(entityMap, "leafFlagCd");
            }
            if (entityMap.containsKey("roleCode")) {
                this.roleCode = MapUtils.getString(entityMap, "roleCode");
            }
            if (entityMap.containsKey("icon")) {
                this.icon = MapUtils.getString(entityMap, "icon");
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

