package com.ocbc.project.im.server.handler;


import com.alibaba.fastjson2.JSON;
import com.ocbc.project.im.common.packet.HeartBeatPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class HeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatPacket> {
    private static final Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);
    public static volatile HeartBeatHandler instance;

    public static HeartBeatHandler getInstance() {
        if (instance == null) {
            synchronized (HeartBeatHandler.class) {
                if (instance == null) {
                    instance = new HeartBeatHandler();
                }
            }
        }
        return instance;
    }

    private HeartBeatHandler () {}
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatPacket msg) throws Exception {
        logger.info("收到心跳包: {}", JSON.toJSONString(msg));
        ctx.writeAndFlush(msg);
    }
}
