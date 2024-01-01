package com.ocbc.project.im.server.handler;

import com.alibaba.fastjson2.JSON;
import com.ocbc.project.im.common.packet.DefaultErrorPacket;
import com.ocbc.project.im.common.packet.MessageRequestPacket;
import com.ocbc.project.im.common.packet.MessageResponsePacket;
import com.ocbc.project.im.common.util.Session;
import com.ocbc.project.im.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(MessageRequestHandler.class);
    private MessageRequestHandler() {}

    public static MessageRequestHandler instance;

    public static MessageRequestHandler getInstance() {
        if (instance == null) {
            synchronized (MessageRequestHandler.class) {
                if (instance == null) {
                    instance = new MessageRequestHandler();
                }
            }
        }
        return instance;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        logger.debug("Received message from client : {}", JSON.toJSONString(messageRequestPacket));
        Session session = SessionUtil.getSessionByChannel(ctx.channel());

        String fromUserid = session.getUserId();
        String fromUsername = session.getUserName();

        // 构建响应结果
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

        messageResponsePacket.setFromUserId(fromUserid);
        messageResponsePacket.setFromUserName(fromUsername);
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
    }

    private void p2tChat(ChannelHandlerContext ctx, String toUserid, MessageResponsePacket packet) {
        Channel channel = SessionUtil.getChannelByUserId(toUserid);

        if (channel != null) {
            if (SessionUtil.hasLogin(channel)) {
                channel.writeAndFlush(packet);
            } else {
                DefaultErrorPacket defaultErrorPacket = new DefaultErrorPacket();
                defaultErrorPacket.setCode("3001");
                defaultErrorPacket.setMessage("该用户没有登录，无法发送消息");
                channel.writeAndFlush(defaultErrorPacket);
            }
        }


    }
}
