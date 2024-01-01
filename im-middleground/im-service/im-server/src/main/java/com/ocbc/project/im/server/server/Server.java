package com.ocbc.project.im.server.server;


import com.ocbc.project.im.common.handler.PacketCodecHandler;
import com.ocbc.project.im.common.handler.ServerIdleHandler;
import com.ocbc.project.im.server.handler.HeartBeatHandler;
import com.ocbc.project.im.server.handler.LoginRequestHandler;
import com.ocbc.project.im.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static  final int port = 8888;
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerIdleHandler());
                        ch.pipeline().addLast(PacketCodecHandler.getInstance());
                        ch.pipeline().addLast(HeartBeatHandler.getInstance());
                        ch.pipeline().addLast(LoginRequestHandler.getInstance());
                        ch.pipeline().addLast(MessageRequestHandler.getInstance());
                    }
                });
        ChannelFuture future = bootstrap.bind(port);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()){
                    logger.info("server started! using port {} " , port);
                }else {
                    logger.info("server start failed! using port {} " , port);
                    channelFuture.cause().printStackTrace();
                    System.exit(0);
                }
            }
        });
    }

}
