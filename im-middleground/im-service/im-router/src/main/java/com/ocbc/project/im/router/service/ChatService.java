package com.ocbc.project.im.router.service;

import com.ocbc.project.im.common.dto.ChatResponse;
import com.ocbc.project.im.common.dto.P2PChatRequest;

public interface ChatService {

    ChatResponse p2pChat(P2PChatRequest request);
}
