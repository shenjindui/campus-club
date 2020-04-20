package cn.fjut.gmxx.campusclub.workflow.entity;/**
 * Created by admin on 2020/1/31.
 */

/**
 * @author : shenjindui
 * @date : 2020-01-31 16:10
 **/

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * \流程线表
 */
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_workflow_link")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowLinkEntity extends BaseEntity implements Serializable {
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

    @Column(name = "WORK_FLOW_LINK_CODE", columnDefinition = "varchar(32) COMMENT '工作流流程线表编号'")
    private String workFlowLinkCode;

    @Column(name = "WORK_FLOW_CODE", columnDefinition = "varchar(32) COMMENT '工作流编号'")
    private String  workFlowCode;

    @Column(name = "WORK_FLOW_LINK_NAME", columnDefinition = "varchar(32) COMMENT '工作流流程线表名称'")
    private String  workFlowLinkName;

    @Column(name = "WORKFLOW_LINK_PRE_NODE", columnDefinition = "varchar(32) COMMENT '工作流流程线上一节点名称'")
    private  String workflowLinkPreNode;

    @Column(name = "WORKFLOW_LINK_NEXT_NODE", columnDefinition = "varchar(32) COMMENT '工作流流程线下一节点名称'")
    private  String workflowLinkNextNode;

    public String getWorkFlowLinkCode() {
        return workFlowLinkCode;
    }

    public void setWorkFlowLinkCode(String workFlowLinkCode) {
        this.workFlowLinkCode = workFlowLinkCode;
    }

    public String getWorkFlowCode() {
        return workFlowCode;
    }

    public void setWorkFlowCode(String workFlowCode) {
        this.workFlowCode = workFlowCode;
    }

    public String getWorkFlowLinkName() {
        return workFlowLinkName;
    }

    public void setWorkFlowLinkName(String workFlowLinkName) {
        this.workFlowLinkName = workFlowLinkName;
    }

    public String getWorkflowLinkPreNode() {
        return workflowLinkPreNode;
    }

    public void setWorkflowLinkPreNode(String workflowLinkPreNode) {
        this.workflowLinkPreNode = workflowLinkPreNode;
    }

    public String getWorkflowLinkNextNode() {
        return workflowLinkNextNode;
    }

    public void setWorkflowLinkNextNode(String workflowLinkNextNode) {
        this.workflowLinkNextNode = workflowLinkNextNode;
    }
}
