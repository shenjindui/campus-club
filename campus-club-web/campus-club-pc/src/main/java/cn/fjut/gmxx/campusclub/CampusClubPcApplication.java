package cn.fjut.gmxx.campusclub;

import cn.fjut.gmxx.campusclub.config.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "cn.fjut.gmxx.campusclub")
@MapperScan("cn.fjut.gmxx.campusclub.*.mapper")
@EnableCaching
@EnableScheduling
@EnableSwagger2//开启swagger2
public class CampusClubPcApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(CampusClubPcApplication.class, args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(configurableApplicationContext);

    }
}
