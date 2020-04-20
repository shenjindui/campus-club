package cn.fjut.gmxx.campusclub.workflow.entity;/**
 * Created by admin on 2020/1/31.
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
 * @date : 2020-01-31 18:21
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_workflow_business")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
/**
 * 工作流业务   流程业务表
 */
public class WorkflowBusinessEntity extends BaseEntity implements Serializable {
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

    @Column(name = "WORK_FLOW_BUSINESS_CODE", columnDefinition = "varchar(32) COMMENT '工作流业务编号'")
    private String workFlowBusinessCode;

    public String getWorkFlowBusinessCode() {
        return workFlowBusinessCode;
    }

    public void setWorkFlowBusinessCode(String workFlowBusinessCode) {
        this.workFlowBusinessCode = workFlowBusinessCode;
    }

    @Column(name = "BUSINESS_CODE", columnDefinition = "varchar(32) COMMENT '业务编号'")
    private String businessCode;

    @Column(name = "WORK_FLOW_CODE", columnDefinition = "varchar(32) COMMENT '工作流编号'")
    private String  workFlowCode;

    @Column(name = "WORK_FLOW_NODE_CODE", columnDefinition = "varchar(32) COMMENT '当前工作流节点编号'")
    private String  workFlowNodeCode;

    @Column(name = "SUGGESTION", columnDefinition = "varchar(32) COMMENT '审批意见'")
    private String  suggestion;

    @Column(name = "APPROVER_CODE", columnDefinition = "varchar(32) COMMENT '审批人编号，关联用户编号'")
    private  String approverCode;

    @Column(name = "USER_CODE", columnDefinition = "varchar(32) COMMENT '系统用户编号'")
    private  String userCode;

    @Column(name = "PCS_ST_CODE", columnDefinition = "varchar(32) COMMENT '当前流程步骤(0待审核,1审核中，2审核通过，3审核不通过)'")
    private  String pcsStCode;

    public String getPcsStCode() {
        return pcsStCode;
    }

    public void setPcsStCode(String pcsStCode) {
        this.pcsStCode = pcsStCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getWorkFlowCode() {
        return workFlowCode;
    }

    public void setWorkFlowCode(String workFlowCode) {
        this.workFlowCode = workFlowCode;
    }

    public String getWorkFlowNodeCode() {
        return workFlowNodeCode;
    }

    public void setWorkFlowNodeCode(String workFlowNodeCode) {
        this.workFlowNodeCode = workFlowNodeCode;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getApproverCode() {
        return approverCode;
    }

    public void setApproverCode(String approverCode) {
        this.approverCode = approverCode;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("pcsStCode")) {
                this.pcsStCode = MapUtils.getString(entityMap, "pcsStCode");
            }
            if (entityMap.containsKey("workFlowBusinessCode")) {
                this.workFlowBusinessCode = MapUtils.getString(entityMap, "workFlowBusinessCode");
            }
            if (entityMap.containsKey("userCode")) {
                this.userCode = MapUtils.getString(entityMap, "userCode");
            }
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("businessCode")) {
                this.businessCode = MapUtils.getString(entityMap, "businessCode");
            }
            if (entityMap.containsKey("workFlowCode")) {
                this.workFlowCode = MapUtils.getString(entityMap, "workFlowCode");
            }
            if (entityMap.containsKey("workFlowNodeCode")) {
                this.workFlowNodeCode = MapUtils.getString(entityMap, "workFlowNodeCode");
            }
            if (entityMap.containsKey("suggestion")) {
                this.suggestion = MapUtils.getString(entityMap, "suggestion");
            }
            if (entityMap.containsKey("approverOpinion")) {
                this.suggestion = MapUtils.getString(entityMap, "approverOpinion");
            }
            if (entityMap.containsKey("approverCode")) {
                this.approverCode = MapUtils.getString(entityMap, "approverCode");
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
