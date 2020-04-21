package cn.fjut.gmxx.campusclub.data;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity  implements Serializable {
    private static final long serialVersionUID = 3324292779427167491L;

    //8个字段
    @Column(
            name = "STATUS_CD",
            columnDefinition = "int default 1 COMMENT '是否有效'"
    )
    protected int statusCd;

    @Column(
            name = "remark",
            columnDefinition = "varchar(400) COMMENT '备注'"
    )
    protected String remark;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(
            name = "CREATE_TIME",columnDefinition="datetime  COMMENT '创建时间'"
    )
    /*@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")*/
    @CreatedDate
    protected Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @Column(
            name = "UPDATE_TIME",columnDefinition="datetime COMMENT '更新时间'"
    )
    /*@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")*/
    protected Date updateTime;

    @Column(
            name = "CREATE_USER",columnDefinition="varchar(32) COMMENT '创建者'"
    )
    protected String createUser;

    @Column(
            name = "UPDATE_USER",columnDefinition="varchar(32) COMMENT '更新者'"
    )
    protected String updateUser;

    @Column(
            name = "DEL_IND",
            columnDefinition = "varchar(1) default '0' COMMENT '是否删除'"
    )
    protected String delInd;

    @Version
    @Column(
            name = "VERSION",columnDefinition = "int default 1 COMMENT '版本'",
            length = 10
    )
    protected int version;

    public BaseEntity() {
    }

    public int getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(int statusCd) {
        this.statusCd = statusCd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDelInd() {
        return delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
