package cn.fjut.gmxx.campusclub.controller.userController;

import cn.fjut.gmxx.campusclub.CampusClubPcApplication;
import cn.fjut.gmxx.campusclub.baseclubMail.api.BaseclubMailApi;
import cn.fjut.gmxx.campusclub.sysuser.api.ISysUserApi;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : shenjindui
 * @date : 2020-01-06 10:23
 **/



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampusClubPcApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  websocket导致spring boot 项目单元测试启动失败的问题解决
public class NewTest {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ISysUserApi iSysUserApi;

    @Autowired
    BaseclubMailApi baseclubMailApi;

    @Test
    public void test(){
        redisUtils.set("sjd","牛哥");
    }

    /**
     * 邮件发送验证
     */
    /*@Test
    public void test1(){
        Map<String,Object> parms=new HashMap<>();
        parms.put("loginName","stsy");
        parms.put("to","1647262043@qq.com");
        parms.put("subject","来自校园社团管理系统的激活邮件");
        String code= RandomUtils.stringWithNumber(6);
        String context = "欢迎使用校园社团管理系统,您的激活码是:<a>"+code+"</a>,本激活码60秒内有效!";
        parms.put("content",context);
        redisUtils.set(MapUtils.getString(parms,"loginName"),code,60);
        baseclubMailApi.sendHtmlMail(parms);
    }*/
}
