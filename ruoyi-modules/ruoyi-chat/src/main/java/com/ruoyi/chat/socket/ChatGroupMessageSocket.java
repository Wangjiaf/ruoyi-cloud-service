package com.ruoyi.chat.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chatGroupMessageSocket/{userId}")
@Component
public class ChatGroupMessageSocket {

    private static Map<String, ChatGroupMessageSocket> clients = new ConcurrentHashMap<String, ChatGroupMessageSocket>();

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
        System.out.println("ChatGroupMessageSocket:" + data);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public static Map<String, ChatGroupMessageSocket> getClients() {
        return clients;
    }

    public Session getSession() {
        return session;
    }

    public String getUserId() {
        return userId;
    }

}
