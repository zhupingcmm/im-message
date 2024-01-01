package com.ocbc.project.im.client.handler;

import com.ocbc.project.im.common.packet.HeartBeatPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientIdleHandler extends IdleStateHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClientIdleHandler.class);
    public static final int HEAT_BEAT_TIME = 5;
    public ClientIdleHandler() {
        super(0, 0, HEAT_BEAT_TIME);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.info("发送心跳");

        ctx.writeAndFlush(new HeartBeatPacket());
    }
}
