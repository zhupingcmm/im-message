package com.ocbc.project.im.router.controller;

import com.ocbc.project.im.common.dto.IMServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/serverinfo")
public class ServerInfoController {

    @GetMapping("/")
    public IMServerInfo getServerInfo() {
        log.info("====================");
        IMServerInfo serverInfo = new IMServerInfo();


        serverInfo.setHost("127.0.0.1");
        serverInfo.setNettyPort(8888);
        serverInfo.setHttpPort(9001);

        return serverInfo;
    }


}
