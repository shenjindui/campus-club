package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/3/22.
 */

import cn.fjut.gmxx.campusclub.baseddct.repository.DictCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : shenjindui
 * @date : 2020-03-22 22:13
 **/
    @Lazy(false)
    @Component
    @EnableScheduling
    public class SpringDynamicCronByDdctTask implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(SpringDynamicCronByDdctTask.class);
    @Autowired
    DictCacheRepository dictCacheRepository;
    private static String cron;

    public SpringDynamicCronByDdctTask() {
        cron = "0/5 * * * * ?";

        // 开启新线程模拟外部更改了任务执行周期
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(150 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cron = "0/10 * * * * ?";
                System.err.println("cron change to: " + cron);
            }
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                // 任务逻辑
                //logger.debug("dynamicCronTask is running...");
                dictCacheRepository.refreshDictAll();
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 任务触发，可修改任务的执行周期
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        });
    }
}