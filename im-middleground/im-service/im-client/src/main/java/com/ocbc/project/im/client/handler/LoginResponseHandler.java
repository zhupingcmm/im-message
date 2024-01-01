package com.ocbc.project.im.client.handler;

import com.alibaba.fastjson2.JSON;
import com.ocbc.project.im.common.packet.LoginResponsePacket;
import com.ocbc.project.im.common.util.LoginUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private static final Logger logger = LoggerFactory.getLogger(LoginResponseHandler.class);
    private LoginResponseHandler() {}

    public static LoginResponseHandler instance;

    public static LoginResponseHandler getInstance() {
        if (instance == null) {
            synchronized (LoginResponseHandler.class) {
                if (instance == null) {
                    instance = new LoginResponseHandler();
                }
            }
        }
        return instance;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {

        if (msg.isSuccess()) {
            logger.info("登陆成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            logger.warn("登陆失败： {}", JSON.toJSONString(msg));
        }
    }
}
