package cn.fjut.gmxx.campusclub.baseclubnews.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
* @类名称 BaseClubNewsEntity
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
@Table(name = "base_club_news")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseClubNewsEntity extends BaseEntity implements Serializable{

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
    * 新闻编号
    */
    @Column(name = "NEWS_CD",columnDefinition = "varchar(32) COMMENT '新闻编号'")
    private String newsCd;

    public String getNewsCd() {
    	return newsCd;
    }
    public void setNewsCd(String newsCd) {
    	this.newsCd = newsCd;
    }
    /**
    * 新闻标题
    */
    @Column(name = "NEWS_TITLE",columnDefinition = "varchar(32) COMMENT '新闻标题'")
    private String newsTitle;

    public String getNewsTitle() {
    	return newsTitle;
    }
    public void setNewsTitle(String newsTitle) {
    	this.newsTitle = newsTitle;
    }
    /**
    * 新闻内容
    */
    @Column(name = "NEWS_DESC",columnDefinition = "varchar(1024) COMMENT '新闻内容'")
    private String newsDesc;

    public String getNewsDesc() {
    	return newsDesc;
    }
    public void setNewsDesc(String newsDesc) {
    	this.newsDesc = newsDesc;
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(name = "PUBLISH_TIME",columnDefinition = "datetime COMMENT '发布时间'")
    private java.util.Date publishTime;

    public java.util.Date getPublishTime() {
    	return publishTime;
    }
    public void setPublishTime(java.util.Date publishTime) {
    	this.publishTime = publishTime;
    }
    /**
    * 新闻状态
    */
    @Column(name = "NEWS_STATUS",columnDefinition = "varchar(16) COMMENT '新闻状态'")
    private String newsStatus;

    public String getNewsStatus() {
    	return newsStatus;
    }
    public void setNewsStatus(String newsStatus) {
    	this.newsStatus = newsStatus;
    }
    /**
    * 访问量
    */
    @Column(name = "TRAFFIC_VOLUME",columnDefinition = "varchar(32) COMMENT '访问量'")
    private String trafficVolume;

    public String getTrafficVolume() {
    	return trafficVolume;
    }
    public void setTrafficVolume(String trafficVolume) {
    	this.trafficVolume = trafficVolume;
    }
    /**
    * 新闻所属社团编号
    */
    @Column(name = "NEWS_ST_CD",columnDefinition = "varchar(32) COMMENT '新闻所属社团编号'")
    private String newsStCd;

    public String getNewsStCd() {
    	return newsStCd;
    }
    public void setNewsStCd(String newsStCd) {
    	this.newsStCd = newsStCd;
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
            if (entityMap.containsKey("newsCd")) {
                this.newsCd = MapUtils.getString(entityMap, "newsCd");
            }
            if (entityMap.containsKey("newsTitle")) {
                this.newsTitle = MapUtils.getString(entityMap, "newsTitle");
            }
            if (entityMap.containsKey("newsDesc")) {
                this.newsDesc = MapUtils.getString(entityMap, "newsDesc");
            }
            if (entityMap.containsKey("publisher")) {
                this.publisher = MapUtils.getString(entityMap, "publisher");
            }
            if (entityMap.containsKey("publishTime")) {
                //this.publishTime = MapUtils.getString(entityMap, "publishTime");
            }
            if (entityMap.containsKey("newsStatus")) {
                this.newsStatus = MapUtils.getString(entityMap, "newsStatus");
            }
            if (entityMap.containsKey("trafficVolume")) {
                this.trafficVolume = MapUtils.getString(entityMap, "trafficVolume");
            }
            if (entityMap.containsKey("newsStCd")) {
                this.newsStCd = MapUtils.getString(entityMap, "newsStCd");
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
            if (entityMap.containsKey("newsStCd")) {
                this.newsStCd = MapUtils.getString(entityMap, "newsStCd");
            }
        }
    }

}
