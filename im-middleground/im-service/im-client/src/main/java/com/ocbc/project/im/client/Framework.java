package com.ocbc.project.im.client;

import com.ocbc.project.im.client.service.ClientService;
import com.ocbc.project.im.client.service.impl.ClientServiceImpl;
import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.common.dto.IMServerInfo;

public class Framework {

    private static final String ROUTE_HOST = "127.0.0.1";

    private static final String ROUTE_PORT = "9003";


    public void start(String userid, String username) {

        ClientService clientService = new ClientServiceImpl();

        // get im server info
        IMServerInfo serverInfo = clientService.getServerInfo(ROUTE_HOST, ROUTE_PORT);

        IMLoginRequest imLoginRequest = IMLoginRequest.builder()
                .serverHost(serverInfo.getHost())
                .httpPort(serverInfo.getHttpPort())
                .nettyPort(serverInfo.getNettyPort())
                .userid(userid)
                .username(username)
                .build();

        // login route server
        clientService.loginRoute(ROUTE_HOST, ROUTE_PORT, imLoginRequest);


        // connect to im server
        clientService.connectToServer(imLoginRequest);

    }
}