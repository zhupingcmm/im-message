package com.ocbc.project.im.server.service.impl;

import com.ocbc.project.im.common.util.Session;
import com.ocbc.project.im.common.util.SessionUtil;
import com.ocbc.project.im.server.service.SessionService;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {
    @Override
    public List<Session> getAllSessions() {
        return SessionUtil.getAllSessions();
    }

    @Override
    public void removeSession(String userId) {
        Channel channel = SessionUtil.getChannelByUserId(userId);

        // remove session
        SessionUtil.removeSessionByChannel(channel);

        // close channel
        channel.close();

        log.info("Disconnect with {}", userId);
    }
}
