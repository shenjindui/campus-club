package cn.fjut.gmxx.campusclub.baseddct.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
* @类名称 BaseDdctEntity
* @类描述 <pre>请填写</pre>
* @作者 sjd V
* @创建时间 2020-01-12
* @版本 vV
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V sjd 2020-01-12 新建
* ----------------------------------------------
*
*/
@Entity
@Table(name = "base_ddct")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseDdctEntity extends BaseEntity implements Serializable{

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
    * 字典键
    */
    @Column(name = "DCT_KEY",columnDefinition = "varchar(32) COMMENT '字典键'")
    private String dctKey;

    public String getDctKey() {
    	return dctKey;
    }
    public void setDctKey(String dctKey) {
    	this.dctKey = dctKey;
    }
    /**
    * 字典类型
    */
    @Column(name = "DCT_TP_CD",columnDefinition = "varchar(32) COMMENT '字典类型'")
    private String dctTpCd;

    public String getDctTpCd() {
    	return dctTpCd;
    }
    public void setDctTpCd(String dctTpCd) {
    	this.dctTpCd = dctTpCd;
    }
    /**
    * 字典类型名称
    */
    @Column(name = "DCT_TP_NM",columnDefinition = "varchar(64) COMMENT '字典类型名称'")
    private String dctTpNm;

    public String getDctTpNm() {
    	return dctTpNm;
    }
    public void setDctTpNm(String dctTpNm) {
    	this.dctTpNm = dctTpNm;
    }
    /**
    * 字典值
    */
    @Column(name = "DCT_VAL",columnDefinition = "varchar(32) COMMENT '字典值'")
    private String dctVal;

    public String getDctVal() {
    	return dctVal;
    }
    public void setDctVal(String dctVal) {
    	this.dctVal = dctVal;
    }
    /**
    * 字典值名称
    */
    @Column(name = "DCT_VAL_NM",columnDefinition = "varchar(64) COMMENT '字典值名称'")
    private String dctValNm;

    public String getDctValNm() {
    	return dctValNm;
    }
    public void setDctValNm(String dctValNm) {
    	this.dctValNm = dctValNm;
    }
    /**
    * 字典描述
    */
    @Column(name = "DCT_DSC",columnDefinition = "varchar(128) COMMENT '字典描述'")
    private String dctDsc;

    public String getDctDsc() {
    	return dctDsc;
    }
    public void setDctDsc(String dctDsc) {
    	this.dctDsc = dctDsc;
    }
    /**
    * 字典排序
    */
    @Column(name = "DCT_SEQ",columnDefinition = "int COMMENT '字典排序'")
    private Integer dctSeq;

    public Integer getDctSeq() {
    	return dctSeq;
    }
    public void setDctSeq(Integer dctSeq) {
    	this.dctSeq = dctSeq;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("dctKey")) {
                this.dctKey = MapUtils.getString(entityMap, "dctKey");
            }
            if (entityMap.containsKey("dctTpCd")) {
                this.dctTpCd = MapUtils.getString(entityMap, "dctTpCd");
            }
            if (entityMap.containsKey("dctTpNm")) {
                this.dctTpNm = MapUtils.getString(entityMap, "dctTpNm");
            }
            if (entityMap.containsKey("dctVal")) {
                this.dctVal = MapUtils.getString(entityMap, "dctVal");
            }
            if (entityMap.containsKey("dctValNm")) {
                this.dctValNm = MapUtils.getString(entityMap, "dctValNm");
            }
            if (entityMap.containsKey("dctDsc")) {
                this.dctDsc = MapUtils.getString(entityMap, "dctDsc");
            }
            if (entityMap.containsKey("dctSeq")) {
                this.dctSeq = MapUtils.getInteger(entityMap, "dctSeq");
            }
            if (entityMap.containsKey("remark")) {
                this.remark = MapUtils.getString(entityMap, "remark");
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
