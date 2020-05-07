package cn.fjut.gmxx.campusclub.baseclubscore.entity;

import cn.fjut.gmxx.campusclub.common.PrimaryKey;
import cn.fjut.gmxx.campusclub.data.BaseEntity;
import org.apache.commons.collections.MapUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
* @类名称 BaseClubMemberEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1
* @创建时间 2020-02-22
* @版本 V1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
@Entity
@Table(name = "base_club_score")
@SequenceGenerator(name="seq", allocationSize=1, initialValue=1, sequenceName="base_club_score")
public class BaseClubScoreEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @PrimaryKey
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
    * 所属社团编号
    */
    @Column(name = "ST_CD",columnDefinition = "varchar(32) COMMENT '所属社团编号'")
    private String stCd;


    /**
    * 得分
    */
    @Column(name = "SCORE",columnDefinition = "varchar(32) COMMENT '得分'")
    private String score;

    /**
    * 评分人员编号
    */
    @Column(name = "RATERS_CODE",columnDefinition = "varchar(32) COMMENT '评分人员编号'")
    private String ratersCode;

    /**
     * 评分人员名称
     */
    @Column(name = "RATERS_NAME",columnDefinition = "varchar(32) COMMENT '评分人员名称'")
    private String ratersName;

    /**
     * 评分人员评分意见
     */
    @Column(name = "RATERS_OPIN",columnDefinition = "varchar(32) COMMENT '评分人员评分意见'")
    private String ratersOpin;

    /**
     * 被评审对象关联编号 （社长评审社员则关联用户工号） （社联或团委评审社团则关联社团编号）
     */
    @Column(name = "RATERS_ASSOCIATION_CODE",columnDefinition = "varchar(32) COMMENT '被评审对象关联编号'")
    private String ratersAssociationCode;
    /**
     * 被评审对象关联名称 社团名称或者社员姓名
     */
    @Column(name = "RATERS_ASSOCIATION_NAME",columnDefinition = "varchar(32) COMMENT '被评审对象关联名称'")
    private String ratersAssociationName;

    /**
     * 评审类型代码 0 社长评审社员  1  社联评审社团  2  团委评审社团
     */
    @Column(name = "RATERS_TYPE",columnDefinition = "varchar(32) COMMENT '评审类型代码'")
    private String ratersType;

    public String getRatersType() {
        return ratersType;
    }

    public void setRatersType(String ratersType) {
        this.ratersType = ratersType;
    }

    /**
     * 评分学年 2018-2019 学年 2019-2020学年
     */
    @Column(name = "SCHOOL_YEAR",columnDefinition = "varchar(64) COMMENT '评分学年'")
    private String schoolYear;

    /**
     * 评分学期  一 二
     */
    @Column(name = "SEMESTERS",columnDefinition = "varchar(16) COMMENT '评分学期'")
    private String emesters;

    /**
     * 评分状态  0处理中  1 处理完成
     */
    @Column(name = "RATERS_PSCCD",columnDefinition = "varchar(32) COMMENT '评分状态  0处理中  1 处理完成'")
    private String ratersPsccd;

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRatersCode() {
        return ratersCode;
    }

    public void setRatersCode(String ratersCode) {
        this.ratersCode = ratersCode;
    }

    public String getRatersName() {
        return ratersName;
    }

    public void setRatersName(String ratersName) {
        this.ratersName = ratersName;
    }

    public String getRatersOpin() {
        return ratersOpin;
    }

    public void setRatersOpin(String ratersOpin) {
        this.ratersOpin = ratersOpin;
    }


    public String getRatersAssociationCode() {
        return ratersAssociationCode;
    }

    public void setRatersAssociationCode(String ratersAssociationCode) {
        this.ratersAssociationCode = ratersAssociationCode;
    }

    public String getRatersAssociationName() {
        return ratersAssociationName;
    }

    public void setRatersAssociationName(String ratersAssociationName) {
        this.ratersAssociationName = ratersAssociationName;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getEmesters() {
        return emesters;
    }

    public void setEmesters(String emesters) {
        this.emesters = emesters;
    }

    public String getRatersPsccd() {
        return ratersPsccd;
    }

    public void setRatersPsccd(String ratersPsccd) {
        this.ratersPsccd = ratersPsccd;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("ratersType")) {
                this.ratersType = MapUtils.getString(entityMap, "ratersType");
            }
            if (entityMap.containsKey("ratersPsccd")) {
                this.ratersPsccd = MapUtils.getString(entityMap, "ratersPsccd");
            }
            if (entityMap.containsKey("schoolYear")) {
                this.schoolYear = MapUtils.getString(entityMap, "schoolYear");
            }
            if (entityMap.containsKey("emesters")) {
                this.emesters = MapUtils.getString(entityMap, "emesters");
            }
            if (entityMap.containsKey("ratersAssociationCode")) {
                this.ratersAssociationCode = MapUtils.getString(entityMap, "ratersAssociationCode");
            }
            if (entityMap.containsKey("ratersOpin")) {
                this.ratersOpin = MapUtils.getString(entityMap, "ratersOpin");
            }
            if (entityMap.containsKey("ratersAssociationName")) {
                this.ratersAssociationName = MapUtils.getString(entityMap, "ratersAssociationName");
            }

            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("stCd")) {
                this.stCd = MapUtils.getString(entityMap, "stCd");
            }
            if (entityMap.containsKey("score")) {
                this.score = MapUtils.getString(entityMap, "score");
            }
            if (entityMap.containsKey("ratersCode")) {
                this.ratersCode = MapUtils.getString(entityMap, "ratersCode");
            }
            if (entityMap.containsKey("ratersName")) {
                this.ratersName = MapUtils.getString(entityMap, "ratersName");
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
