package com.ocbc.project.im.client.service;

import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.common.dto.IMServerInfo;

public interface ClientService {

    /**
     * 获取 Im server 的信息
     */
    IMServerInfo getServerInfo(String routeHost, String routePort);

    /**
     * 登陆 Im router
     */

    void loginRoute(String routeHost, String routePort, IMLoginRequest request);

    /**
     * 连接 im-server
     */
    void connectToServer(IMLoginRequest request);
}

