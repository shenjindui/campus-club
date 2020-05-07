package cn.fjut.gmxx.campusclub.workflow.entity;
import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 工作流 流程表
 * @author : shenjindui
 * @date : 2020-01-31 15:58
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_workflow")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 工作流成Id
     */
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

    @Column(name = "WORK_FLOW_CODE", columnDefinition = "varchar(32) COMMENT '工作流编号'")
    private String workFlowCode;

    public String getWorkFlowCode() {
        return workFlowCode;
    }

    public void setWorkFlowCode(String workFlowCode) {
        this.workFlowCode = workFlowCode;
    }

    @Column(name = "WORK_FLOW_NAME", columnDefinition = "varchar(32) COMMENT '工作流名称'")
    private String workFlowName;

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

    @Column(name = "WORK_FLOW_DESC", columnDefinition = "varchar(32) COMMENT '工作流描述'")
    private String workflowDesc;

    public String getWorkflowDesc() {
        return workflowDesc;
    }

    public void setWorkflowDesc(String workflowDesc) {
        this.workflowDesc = workflowDesc;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("workFlowCode")) {
                this.workFlowCode = MapUtils.getString(entityMap, "workFlowCode");
            }
            if (entityMap.containsKey("workFlowName")) {
                this.workFlowName = MapUtils.getString(entityMap, "workFlowName");
            }
            if (entityMap.containsKey("workflowDesc")) {
                this.workflowDesc = MapUtils.getString(entityMap, "workflowDesc");
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
