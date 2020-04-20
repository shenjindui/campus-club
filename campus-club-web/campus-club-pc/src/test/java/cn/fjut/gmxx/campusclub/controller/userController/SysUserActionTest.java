/*
package cn.fjut.gmxx.campusclub.controller.userController;

import cn.fjut.gmxx.campusclub.CampusClubPcApplication;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

*/
/**
 * Created by admin on 2020/1/3.
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampusClubPcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserActionTest {

    */
/**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     *//*

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    RedisUtils redisUtils;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }
    */
/**
     * 向"/test"地址发送请求，并打印返回结果
     *
     * @throws Exception
     *//*

    @Test
    public void test1() throws Exception {

        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/api/test", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }

    @Test
    public void test2(){
        redisUtils.set("sjd","沈金堆25");
    }
}*/
