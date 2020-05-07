package cn.fjut.gmxx.campusclub.sysdepartment.entity;

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : shenjindui
 * @date : 2020-01-10 22:49
 **/
    @Entity
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @Table(name = "sys_department")
    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class SysDepartment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 16)
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    /**
     * 部门编号
     */
    @Column(name = "DEPT_CODE", columnDefinition = "varchar(32) COMMENT '部门编号'")
    private String deptCode;

    /**
     * 部门名
     */
    @Column(name = "DEPT_NAME", columnDefinition = "varchar(50) COMMENT '部门名'")
    private String deptName;

    /**
     * 父部门编号
     */
    @Column(name = "PARENT_DEPT_CODE", columnDefinition = "varchar(32) COMMENT '父部门编号'")
    private String parentDeptCode;

    /**
     *部门级别(一级1二级2三级3四级4)
     */
    @Column(name = "DEPT_LEVEL_CD", columnDefinition = "varchar(32) COMMENT '部门级别(一级1二级2三级3四级4)'")
    private String deptLevelCd;

    /**
     *
     */
    @Column(name = "SORT", columnDefinition = "varchar(32) COMMENT '排序'")
    private String sort;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(String parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public String getDeptLevelCd() {
        return deptLevelCd;
    }

    public void setDeptLevelCd(String deptLevelCd) {
        this.deptLevelCd = deptLevelCd;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}