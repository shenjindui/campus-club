package cn.fjut.gmxx.campusclub.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : shenjindui
 * 多数据源配置
 * @date : 2020-03-28 12:55
 **/
@Configuration
@MapperScan(basePackages = {"cn.fjut.gmxx.campusclub.*.mapper"})
public class MutilDataSourceConfig {
}
