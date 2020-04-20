package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/3/24.
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * @date : 2020-03-24 16:41
 **/
@Component
public class WebSocketScheduleTask {
    @Scheduled(cron = "0 0/1 * * * ?")
    //定时任务
    public void sendtoClient() {
        //SocketServer.sendAll(System.currentTimeMillis() + "定时发送消息");
    }
}