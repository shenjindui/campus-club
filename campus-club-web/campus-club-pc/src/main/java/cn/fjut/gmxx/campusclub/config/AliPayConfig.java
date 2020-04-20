package cn.fjut.gmxx.campusclub.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝支付设置
 * @author jitwxs
 * @since 2018/6/4 19:04
 */
@Configuration
public class AliPayConfig {
    @Value("${alipay.gateway_url}")
    private String gatewayUrl;
    @Value("${alipay.app_id}")
    private String appId;
    @Value("${alipay.merchant_private_key}")
    private String merchantPrivateKey;
    @Value("${alipay.alipay_public_key}")
    private String alipayPublicKey;
    @Value("${alipay.sign_type}")
    private String signType;
    @Value("${alipay.uid}")
    private String sellerId;
    @Value("${alipay.notify_url}")
    private String notifyUrl;
    @Value("${alipay.return_url}")
    private String returnUrl;

    @Bean
    AlipayClient alipayClient() {
        return new DefaultAlipayClient
                (gatewayUrl, appId, merchantPrivateKey, "json", "utf-8", alipayPublicKey, signType);
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
