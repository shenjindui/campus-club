package cn.fjut.gmxx.campusclub.sysbusiness.entity;/**
 * Created by admin on 2020/1/31.
 */

/**
 * @author : shenjindui
 * @date : 2020-01-31 18:28
 **/

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 系统业务表  审批 活动申请等等业务
 */
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_business")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysBusinessEntity extends BaseEntity implements Serializable {
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

    @Column(name = "BUSINESS_CODE", columnDefinition = "varchar(32) COMMENT '业务编号'")
    private String businessCode;

    @Column(name = "APPROVER_USER_CODE", columnDefinition = "varchar(32) COMMENT '业务申请用户编号'")
    private String approverUserCode;

    @Column(name = "BUSINESS_CATEGORY", columnDefinition = "varchar(255) COMMENT '业务类别'")
    private String businessCategory;

    @Column(name = "BUSINESS_ASSOCIATION_CODE", columnDefinition = "varchar(32) COMMENT '业务关联编号'")
    private String businessAssociationCode;

    @Column(name = "BUSINESS_STATE", columnDefinition = "varchar(255) COMMENT '业务状态(状态：0-未结束；1-已结束)'")
    private String businessState;

    @Column(name = "BUSINESS_DESC", columnDefinition = "varchar(32) COMMENT '业务描述'")
    private String businessDesc;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getApproverUserCode() {
        return approverUserCode;
    }

    public void setApproverUserCode(String approverUserCode) {
        this.approverUserCode = approverUserCode;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getBusinessAssociationCode() {
        return businessAssociationCode;
    }

    public void setBusinessAssociationCode(String businessAssociationCode) {
        this.businessAssociationCode = businessAssociationCode;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("businessAssociationCode")) {
                this.businessAssociationCode = MapUtils.getString(entityMap, "businessAssociationCode");
            }
            if (entityMap.containsKey("businessCode")) {
                this.businessCode = MapUtils.getString(entityMap, "businessCode");
            }
            if (entityMap.containsKey("approverUserCode")) {
                this.approverUserCode = MapUtils.getString(entityMap, "approverUserCode");
            }
            if (entityMap.containsKey("businessCategory")) {
                this.businessCategory = MapUtils.getString(entityMap, "businessCategory");
            }
            if (entityMap.containsKey("businessState")) {
                this.businessState = MapUtils.getString(entityMap, "businessState");
            }
            if (entityMap.containsKey("businessDesc")) {
                this.businessDesc = MapUtils.getString(entityMap, "businessDesc");
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
