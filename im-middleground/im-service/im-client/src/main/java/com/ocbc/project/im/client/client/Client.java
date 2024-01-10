package com.ocbc.project.im.client.client;

import com.ocbc.project.im.client.handler.ClientIdleHandler;
import com.ocbc.project.im.client.handler.LoginHandler;
import com.ocbc.project.im.client.handler.LoginResponseHandler;
import com.ocbc.project.im.client.handler.MessageResponseHandler;
import com.ocbc.project.im.common.handler.PacketCodecHandler;
import com.ocbc.project.im.common.handler.ServerIdleHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static String userid = "101";
    public static String username = "tianyalan";
    public static String host = "127.0.0.1";

    public static String password = "xxxx";
    public static int port = 8888;


    public static void main(String[] args) {
        start(userid, username, host, port);
    }

    public static void start(String userid, String username, String host, int port) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new ServerIdleHandler());
                        ch.pipeline().addLast(PacketCodecHandler.getInstance());
//                        ch.pipeline().addLast(new ClientIdleHandler());
                        ch.pipeline().addLast(new LoginHandler(userid,username));
                        ch.pipeline().addLast(LoginResponseHandler.getInstance());
//                        ch.pipeline().addLast(MessageResponseHandler.getInstance());
                    }
                });

        ChannelFuture future = bootstrap.connect(host, port)
                .addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            logger.info("Success connect to http://{}:{}", host, port);
                        } else {
                            logger.error("Failed connect to http://{}:{}", host, port);
                            System.exit(0);
                        }
                    }
                });

        try {
            future.channel().closeFuture().sync();
            logger.info("与服务端断开连接！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
