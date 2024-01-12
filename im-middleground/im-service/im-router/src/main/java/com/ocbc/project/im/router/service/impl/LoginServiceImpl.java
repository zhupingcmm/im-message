package com.ocbc.project.im.router.service.impl;

import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.router.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final RestTemplate restTemplate;

    private static final Map<String, IMLoginRequest> loginRequestMap = new ConcurrentHashMap<>();


    @Override
    public void login(IMLoginRequest request) {
        loginRequestMap.putIfAbsent(request.getUserid(), request);
    }

    @Override
    public void logout(String userid) {
        loginRequestMap.remove(userid);

        // remove user relate info from im-server
        restTemplate.delete("http://localhost:8080/session/" + userid);
    }

    @Override
    public Boolean isLogin(String userid) {
        return loginRequestMap.containsKey(userid);
    }

    @Override
    public IMLoginRequest getLoginInfo(String userid) {
        return loginRequestMap.get(userid);
    }

    @Override
    public Map<String, IMLoginRequest> getAllLoginUser() {
        return loginRequestMap;
    }
}
