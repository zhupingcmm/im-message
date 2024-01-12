package com.ocbc.project.im.client.client;

import com.ocbc.project.im.client.service.ClientService;
import com.ocbc.project.im.client.service.impl.ClientServiceImpl;
import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.common.dto.IMServerInfo;

public class Client {

    private static final String ROUTE_HOST = "127.0.0.1";

    private static final String ROUTE_PORT = "9003";

    private static final String userid = "100";

    private static final String username = "zp";

    public static void start() {
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
