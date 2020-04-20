package cn.fjut.gmxx.campusclub.baseclubactivity.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
* @类名称 BaseClubActivityEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-02-08
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-08 新建
* ----------------------------------------------
*
*/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "base_club_activity")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseClubActivityEntity extends BaseEntity implements Serializable{

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
    * 活动编号
    */
    @Column(name = "ACTIVITY_ID",columnDefinition = "varchar(32) COMMENT '活动编号'")
    private String activityId;

    public String getActivityId() {
    	return activityId;
    }
    public void setActivityId(String activityId) {
    	this.activityId = activityId;
    }
    /**
    * 活动名称
    */
    @Column(name = "ACTIVITY_NAME",columnDefinition = "varchar(255) COMMENT '活动名称'")
    private java.lang.String activityName;

    public java.lang.String getActivityName() {
    	return activityName;
    }
    public void setActivityName(java.lang.String activityName) {
    	this.activityName = activityName;
    }
    /**
    * 主办社团编号
    */
    @Column(name = "HOST_ST_CD",columnDefinition = "varchar(255) COMMENT '主办社团编号'")
    private String hostStCd;

    public String getHostStCd() {
    	return hostStCd;
    }
    public void setHostStCd(String hostStCd) {
    	this.hostStCd = hostStCd;
    }
    /**
    * 预计活动开始时间
    */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(name = "START_TIME",columnDefinition = "datetime COMMENT '预计活动开始时间'")
    private Date startTime;


    public Date getStartTime() {
    	return startTime;
    }
    public void setStartTime(Date startTime) {
    	this.startTime = startTime;
    }
    /**
    * 预计活动结束时间
    */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(name = "END_TIME",columnDefinition = "datetime COMMENT '预计结束开始时间'")
    private Date endTime;

    public Date getEndTime() {
    	return endTime;
    }
    public void setEndTime(Date endTime) {
    	this.endTime = endTime;
    }
    /**
    * 活动地点
    */
    @Column(name = "ACTIVITY_SPACE",columnDefinition = "varchar(255) COMMENT '活动地点'")
    private String activitySpace;

    public String getActivitySpace() {
    	return activitySpace;
    }
    public void setActivitySpace(String activitySpace) {
    	this.activitySpace = activitySpace;
    }
    /**
    * 活动资金预算
    */
    @Column(name = "FOUNDS_NUM",columnDefinition = "varchar(128) COMMENT '活动资金预算'")
    private String foundsNum;

    public String getFoundsNum() {
    	return foundsNum;
    }
    public void setFoundsNum(String foundsNum) {
    	this.foundsNum = foundsNum;
    }
    /**
    * 活动策划是否通过
    */
    @Column(name = "PROPOSA_AGREE",columnDefinition = "varchar(1) COMMENT '活动策划是否通过'")
    private String proposaAgree;

    public String getProposaAgree() {
    	return proposaAgree;
    }
    public void setProposaAgree(String proposaAgree) {
    	this.proposaAgree = proposaAgree;
    }
    /**
    * 社联是否审批通过
    */
    @Column(name = "ASSOCIATION_AGREE",columnDefinition = "varchar(1) COMMENT '社联是否审批通过'")
    private String associationAgree;

    public String getAssociationAgree() {
    	return associationAgree;
    }
    public void setAssociationAgree(String associationAgree) {
    	this.associationAgree = associationAgree;
    }
    /**
    * 团委是否审批通过
    */
    @Column(name = "YOUTH_LEAGUE_AGREE",columnDefinition = "varchar(1) COMMENT '团委是否审批通过'")
    private String youthLeagueAgree;

    public String getYouthLeagueAgree() {
    	return youthLeagueAgree;
    }
    public void setYouthLeagueAgree(String youthLeagueAgree) {
    	this.youthLeagueAgree = youthLeagueAgree;
    }
    /**
    * 活动考核人
    */
    @Column(name = "ACTIVITY_ASSESSOR",columnDefinition = "varchar(32) COMMENT '活动考核人'")
    private String activityAssessor;

    public String getActivityAssessor() {
    	return activityAssessor;
    }
    public void setActivityAssessor(String activityAssessor) {
    	this.activityAssessor = activityAssessor;
    }
    /**
    * 活动得分
    */
    @Column(name = "ACTIVITY_SCORE",columnDefinition = "varchar(32) COMMENT '活动得分'")
    private String activityScore;

    public String getActivityScore() {
    	return activityScore;
    }
    public void setActivityScore(String activityScore) {
    	this.activityScore = activityScore;
    }
    /**
    * 活动类型
    */
    @Column(name = "ACTIVITY_TYPE",columnDefinition = "varchar(32) COMMENT '活动类型'")
    private String activityType;

    public String getActivityType() {
    	return activityType;
    }
    public void setActivityType(String activityType) {
    	this.activityType = activityType;
    }
    /**
    * 活动内容
    */
    @Column(name = "ACTIVITY_DSC",columnDefinition = "varchar(255) COMMENT '活动内容'")
    private String activityDsc;

    public String getActivityDsc() {
    	return activityDsc;
    }
    public void setActivityDsc(String activityDsc) {
    	this.activityDsc = activityDsc;
    }
    /**
    * 活动附件说明文件 地址
    */
    @Column(name = "ACTIVITY_FILE",columnDefinition = "varchar(255) COMMENT '活动附件说明文件 地址'")
    private String activityFile;

    public String getActivityFile() {
    	return activityFile;
    }
    public void setActivityFile(String activityFile) {
    	this.activityFile = activityFile;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("activityId")) {
                this.activityId = MapUtils.getString(entityMap, "activityId");
            }
            if (entityMap.containsKey("activityName")) {
                this.activityName = MapUtils.getString(entityMap, "activityName");
            }
            if (entityMap.containsKey("hostStCd")) {
                this.hostStCd = MapUtils.getString(entityMap, "hostStCd");
            }
            if (entityMap.containsKey("startTime")) {
               // this.startTime = MapUtils.getD(entityMap, "startTime");
            }
            if (entityMap.containsKey("endTime")) {
                //this.endTime = MapUtils.getString(entityMap, "endTime");
            }
            if (entityMap.containsKey("activitySpace")) {
                this.activitySpace = MapUtils.getString(entityMap, "activitySpace");
            }
            if (entityMap.containsKey("foundsNum")) {
                this.foundsNum = MapUtils.getString(entityMap, "foundsNum");
            }
            if (entityMap.containsKey("proposaAgree")) {
                this.proposaAgree = MapUtils.getString(entityMap, "proposaAgree");
            }
            if (entityMap.containsKey("associationAgree")) {
                this.associationAgree = MapUtils.getString(entityMap, "associationAgree");
            }
            if (entityMap.containsKey("activityAssessor")) {
                this.activityAssessor = MapUtils.getString(entityMap, "activityAssessor");
            }
            if (entityMap.containsKey("activityScore")) {
                this.activityScore = MapUtils.getString(entityMap, "activityScore");
            }
            if (entityMap.containsKey("youthLeagueAgree")) {
                this.youthLeagueAgree = MapUtils.getString(entityMap, "youthLeagueAgree");
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
            if (entityMap.containsKey("activityType")) {
                this.activityType = MapUtils.getString(entityMap, "activityType");
            }
            if (entityMap.containsKey("activityDsc")) {
                this.activityDsc = MapUtils.getString(entityMap, "activityDsc");
            }
            if (entityMap.containsKey("activityFile")) {
                this.activityFile = MapUtils.getString(entityMap, "activityFile");
            }
        }
    }

}
