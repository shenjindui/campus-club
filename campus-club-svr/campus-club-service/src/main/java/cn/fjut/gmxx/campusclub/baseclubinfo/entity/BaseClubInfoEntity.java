package cn.fjut.gmxx.campusclub.baseclubinfo.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
* @类名称 BaseClubInfoEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui
* @创建时间 2020-01-18
* @版本 vv
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
*  shenjindui 2020-01-18 新建
* ----------------------------------------------
*
*/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "base_club_info")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseClubInfoEntity extends BaseEntity implements Serializable{

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
    * 社团编号
    */
    @Column(name = "ST_CD",columnDefinition = "varchar(32) COMMENT '社团编号'")
    private String stCd;

    public String getStCd() {
    	return stCd;
    }
    public void setStCd(String stCd) {
    	this.stCd = stCd;
    }
    /**
    * 社团名字
    */
    @Column(name = "ST_NAME",columnDefinition = "varchar(50) COMMENT '社团名字'")
    private String stName;

    public String getStName() {
    	return stName;
    }
    public void setStName(String stName) {
    	this.stName = stName;
    }
    /**
    * 社团负责人
    */
    @Column(name = "ST_CHARGE_NAME",columnDefinition = "varchar(32) COMMENT '社团负责人'")
    private String stChargeName;

    public String getStChargeName() {
    	return stChargeName;
    }
    public void setStChargeName(String stChargeName) {
    	this.stChargeName = stChargeName;
    }
    /**
    * 社团负责人 学号
    */
    @Column(name = "ST_CHARGE_SNO",columnDefinition = "varchar(32) COMMENT '社团负责人 学号'")
    private String stChargeSno;

    public String getStChargeSno() {
    	return stChargeSno;
    }
    public void setStChargeSno(String stChargeSno) {
    	this.stChargeSno = stChargeSno;
    }
    /**
    * 社团负责人 手机号
    */
    @Column(name = "ST_CHARGE_PHONE",columnDefinition = "varchar(32) COMMENT '社团负责人 手机号'")
    private String stChargePhone;

    public String getStChargePhone() {
    	return stChargePhone;
    }
    public void setStChargePhone(String stChargePhone) {
    	this.stChargePhone = stChargePhone;
    }
    /**
    * 社团创建人
    */
    @Column(name = "ST_FOUNDER",columnDefinition = "varchar(32) COMMENT '社团创建人'")
    private String stFounder;

    public String getStFounder() {
    	return stFounder;
    }
    public void setStFounder(String stFounder) {
    	this.stFounder = stFounder;
    }
    /**
    * 社团性质
    */
    @Column(name = "ST_NATURE",columnDefinition = "varchar(32) COMMENT '社团性质'")
    private String stNature;

    public String getStNature() {
    	return stNature;
    }
    public void setStNature(String stNature) {
    	this.stNature = stNature;
    }
    /**
    * 社团描述
    */
    @Column(name = "ST_DESC",columnDefinition = "text COMMENT '社团描述'")
    private String stDesc;

    public String getStDesc() {
    	return stDesc;
    }
    public void setStDesc(String stDesc) {
    	this.stDesc = stDesc;
    }
    /**
    * 备注
    *//*
    @Column(name = "REMARK")
    private String remark;

    public String getRemark() {
    	return remark;
    }
    public void setRemark(String remark) {
    	this.remark = remark;
    }*/
    /**
    * 所属学校编码
    */
    @Column(name = "SCHOOL_NO",columnDefinition = "varchar(32) COMMENT '所属学校编码'")
    private String schoolNo;

    public String getSchoolNo() {
    	return schoolNo;
    }
    public void setSchoolNo(String schoolNo) {
    	this.schoolNo = schoolNo;
    }
    /**
    * 所属学院编码
    */
    @Column(name = "COLLEGE_NO",columnDefinition = "varchar(32) COMMENT '所属学院编码'")
    private String collegeNo;

    public String getCollegeNo() {
    	return collegeNo;
    }
    public void setCollegeNo(String collegeNo) {
    	this.collegeNo = collegeNo;
    }
    /**
    * 社团状态workflow_cd
    */
    @Column(name = "WORKFLOW_CD",columnDefinition = "int COMMENT '工作流流成'")
    private Integer workflowCd;

    public Integer getWorkflowCd() {
        return workflowCd;
    }

    public void setWorkflowCd(Integer workflowCd) {
        this.workflowCd = workflowCd;
    }

    @Column(name = "CREATE_OPINION",columnDefinition = "varchar(300) COMMENT '社团创建理由'")
    private String createOpinion;

    public String getCreateOpinion() {
        return createOpinion;
    }

    public void setCreateOpinion(String createOpinion) {
        this.createOpinion = createOpinion;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("opinion")) {
                this.createOpinion = MapUtils.getString(entityMap, "opinion");
            }
            if (entityMap.containsKey("workflowCd")) {
                this.workflowCd = MapUtils.getInteger(entityMap, "workflowCd");
            }
            if (entityMap.containsKey("stCd")) {
                this.stCd = MapUtils.getString(entityMap, "stCd");
            }
            if (entityMap.containsKey("stName")) {
                this.stName = MapUtils.getString(entityMap, "stName");
            }
            if (entityMap.containsKey("stChargeName")) {
                this.stChargeName = MapUtils.getString(entityMap, "stChargeName");
            }
            if (entityMap.containsKey("stChargeSno")) {
                this.stChargeSno = MapUtils.getString(entityMap, "stChargeSno");
            }
            if (entityMap.containsKey("stChargePhone")) {
                this.stChargePhone = MapUtils.getString(entityMap, "stChargePhone");
            }
            if (entityMap.containsKey("stFounder")) {
                this.stFounder = MapUtils.getString(entityMap, "stFounder");
            }
            if (entityMap.containsKey("stNature")) {
                this.stNature = MapUtils.getString(entityMap, "stNature");
            }
            if (entityMap.containsKey("stDesc")) {
                this.stDesc = MapUtils.getString(entityMap, "stDesc");
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
            if (entityMap.containsKey("schoolNo")) {
                this.schoolNo = MapUtils.getString(entityMap, "schoolNo");
            }
            if (entityMap.containsKey("collegeNo")) {
                this.collegeNo = MapUtils.getString(entityMap, "collegeNo");
            }
        }
    }
}
