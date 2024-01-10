package com.ocbc.project.im.router.service.impl;

import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.router.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Map<String, IMLoginRequest> loginRequestMap = new ConcurrentHashMap<>();


    @Override
    public void login(IMLoginRequest request) {
        loginRequestMap.putIfAbsent(request.getUserid(), request);
    }

    @Override
    public void logout(String userid) {
        loginRequestMap.remove(userid);
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
