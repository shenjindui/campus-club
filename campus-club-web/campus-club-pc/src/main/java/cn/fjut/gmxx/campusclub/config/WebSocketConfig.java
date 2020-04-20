package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/3/24.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author : shenjindui
 * @date : 2020-03-24 14:32
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }



}
