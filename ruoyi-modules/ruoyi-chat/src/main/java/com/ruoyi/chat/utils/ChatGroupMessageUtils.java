package com.ruoyi.chat.utils;

import com.ruoyi.chat.socket.ChatGroupMessageSocket;

public class ChatGroupMessageUtils {

    public static boolean socketSendMessage(String userId, String message) {
        for (ChatGroupMessageSocket item : ChatGroupMessageSocket.getClients().values()) {
            if (item.getUserId().equals(userId)) {
                item.getSession().getAsyncRemote().sendText(message);
                return true;
            }
        }
        return false;
    }

}
