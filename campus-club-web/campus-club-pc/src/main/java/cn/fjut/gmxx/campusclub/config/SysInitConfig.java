package cn.fjut.gmxx.campusclub.config;
import cn.fjut.gmxx.campusclub.baseddct.repository.DictCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * 系统初始化字典缓存
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