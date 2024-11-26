package com.ruoyi.chat.utils;

import com.alibaba.fastjson.JSON;
import com.ruoyi.chat.socket.ChatTipSocket;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;

import java.io.IOException;

public class ChatTipUtils {

    private static RedisService redisService = SpringUtils.getBean(RedisService.class);

    public static String cutTipContent(String tipContent) {
        if (StringUtils.isEmpty(tipContent) || tipContent.length() <= 20) {
            return tipContent;
        }
        return tipContent.substring(0, 20);
    }

    public static String fullTipContent(String tipContent) {
        tipContent = StringUtils.isEmpty(tipContent) ? "" : tipContent;
        if (StringUtils.isEmpty(tipContent) || tipContent.length() < 20) {
            return tipContent;
        }
        return tipContent + "...";
    }

    public static void socketSendMessage(String userId, String message) {
        for (ChatTipSocket item : ChatTipSocket.getClients().values()) {
            if (item.getUserId().equals(userId)) {
                item.getSession().getAsyncRemote().sendText(message);
            }
        }
    }

}
