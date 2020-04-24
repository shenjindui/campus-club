package cn.fjut.gmxx.campusclub.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * WebSocketScheduleTask定时任务
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