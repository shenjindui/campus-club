package cn.fjut.gmxx.campusclub.baseclubfunds.entity;/**
 * Created by admin on 2020/3/21.
 */

import cn.fjut.gmxx.campusclub.data.BaseEntity;
import lombok.*;
import org.apache.commons.collections.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-03-21 20:05
 **/
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "base_club_funds")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseClubFundsEntity extends BaseEntity implements Serializable {
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
     * 财务编号
     */
    @Column(name = "FUNDS_CD",columnDefinition = "varchar(32) COMMENT '财务编号'")
    private String fundsCd;

    public String getFundsCd() {
        return fundsCd;
    }

    public void setFundsCd(String fundsCd) {
        this.fundsCd = fundsCd;
    }

    /**
     * 社团编号
     */
    @Column(name = "ST_CD",columnDefinition = "varchar(32) COMMENT '财务所属社团编号'")
    private String stCd;

    /**
     * 财务类型  1.社团社费缴纳 2.社团活动支出  3.社团购买器材 4.其他 ... 5 社团社费发布 活动缴费发布等等
     */
    @Column(name = "TYPE",columnDefinition = "varchar(32) COMMENT '财务操作类型'")
    private String type;

    /**
     * 金额
     */
    @Column(name = "AMOUNT",columnDefinition = "varchar(64) COMMENT '金额'")
    private String amount;

    /**
     * 金额类型  + 收入 -支出
     */
    @Column(name = "AMOUNT_TYPE",columnDefinition = "varchar(32) COMMENT '金额类型'")
    private String amountType;

    /**
     * 财务关联编号 ，比如社员缴费则关联该社员的人员编号
     */
    @Column(name = "FUNDS_ASSOCIATION_CODE",columnDefinition = "varchar(32) COMMENT '财务关联编号'")
    private String fundsAssociationCode;

    @Column(name = "FUNDS_ASSOCIATION_FUNDSCODE",columnDefinition = "varchar(32) COMMENT '财务关联的财务编号'")
    private String fundsAssociationFundscode;

    @Column(name = "FUNDS_PSCCD",columnDefinition = "varchar(32) COMMENT '财务流程(是否支付)'")
    private String fundsPsccd;

    /**
     * 财务订单号
     */
    @Column(name = "ORDER_ID",columnDefinition = "varchar(32) COMMENT '财务支付订单号'")
    private String orderId;

    /**
     * 财务第三方支付交易号
     */
    @Column(name = "THIRD_ORDER_ID",columnDefinition = "varchar(32) COMMENT '财务第三方支付交易号'")
    private String thirdOrderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId;
    }

    public String getFundsAssociationFundscode() {
        return fundsAssociationFundscode;
    }

    public void setFundsAssociationFundscode(String fundsAssociationFundscode) {
        this.fundsAssociationFundscode = fundsAssociationFundscode;
    }

    public String getFundsPsccd() {
        return fundsPsccd;
    }

    public void setFundsPsccd(String fundsPsccd) {
        this.fundsPsccd = fundsPsccd;
    }

    public String getFundsAssociationCode() {
        return fundsAssociationCode;
    }

    public void setFundsAssociationCode(String fundsAssociationCode) {
        this.fundsAssociationCode = fundsAssociationCode;
    }

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public void mapCoverToEntity(Map<String, Object> entityMap) {
        if (entityMap != null && !entityMap.isEmpty()) {
            if (entityMap.containsKey("orderId")) {
                this.orderId = MapUtils.getString(entityMap, "orderId");
            }
            if (entityMap.containsKey("thirdOrderId")) {
                this.thirdOrderId = MapUtils.getString(entityMap, "thirdOrderId");
            }
            if (entityMap.containsKey("fundsAssociationFundscode")) {
                this.fundsAssociationFundscode = MapUtils.getString(entityMap, "fundsAssociationFundscode");
            }
            if (entityMap.containsKey("fundsPsccd")) {
                this.fundsPsccd = MapUtils.getString(entityMap, "fundsPsccd");
            }
            if (entityMap.containsKey("uuid")) {
                this.uuid = MapUtils.getString(entityMap, "uuid");
            }
            if (entityMap.containsKey("stCd")) {
                this.stCd = MapUtils.getString(entityMap, "stCd");
            }
            if (entityMap.containsKey("type")) {
                this.type = MapUtils.getString(entityMap, "type");
            }
            if (entityMap.containsKey("amount")) {
                this.amount = MapUtils.getString(entityMap, "amount");
            }
            if (entityMap.containsKey("amountType")) {
                this.amountType = MapUtils.getString(entityMap, "amountType");
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
            if (entityMap.containsKey("fundsCd")) {
                this.fundsCd = MapUtils.getString(entityMap, "fundsCd");
            }
            if (entityMap.containsKey("fundsAssociationCode")) {
                this.fundsCd = MapUtils.getString(entityMap, "fundsAssociationCode");
            }
        }
    }
}
