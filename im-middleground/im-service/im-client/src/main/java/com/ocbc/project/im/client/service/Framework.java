package com.ocbc.project.im.client.service;

import com.ocbc.project.im.client.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Framework {

    @Value("${im.userid}")
    private String userid;

    @Value("${im.username}")
    private String username;

    @PostConstruct
    public void start() {
        Client client = new Client();
        client.start(userid, username);
    }
}
