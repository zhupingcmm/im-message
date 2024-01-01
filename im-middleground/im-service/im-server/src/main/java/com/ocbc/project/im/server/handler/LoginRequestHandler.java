package com.ocbc.project.im.server.handler;

import com.ocbc.project.im.common.packet.LoginRequestPacket;
import com.ocbc.project.im.common.packet.LoginResponsePacket;
import com.ocbc.project.im.common.util.LoginUtil;
import com.ocbc.project.im.common.util.Session;
import com.ocbc.project.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

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
        LoginResponsePacket responsePacket = new LoginResponsePacket();

        if (checkLogin(ctx, packet)) {
            responsePacket.setCode("0000");
            responsePacket.setMessage("登陆成功");

            //表示和设置该用户的登录状态
            LoginUtil.markAsLogin(ctx.channel());

            //绑定Session和Channel的关系：最重要的一步
            SessionUtil.buildSession(new Session(packet.getUserId(), packet.getUserName(), packet.getPassword()), ctx.channel());
        } else  {
            responsePacket.setCode("1001");
            responsePacket.setMessage("登录失败");
        }

        return responsePacket;
    }

    private boolean checkLogin(ChannelHandlerContext ctx, LoginRequestPacket packet) {
        return !SessionUtil.hasLogin(ctx.channel());
    }
}
