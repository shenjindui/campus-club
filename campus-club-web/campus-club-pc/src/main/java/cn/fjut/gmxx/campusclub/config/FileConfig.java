package cn.fjut.gmxx.campusclub.config;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author : shenjindui
 * @date : 2020-01-21 16:11
 **/
@Configuration
public class FileConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("D:\\profile\\upload");
        return factory.createMultipartConfig();
    }
}