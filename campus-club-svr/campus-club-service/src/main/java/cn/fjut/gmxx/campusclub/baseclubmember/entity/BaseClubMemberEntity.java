package cn.fjut.gmxx.campusclub.baseclubmember.entity;

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
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-02-22 新建
* ----------------------------------------------
*
*/
@Entity
@Table(name = "base_club_member")
@SequenceGenerator(name="seq", allocationSize=1, initialValue=1, sequenceName="SEQ_base_club_member")
public class BaseClubMemberEntity extends BaseEntity implements Serializable{

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
    * 社员编号
    */
    @Column(name = "MEMBER_CD")
    private String memberCd;

    public String getMemberCd() {
    	return memberCd;
    }
    public void setMemberCd(String memberCd) {
    	this.memberCd = memberCd;
    }
    /**
    * 社员名字
    */
    @Column(name = "MEMBER_NAME")
    private String memberName;

    public String getMemberName() {
    	return memberName;
    }
    public void setMemberName(String memberName) {
    	this.memberName = memberName;
    }
    /**
    * 社员学号
    */
    @Column(name = "MEMBER_SNO")
    private String memberSno;

    public String getMemberSno() {
    	return memberSno;
    }
    public void setMemberSno(String memberSno) {
    	this.memberSno = memberSno;
    }
    /**
    * 所属社团编号
    */
    @Column(name = "ST_CD")
    private String stCd;

    public String getStCd() {
    	return stCd;
    }
    public void setStCd(String stCd) {
    	this.stCd = stCd;
    }
    /**
    * 所属部门编号
    */
    @Column(name = "ST_DEPART_CD")
    private String stDepartCd;

    public String getStDepartCd() {
    	return stDepartCd;
    }
    public void setStDepartCd(String stDepartCd) {
    	this.stDepartCd = stDepartCd;
    }
    /**
    * 职位类型
    */
    @Column(name = "POSITION_CD")
    private String positionCd;

    public String getPositionCd() {
    	return positionCd;
    }
    public void setPositionCd(String positionCd) {
    	this.positionCd = positionCd;
    }
    /**
    * 性别
    */
    @Column(name = "SEX")
    private String sex;

    public String getSex() {
    	return sex;
    }
    public void setSex(String sex) {
    	this.sex = sex;
    }
    /**
    * 班级年级
    */
    @Column(name = "CLASS")
    private String classes;

    public String getClasses() {
    	return classes;
    }
    public void setClasses(String classes) {
    	this.classes = classes;
    }
    /**
    * 专业
    */
    @Column(name = "MAJOR")
    private String major;

    public String getMajor() {
    	return major;
    }
    public void setMajor(String major) {
    	this.major = major;
    }
    /**
    * 手机号
    */
    @Column(name = "PHONE")
    private String phone;

    public String getPhone() {
    	return phone;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("memberCd")) {
                this.memberCd = MapUtils.getString(entityMap, "memberCd");
            }
            if (entityMap.containsKey("memberName")) {
                this.memberName = MapUtils.getString(entityMap, "memberName");
            }
            if (entityMap.containsKey("memberSno")) {
                this.memberSno = MapUtils.getString(entityMap, "memberSno");
            }
            if (entityMap.containsKey("stCd")) {
                this.stCd = MapUtils.getString(entityMap, "stCd");
            }
            if (entityMap.containsKey("stDepartCd")) {
                this.stDepartCd = MapUtils.getString(entityMap, "stDepartCd");
            }
            if (entityMap.containsKey("positionCd")) {
                this.positionCd = MapUtils.getString(entityMap, "positionCd");
            }
            if (entityMap.containsKey("sex")) {
                this.sex = MapUtils.getString(entityMap, "sex");
            }
            if (entityMap.containsKey("classes")) {
                this.classes = MapUtils.getString(entityMap, "classes");
            }
            if (entityMap.containsKey("stNature")) {
                this.major = MapUtils.getString(entityMap, "major");
            }
            if (entityMap.containsKey("phone")) {
                this.phone = MapUtils.getString(entityMap, "phone");
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
