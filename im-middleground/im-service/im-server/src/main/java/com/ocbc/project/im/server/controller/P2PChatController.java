package com.ocbc.project.im.server.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ocbc.project.im.common.dto.ChatResponse;
import com.ocbc.project.im.common.dto.P2PChatRequest;
import com.ocbc.project.im.common.packet.MessageResponsePacket;
import com.ocbc.project.im.common.util.ChannelUtil;
import com.ocbc.project.im.common.util.Session;
import com.ocbc.project.im.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.internal.ChannelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p2p")
public class P2PChatController {

    @PostMapping(value = "/chat")
    public ChatResponse p2pChat(@RequestBody P2PChatRequest request) {

        ChatResponse response = new ChatResponse();
        String userId = request.getToUserId();
        Channel channel = SessionUtil.getChannelByUserId(userId);
        if (ObjectUtil.isEmpty(channel)) {
            response.setCode("4001");
            response.setMsg(userId + " do not login, can not send message");
            return response;
        }

        Session session = SessionUtil.getSessionByChannel(channel);

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setFromUserId(request.getFromUserId());
        responsePacket.setMessage(request.getMsg());

        // write data to client
        ChannelUtil.writeAndFlush(channel,responsePacket);

        return response;
    }

}
