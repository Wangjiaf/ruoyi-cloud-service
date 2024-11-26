package com.ruoyi.chat.utils;

import com.ruoyi.chat.socket.ChatUserMessageSocket;

public class ChatUserMessageUtils {

    public static boolean socketSendMessage(String userId, String message) {
        for (ChatUserMessageSocket item : ChatUserMessageSocket.getClients().values()) {
            if (item.getUserId().equals(userId)) {
                item.getSession().getAsyncRemote().sendText(message);
                return true;
            }
        }
        return false;
    }

}
