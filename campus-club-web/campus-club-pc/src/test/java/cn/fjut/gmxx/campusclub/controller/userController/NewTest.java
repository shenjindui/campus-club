/*
package cn.fjut.gmxx.campusclub.controller.userController;

import cn.fjut.gmxx.campusclub.sysuser.api.ISysUserApi;
import cn.fjut.gmxx.campusclub.utlis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author : shenjindui
 * @date : 2020-01-06 10:23
 **//*


@RunWith(SpringRunner.class)
@SpringBootTest()
public class NewTest {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ISysUserApi iSysUserApi;
    @Test
    public void test(){
        redisUtils.set("sjd","牛哥");
    }

    @Test
    public void test1(){
        Map<String,Object> maps=new HashMap<>();
        Map<String,Object> result=iSysUserApi.getSysUserByOneMap(maps);
    }
}
*/
