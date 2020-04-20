package cn.fjut.gmxx.campusclub.baseclubnotice.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
* @类名称 BaseClubNoticeEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V
* @创建时间 2020-02-24
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V shenjindui 2020-02-24 新建
* ----------------------------------------------
*
*/
@Entity
@Table(name = "base_club_notice")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseClubNoticeEntity extends BaseEntity implements Serializable{

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
    * 公告编号
    */
    @Column(name = "NOTICE_CD",columnDefinition = "varchar(32) COMMENT '公告编号'")
    private String noticeCd;

    public String getNoticeCd() {
    	return noticeCd;
    }
    public void setNoticeCd(String noticeCd) {
    	this.noticeCd = noticeCd;
    }
    /**
    * 公告内容
    */
    @Column(name = "NOTICE_DESC",columnDefinition = "varchar(1024) COMMENT '公告内容'")
    private String noticeDesc;

    public String getNoticeDesc() {
    	return noticeDesc;
    }
    public void setNoticeDesc(String noticeDesc) {
    	this.noticeDesc = noticeDesc;
    }
    /**
    * 公告所属社团编号
    */
    @Column(name = "NOTICE_ST_CD",columnDefinition = "varchar(32) COMMENT '公告所属社团编号'")
    private String noticeStCd;

    public String getNoticeStCd() {
    	return noticeStCd;
    }
    public void setNoticeStCd(String noticeStCd) {
    	this.noticeStCd = noticeStCd;
    }
    /**
    * 发布人
    */
    @Column(name = "PUBLISHER",columnDefinition = "varchar(32) COMMENT '发布人'")
    private String publisher;

    public String getPublisher() {
    	return publisher;
    }
    public void setPublisher(String publisher) {
    	this.publisher = publisher;
    }
    /**
    * 发布时间
    */
    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(name = "RELEASE_TIME",columnDefinition = "datetime COMMENT '发布人'")
    private java.util.Date releaseTime;

    public java.util.Date getReleaseTime() {
    	return releaseTime;
    }
    public void setReleaseTime(java.util.Date releaseTime) {
    	this.releaseTime = releaseTime;
    }
    /**
    * 公告状态
    */
    @Column(name = "NOTICE_STATUS",columnDefinition = "varchar(32) COMMENT '公告状态'")
    private String noticeStatus;

    public String getNoticeStatus() {
    	return noticeStatus;
    }
    public void setNoticeStatus(String noticeStatus) {
    	this.noticeStatus = noticeStatus;
    }
    /**
    * 公告访问量
    */
    @Column(name = "TRAFFIC_VOLUME",columnDefinition = "varchar(32) COMMENT '公告访问量'")
    private String trafficVolume;

    public String getTrafficVolume() {
    	return trafficVolume;
    }
    public void setTrafficVolume(String trafficVolume) {
    	this.trafficVolume = trafficVolume;
    }
    /**
     *
     * @param entityMap
     */
    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("noticeCd")) {
                this.noticeCd = MapUtils.getString(entityMap, "noticeCd");
            }
            if (entityMap.containsKey("noticeDesc")) {
                this.noticeDesc = MapUtils.getString(entityMap, "noticeDesc");
            }
            if (entityMap.containsKey("noticeStCd")) {
                this.noticeStCd = MapUtils.getString(entityMap, "noticeStCd");
            }
            if (entityMap.containsKey("publisher")) {
                this.publisher = MapUtils.getString(entityMap, "publisher");
            }
            if (entityMap.containsKey("trafficVolume")) {
                this.trafficVolume = MapUtils.getString(entityMap, "trafficVolume");
            }
            if (entityMap.containsKey("releaseTime")) {
                //this.releaseTime = MapUtils.getString(entityMap, "releaseTime");
            }
            if (entityMap.containsKey("noticeStatus")) {
                this.noticeStatus = MapUtils.getString(entityMap, "noticeStatus");
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
