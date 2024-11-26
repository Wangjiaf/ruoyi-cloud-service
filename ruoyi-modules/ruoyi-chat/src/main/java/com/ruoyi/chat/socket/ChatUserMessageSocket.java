package com.ruoyi.chat.socket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chatUserMessageSocket/{userId}")
@Component
public class ChatUserMessageSocket {

    private static Map<String, ChatUserMessageSocket> clients = new ConcurrentHashMap<String, ChatUserMessageSocket>();

    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        this.userId = userId;
        this.session = session;
        clients.put(userId, this);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(userId);
    }

    @OnMessage
    public void onMessage(String data) throws IOException {
        JSONObject jo = JSONObject.parseObject(data);
        sendMessageTo(jo.get("message").toString(), jo.get("to").toString());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /** 发送个人消息 */
    public void sendMessageTo(String message, String to) throws IOException {
        for (ChatUserMessageSocket item : clients.values()) {
            if (item.userId.equals(to)) {
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    public static Map<String, ChatUserMessageSocket> getClients() {
        return clients;
    }

    public Session getSession() {
        return session;
    }

    public String getUserId() {
        return userId;
    }

}
