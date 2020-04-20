package cn.fjut.gmxx.campusclub.controller.commonController;/**
 * Created by admin on 2020/3/24.
 */

import cn.fjut.gmxx.campusclub.config.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

/**
 * @author : shenjindui
 * @date : 2020-03-24 17:29
 **/
public class WebSocketController {
    @Autowired
    WebSocketServer webSocketServer;
    @PostMapping("/sendAllWebSocket")
    public String test() {
        String text="你们好！这是websocket群体发送！";
        try {
            webSocketServer.sendInfo(text);
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }
}
