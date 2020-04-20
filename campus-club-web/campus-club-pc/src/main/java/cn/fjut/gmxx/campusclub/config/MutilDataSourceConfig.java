package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/3/28.
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : shenjindui
 * @date : 2020-03-28 12:55
 **/
@Configuration
@MapperScan(basePackages = {"cn.fjut.gmxx.campusclub.*.mapper"})
public class MutilDataSourceConfig {
}
