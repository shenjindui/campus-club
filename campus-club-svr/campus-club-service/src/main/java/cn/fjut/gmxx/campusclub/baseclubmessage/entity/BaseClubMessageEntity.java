package cn.fjut.gmxx.campusclub.baseclubmessage.entity;

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
@Table(name = "base_club_message")
@SequenceGenerator(name="seq", allocationSize=1, initialValue=1, sequenceName="base_club_message")
public class BaseClubMessageEntity extends BaseEntity implements Serializable{

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
    * 留言编号
    */
    @Column(name = "MESSAGE_CD",columnDefinition = "varchar(32) COMMENT '留言编号'")
    private String messageCd;


    /**
    * 留言内容
    */
    @Column(name = "MESSAGE_DESC",columnDefinition = "varchar(255) COMMENT '留言内容'")
    private String messageDesc;

    /**
    * 留言人名字
    */
    @Column(name = "MESSAGE_NAME",columnDefinition = "varchar(32) COMMENT '留言人名字'")
    private String messageName;

    /**
    * 留言学号、工号
    */
    @Column(name = "MESSAGE_SNO",columnDefinition = "varchar(32) COMMENT '留言学号、工号'")
    private String messagSno;

    /**
    * 留言社团编号
    */
    @Column(name = "MESSAGE_ST_CD",columnDefinition = "varchar(32) COMMENT '留言社团编号'")
    private String messageStCd;

    /**
     * 留言状态  0处理中 提交中 1 处理完成
     */
    @Column(name = "MESSAGE_PSCCD",columnDefinition = "varchar(2) COMMENT '留言状态  0处理中 提交中 1 处理完成'")
    private String messagePsccd;

    /**
     * 留言处理意见
     */
    @Column(name = "MESSAGE_PSC_OPIN",columnDefinition = "varchar(255) COMMENT '留言处理意见'")
    private String messagePscOpin;

    /**
     * 留言处理人编号
     */
    @Column(name = "MESSAGE_PSC_CODE",columnDefinition = "varchar(32) COMMENT '留言处理人编号'")
    private String messagePscCode;

    /**
     * 留言处理人姓名
     */
    @Column(name = "MESSAGE_PSC_name",columnDefinition = "varchar(32) COMMENT '留言处理人姓名'")
    private String messagePscName;

    public String getMessagePsccd() {
        return messagePsccd;
    }

    public void setMessagePsccd(String messagePsccd) {
        this.messagePsccd = messagePsccd;
    }

    public String getMessagePscOpin() {
        return messagePscOpin;
    }

    public void setMessagePscOpin(String messagePscOpin) {
        this.messagePscOpin = messagePscOpin;
    }

    public String getMessagePscCode() {
        return messagePscCode;
    }

    public void setMessagePscCode(String messagePscCode) {
        this.messagePscCode = messagePscCode;
    }

    public String getMessagePscName() {
        return messagePscName;
    }

    public void setMessagePscName(String messagePscName) {
        this.messagePscName = messagePscName;
    }

    public String getMessageCd() {
        return messageCd;
    }

    public void setMessageCd(String messageCd) {
        this.messageCd = messageCd;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessagSno() {
        return messagSno;
    }

    public void setMessagSno(String messagSno) {
        this.messagSno = messagSno;
    }

    public String getMessageStCd() {
        return messageStCd;
    }

    public void setMessageStCd(String messageStCd) {
        this.messageStCd = messageStCd;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("messagePsccd")) {
                this.messagePsccd = MapUtils.getString(entityMap, "messagePsccd");
            }
            if (entityMap.containsKey("messagePscOpin")) {
                this.messagePscOpin = MapUtils.getString(entityMap, "messagePscOpin");
            }
            if (entityMap.containsKey("messagePscCode")) {
                this.messagePscCode = MapUtils.getString(entityMap, "messagePscCode");
            }
            if (entityMap.containsKey("messagePscName")) {
                this.messagePscName = MapUtils.getString(entityMap, "messagePscName");
            }
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("messageCd")) {
                this.messageCd = MapUtils.getString(entityMap, "messageCd");
            }
            if (entityMap.containsKey("messageDesc")) {
                this.messageDesc = MapUtils.getString(entityMap, "messageDesc");
            }
            if (entityMap.containsKey("messageName")) {
                this.messageName = MapUtils.getString(entityMap, "messageName");
            }
            if (entityMap.containsKey("messagSno")) {
                this.messagSno = MapUtils.getString(entityMap, "messagSno");
            }
            if (entityMap.containsKey("messageStCd")) {
                this.messageStCd = MapUtils.getString(entityMap, "messageStCd");
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
