package com.ocbc.project.im.client.handler;

import com.ocbc.project.im.common.packet.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginHandler extends ChannelInboundHandlerAdapter {
    private String userid;

    private String username;

    private String password;

    public LoginHandler(String userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(this.userid);
        loginRequestPacket.setUserName(this.username);
        loginRequestPacket.setPassword(this.password);

        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
