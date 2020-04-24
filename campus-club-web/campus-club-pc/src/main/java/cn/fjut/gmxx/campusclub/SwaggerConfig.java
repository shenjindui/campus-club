package cn.fjut.gmxx.campusclub;
import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * author : shenjindui
 * date : 2019-12-29
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig  {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.fjut.gmxx.campusclub.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("校园社团管理系统API")
                .description("校园社团管理系统-ui")
                .version("V1.0")
                .termsOfServiceUrl("http://localhost:8089/")
                .contact("2802630961@qq.com")
                .build();
    }
}

