package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/3/22.
 */

import cn.fjut.gmxx.campusclub.baseddct.repository.DictCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * @date : 2020-03-22 22:05
 **/
@Component
public class SysInitConfig implements CommandLineRunner {
    @Autowired
    DictCacheRepository dictCacheRepository;
    @Override
    public void run(String... strings) throws Exception {
        dictCacheRepository.refreshDictAll();

    }
}