package com.ocbc.project.im.client.service.impl;

import com.ocbc.project.im.client.handler.ClientIdleHandler;
import com.ocbc.project.im.client.handler.LoginHandler;
import com.ocbc.project.im.client.handler.LoginResponseHandler;
import com.ocbc.project.im.client.handler.MessageResponseHandler;
import com.ocbc.project.im.client.service.ClientService;
import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.common.dto.IMLogoutResponse;
import com.ocbc.project.im.common.dto.IMServerInfo;
import com.ocbc.project.im.common.handler.PacketCodecHandler;
import com.ocbc.project.im.common.handler.ServerIdleHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
public class ClientServiceImpl implements ClientService {
    @Override
    public IMServerInfo getServerInfo(String routeHost, String routePort) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://" + routeHost + ":" + routePort + "/serverinfo/", IMServerInfo.class);
    }

    @Override
    public void loginRoute(String routeHost, String routePort, IMLoginRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        IMLogoutResponse  response = restTemplate.postForObject("http://" + routeHost + ":" + routePort + "/auth/login", request, IMLogoutResponse.class);

        if (Objects.requireNonNull(response).success()) {
            log.info("Login success");
        } else {
            log.error("Login failed: {}, the process will exit", response.getMsg());
            System.exit(0);
        }

    }

    @Override
    public void connectToServer(IMLoginRequest request) {

        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerIdleHandler());
                        ch.pipeline().addLast(PacketCodecHandler.getInstance());
                        ch.pipeline().addLast(new ClientIdleHandler());
                        ch.pipeline().addLast(new LoginHandler(request.getUserid(),request.getUsername()));
                        ch.pipeline().addLast(LoginResponseHandler.getInstance());
                        ch.pipeline().addLast(MessageResponseHandler.getInstance());
                    }
                });

        ChannelFuture future = bootstrap.connect(request.getServerHost(), request.getNettyPort())
                .addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            log.info("Success connect to http://{}:{}", request.getServerHost(), request.getNettyPort());
                        } else {
                            log.error("Failed connect to http://{}:{}", request.getServerHost(), request.getNettyPort());
                            System.exit(0);
                        }
                    }
                });

        try {
            future.channel().closeFuture().sync();
            log.info("与服务端断开连接！");
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

    }
}
