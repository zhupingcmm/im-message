package com.ocbc.project.im.common.handler;

import com.ocbc.project.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerIdleHandler extends IdleStateHandler {
    private static final int HEAT_BEAT_TIME = 20;

    private static final Logger logger = LoggerFactory.getLogger(ServerIdleHandler.class);

    public ServerIdleHandler() {
        super(0, 0, HEAT_BEAT_TIME);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.warn(HEAT_BEAT_TIME + "s , there is no heart beat , so will close the channel");

        // remove
        SessionUtil.removeSessionByChannel(ctx.channel());

        // close channel
        ctx.channel().close();
    }
}
