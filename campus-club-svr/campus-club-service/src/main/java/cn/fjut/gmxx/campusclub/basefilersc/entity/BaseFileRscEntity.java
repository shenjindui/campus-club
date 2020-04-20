package cn.fjut.gmxx.campusclub.basefilersc.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
* @类名称 BaseFileRscEntity
* @类描述 <pre>请填写</pre>
* @作者 shenjindui V1.0
* @创建时间 2020-01-21
* @版本 vV1.0
* @修改记录
*
* 版本 修改人 修改时间 修改内容描述
* ----------------------------------------------
* V1.0 shenjindui 2020-01-21 新建
* ----------------------------------------------
*
*/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "base_file_rsc")
//@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class BaseFileRscEntity extends BaseEntity implements Serializable{

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
    * 文件编号
    */
    @Column(name = "FILE_ID",columnDefinition = "varchar(32) COMMENT '文件编号'")
    private String fileId;

    public String getFileId() {
    	return fileId;
    }
    public void setFileId(String fileId) {
    	this.fileId = fileId;
    }
    /**
    * 文件名称
    */
    @Column(name = "FILE_NM",columnDefinition = "varchar(255) COMMENT '文件名称'")
    private String fileNm;

    public String getFileNm() {
    	return fileNm;
    }
    public void setFileNm(String fileNm) {
    	this.fileNm = fileNm;
    }
    /**
    * 文件扩展名
    */
    @Column(name = "FILE_EXPD_NM",columnDefinition = "varchar(32) COMMENT '文件扩展名'")
    private String fileExpdNm;

    public String getFileExpdNm() {
    	return fileExpdNm;
    }
    public void setFileExpdNm(String fileExpdNm) {
    	this.fileExpdNm = fileExpdNm;
    }
    /**
    * 文件大小
    */
    @Column(name = "FILE_SIZE",columnDefinition = "varchar(32) COMMENT '文件大小'")
    private BigDecimal fileSize;

    public BigDecimal getFileSize() {
    	return fileSize;
    }
    public void setFileSize(BigDecimal fileSize) {
    	this.fileSize = fileSize;
    }
    /**
    * 文件路径
    */
    @Column(name = "FILE_RTE",columnDefinition = "varchar(255) COMMENT '文件路径'")
    private String fileRte;

    public String getFileRte() {
    	return fileRte;
    }
    public void setFileRte(String fileRte) {
    	this.fileRte = fileRte;
    }
    /**
    * 文件类型 
    */
    @Column(name = "FILE_TP_CD",columnDefinition = "varchar(32) COMMENT '文件类型'")
    private String fileTpCd;

    public String getFileTpCd() {
    	return fileTpCd;
    }
    public void setFileTpCd(String fileTpCd) {
    	this.fileTpCd = fileTpCd;
    }
    /**
    * 文件用途类型  1 2 3 4 5
    */
    @Column(name = "FILE_PURPOSE",columnDefinition = "varchar(32) COMMENT '文件用途类型'")
    private String filePurpose;

    public String getFilePurpose() {
    	return filePurpose;
    }
    public void setFilePurpose(String filePurpose) {
    	this.filePurpose = filePurpose;
    }
    /**
    * 创建人编号 关联工号
    */
    @Column(name = "CREATE_USER_ID",columnDefinition = "varchar(32) COMMENT '创建人编号 关联工号'")
    private String createUserId;

    public String getCreateUserId() {
    	return createUserId;
    }
    public void setCreateUserId(String createUserId) {
    	this.createUserId = createUserId;
    }

    @Column(name = "ST_CD",columnDefinition = "varchar(32) COMMENT '文件所属社团编号'")
    private String stCd;

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }
}
