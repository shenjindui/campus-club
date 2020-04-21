package cn.fjut.gmxx.campusclub.exception;/**
 * Created by admin on 2020/4/21.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * @date : 2020-04-21 16:14
 **/
@PropertySource("classpath:default-error-code.properties")
@Component
@Configuration
public class ExcetionMsg {
    @Autowired
    private Environment environment;

    public String getProperty(String parms, String... args){
        String valueForPlace = environment.getProperty(parms);
        if(parms != null && !"".equals(parms) && valueForPlace != null && !"".equals(valueForPlace) && args != null && args.length > 0) {
            for(int i = 0; i < args.length; ++i) {
                if(args[i] != null) {
                    valueForPlace = valueForPlace.replaceAll("\\{" + i + "\\}", args[i]);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append(valueForPlace);
        sb.append("");
        return sb.toString();
    }
}
