package com.ocbc.project.im.router.service.impl;

import com.ocbc.project.im.common.dto.ChatResponse;
import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.common.dto.P2PChatRequest;
import com.ocbc.project.im.router.service.ChatService;
import com.ocbc.project.im.router.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final LoginService loginService;

    private final RestTemplate restTemplate;
    @Override
    public ChatResponse p2pChat(P2PChatRequest request) {

        IMLoginRequest loginRequest = loginService.getLoginInfo(request.getToUserId());

        return restTemplate.patchForObject("http://" + loginRequest.getServerHost() + ":" + loginRequest.getHttpPort()+"/p2p/chat", request, ChatResponse.class);
    }
}
