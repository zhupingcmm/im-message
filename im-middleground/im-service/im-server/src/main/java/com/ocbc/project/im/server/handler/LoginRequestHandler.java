package com.ocbc.project.im.server.handler;

import com.ocbc.project.im.common.packet.LoginRequestPacket;
import com.ocbc.project.im.common.packet.LoginResponsePacket;
import com.ocbc.project.im.common.util.LoginUtil;
import com.ocbc.project.im.common.util.Session;
import com.ocbc.project.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static final Logger logger = LoggerFactory.getLogger(LoginRequestHandler.class);

    public static volatile LoginRequestHandler instance;

    private LoginRequestHandler() {}

    public static LoginRequestHandler getInstance() {
        if (instance == null) {
            synchronized (LoginRequestHandler.class) {
                if (instance == null) {
                    instance = new LoginRequestHandler();
                }
            }
        }

        return instance;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        LoginResponsePacket responsePacket = login(ctx, packet);
        ctx.channel().writeAndFlush(responsePacket);
    }

    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket packet) {
        logger.info("{}({}) try to login", packet.getUserName(), packet.getUserId());
        LoginResponsePacket responsePacket = new LoginResponsePacket();

        if (checkLogin(ctx, packet)) {
            responsePacket.setCode("0000");
            responsePacket.setMessage("Login success");

            //表示和设置该用户的登录状态
            LoginUtil.markAsLogin(ctx.channel());

            //绑定Session和Channel的关系：最重要的一步
            SessionUtil.buildSession(new Session(packet.getUserId(), packet.getUserName()), ctx.channel());

            logger.info("{}({}) login success", packet.getUserName(), packet.getUserId());
        } else  {
            responsePacket.setCode("1001");
            responsePacket.setMessage("Login failed");
            logger.error("{}({}) login failed", packet.getUserName(), packet.getUserId());
        }

        return responsePacket;
    }

    private boolean checkLogin(ChannelHandlerContext ctx, LoginRequestPacket packet) {
        return !SessionUtil.hasLogin(ctx.channel());
    }
}
