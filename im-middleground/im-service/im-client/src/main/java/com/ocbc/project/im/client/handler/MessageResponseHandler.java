package com.ocbc.project.im.client.handler;

import com.ocbc.project.im.common.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    private static Logger logger = LoggerFactory.getLogger(MessageResponseHandler.class);
    private MessageResponseHandler () {}
    public static volatile MessageResponseHandler instance;

    public static MessageResponseHandler getInstance() {
        if (instance == null) {
            synchronized (MessageResponseHandler.class) {
                if (instance == null) {
                    instance = new MessageResponseHandler();
                }
            }
        }

        return instance;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        logger.info("Received from {} with message {}", msg.getFromUserId(), msg.getMessage());
    }
}
