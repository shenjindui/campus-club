package cn.fjut.gmxx.campusclub.utlis;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-01-13 14:40
 **/
@Component
public class JwtUtils {
    //密钥
    public static final String SECRET = "xiaoyuanshetuanguanlibysjd;o160225";
    //token过期时间:秒  30分钟
    public static final int EXPIRE = 30*60*60;

    /**
     * 生成Token
     * @param userCode
     * @param loginName
     * @return
     * @throws Exception
     */
    public  String createToken(String userCode, String loginName) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("algorithm", "HS256");
        map.put("type", "JWT");

        String token = null;//签名
        try {
            token = JWT.create()
                    .withHeader(map)//头
                    .withClaim("userCode", userCode)
                    .withClaim("loginName", loginName)
                    .withSubject("校园社团管理")//
                    .withIssuedAt(new Date())//签名时间
                    .withExpiresAt(expireDate)//过期时间
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            throw ExceptionFactory.getBizException("Token创建失败");
        }
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    public  Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw ExceptionFactory.getBizException("凭证已过期，请重新登录",token);
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     * @param token
     * @return
     */
    public  Map<String, Claim> parseToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }


   /* public static void main(String[] args) {
        try {
            String token = JwtUtils.createToken("12345", "wangbo");
            System.out.println("token=" + token);
            String text="eyJ0eXAiOiJKV1QiLCJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJhbGdvcml0aG0iOiJIUzI1NiJ9.eyJzdWIiOiLmoKHlm63npL7lm6LnrqHnkIYiLCJsb2dpbk5hbWUiOiJ3YW5nYm8iLCJleHAiOjE1Nzg4OTg2NDQsImlhdCI6MTU3ODg5ODYzOSwidXNlckNvZGUiOiIxMjM0NSJ9._k2GvIjdjy0xqxUp9F8mOpru6cKsntkMoVmbf_wVS7c";
            Map<String, Claim> map = JwtUtils.verifyToken(text);//验证Token
            //遍历
            for (Map.Entry<String, Claim> entry : map.entrySet()) {
                if (entry.getValue().asString() != null) {
                    System.out.println(entry.getKey() + "===" + entry.getValue().asString());
                } else {
                    System.out.println(entry.getKey() + "===" + entry.getValue().asDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
