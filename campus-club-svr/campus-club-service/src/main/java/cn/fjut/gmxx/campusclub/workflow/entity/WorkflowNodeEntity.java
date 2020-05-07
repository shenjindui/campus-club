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
 * @author : shenjindui
 * @date : 2020-01-31 16:06
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_workflow_node")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowNodeEntity  extends BaseEntity implements Serializable {
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

    @Column(name = "WORK_FLOW_NODE_CODE", columnDefinition = "varchar(32) COMMENT '工作流节点编号'")
    private String workFlowNodeCode;

    @Column(name = "WORK_FLOW_CODE", columnDefinition = "varchar(32) COMMENT '工作流编号'")
    private String  workFlowCode;

    @Column(name = "WORK_FLOW_NODE_NAME", columnDefinition = "varchar(32) COMMENT '工作流节点名称'")
    private String  workFlowNodeName;

    @Column(name = "work_flow_node_type", columnDefinition = "varchar(32) COMMENT '工作流节点类型 （1第一岗 ?2第二岗）'")
    private String  workFlowNodeType;

    public String getWorkFlowNodeType() {
        return workFlowNodeType;
    }

    public void setWorkFlowNodeType(String workFlowNodeType) {
        this.workFlowNodeType = workFlowNodeType;
    }

    public String getWorkFlowNodeCode() {
        return workFlowNodeCode;
    }

    public void setWorkFlowNodeCode(String workFlowNodeCode) {
        this.workFlowNodeCode = workFlowNodeCode;
    }

    public String getWorkFlowCode() {
        return workFlowCode;
    }

    public void setWorkFlowCode(String workFlowCode) {
        this.workFlowCode = workFlowCode;
    }

    public String getWorkFlowNodeName() {
        return workFlowNodeName;
    }

    public void setWorkFlowNodeName(String workFlowNodeName) {
        this.workFlowNodeName = workFlowNodeName;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("workFlowCode")) {
                this.workFlowCode = MapUtils.getString(entityMap, "workFlowCode");
            }
            if (entityMap.containsKey("workFlowNodeCode")) {
                this.workFlowNodeCode = MapUtils.getString(entityMap, "workFlowNodeCode");
            }
            if (entityMap.containsKey("workFlowNodeName")) {
                this.workFlowNodeName = MapUtils.getString(entityMap, "workFlowNodeName");
            }
            if (entityMap.containsKey("workFlowNodeType")) {
                this.workFlowNodeType = MapUtils.getString(entityMap, "workFlowNodeType");
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
