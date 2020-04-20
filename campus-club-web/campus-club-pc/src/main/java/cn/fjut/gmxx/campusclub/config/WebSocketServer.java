package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/3/24.
 */

/**
 * @author : shenjindui
 * @date : 2020-03-24 17:28
 **/

import cn.fjut.gmxx.campusclub.baseclubfunds.api.IBaseClubFundsApi;
import cn.fjut.gmxx.campusclub.baseclubmember.api.IBaseClubMemberApi;
import cn.fjut.gmxx.campusclub.sysuser.service.ISysUserService;
import cn.fjut.gmxx.campusclub.sysuserrolerel.api.ISysUserRoleRelApi;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/push/websocket/{userCode}")
public class WebSocketServer {
    //此处是解决无法注入的关键
    private static ApplicationContext applicationContext;
    //注入的service
    private ISysUserService sysUserService;
    private IBaseClubMemberApi baseClubMemberApi ;
    private IBaseClubFundsApi baseClubFundsApi ;
    private ISysUserRoleRelApi sysUserRoleRelApi;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userCode") String userCode) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        //log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        Map<String,List<Map<String, Object>>> result=new HashMap<>();
        this.sid=userCode;
        try {
            sysUserService = applicationContext.getBean(ISysUserService.class);
            baseClubMemberApi = applicationContext.getBean(IBaseClubMemberApi.class);
            baseClubFundsApi = applicationContext.getBean(IBaseClubFundsApi.class);
            sysUserRoleRelApi=applicationContext.getBean(ISysUserRoleRelApi.class);
            Map<String, Object> params = new HashMap();
            params.clear();
            params.put("userCode",userCode);
            Map<String, Object> userDetailMap= sysUserService.getSysUserMap(params);
            params.clear();
            params.put("userCode",userCode);
            params.put("defaultRole",1);
            Map<String, Object> sysUserRoleRelMap=sysUserRoleRelApi.getSysUserRoleRelMap(params);
            //role-00003为社团社员角色
            if(sysUserRoleRelMap!=null&&MapUtils.getString(sysUserRoleRelMap,"roleCode").equals("role-00003")){
                params.clear();
                params.put("memberSno", MapUtils.getString(userDetailMap,"jobNum"));
                List<Map<String, Object>> memberList = baseClubMemberApi.findBaseClubMember(params);
                params.clear();
                for(Map<String, Object> map:memberList){
                    params.put("fundsAssociationCode", MapUtils.getString(map,"stCd"));//财务关联编号
                    params.put("type",1);//财务操作类型
                    params.put("amountType","+");//收入
                    List<Map<String, Object>> messageList= baseClubFundsApi.findBaseClubFunds(params);
                    result.put(userCode,messageList);
                }
                sendMessage(JSON.toJSONString(result));
            }else{
                sendMessage(null);
            }

        } catch (IOException e) {
            //log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
       // log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //log.info("收到来自窗口"+sid+"的信息:"+message);
        if("heart".equals(message)){
            try {
                sendMessage("heartOk");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
       // log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {

        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {

                item.sendMessage(message);
              //  log.info("推送消息到窗口"+item.sid+"，推送内容:"+message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
