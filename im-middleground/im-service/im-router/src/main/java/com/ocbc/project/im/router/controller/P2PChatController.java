package com.ocbc.project.im.router.controller;

import com.ocbc.project.im.common.dto.ChatResponse;
import com.ocbc.project.im.common.dto.P2PChatRequest;
import com.ocbc.project.im.router.service.ChatService;
import com.ocbc.project.im.router.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p2p")
@AllArgsConstructor
public class P2PChatController {

    private final ChatService chatService;

    private final LoginService loginService;


    @PostMapping("/chat")
    public ChatResponse p2pChat(@RequestBody P2PChatRequest request) {
        ChatResponse chatResponse = new ChatResponse();

        if (!loginService.isLogin(request.getFromUserId())) {

            chatResponse.setCode("3002");
            chatResponse.setMsg("Please login!!!");
            return chatResponse;
        }

        if (!loginService.isLogin(request.getToUserId())) {
            chatResponse.setCode("3003");
            chatResponse.setMsg("remote user is not login");
        }

        chatResponse = chatService.p2pChat(request);

        return chatResponse;
    }


}
