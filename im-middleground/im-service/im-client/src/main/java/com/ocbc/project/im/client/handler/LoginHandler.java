package com.ocbc.project.im.client.handler;

import com.ocbc.project.im.common.packet.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginHandler extends ChannelInboundHandlerAdapter {
    private String userid;

    private String username;

    public LoginHandler(String userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //构建登录对象，发起登录操作
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(this.userid);
        loginRequestPacket.setUserName(this.username);

        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
