package com.ocbc.project.im.server.controller;

import com.ocbc.project.im.common.util.Session;
import com.ocbc.project.im.common.util.SessionUtil;
import com.ocbc.project.im.server.service.SessionService;
import com.ocbc.project.infrastructure.vo.Result;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    @GetMapping("/")
    public Result<List<Session>> getAllSessions() {
        List<Session> sessions = SessionUtil.getAllSessions();

        Result<List<Session>> result = new Result<>();

        result.setData(sessions);
        return result;
    }


    @DeleteMapping("/{userid}")
    public void removeSession(@PathVariable(value = "userid") String userId) {
        sessionService.removeSession(userId);
    }
}
