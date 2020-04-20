package cn.fjut.gmxx.campusclub.workflow.entity;/**
 * Created by admin on 2020/1/31.
 */

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : shenjindui
 * @date : 2020-01-31 18:15
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "sys_workflow_approver")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
/**
 * 审批人员表
 */
public class WorklowApproverEntity extends BaseEntity implements Serializable {
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

    @Column(name = "WORK_FLOW_APPROVER_CODE", columnDefinition = "varchar(32) COMMENT '审批人员编号'")
    private String workFlowApproverCode;

    @Column(name = "WORK_FLOW_CODE", columnDefinition = "varchar(32) COMMENT '工作流编号'")
    private String  workFlowCode;

    @Column(name = "WORK_FLOW_NODE_CODE", columnDefinition = "varchar(32) COMMENT '工作流节点编号'")
    private String  workFlowNodeCode;

    @Column(name = "USER_CODE", columnDefinition = "varchar(32) COMMENT '审批人用户编号'")
    private  String userCode;

    @Column(name = "APPROVER_TYPE", columnDefinition = "varchar(32) COMMENT '审批人类型(0,经办，1复核)'")
    private String approverType;

    public String getApproverType() {
        return approverType;
    }

    public void setApproverType(String approverType) {
        this.approverType = approverType;
    }

    public String getWorkFlowApproverCode() {
        return workFlowApproverCode;
    }

    public void setWorkFlowApproverCode(String workFlowApproverCode) {
        this.workFlowApproverCode = workFlowApproverCode;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
