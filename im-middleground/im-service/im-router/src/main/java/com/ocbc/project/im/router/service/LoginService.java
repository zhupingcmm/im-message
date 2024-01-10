package com.ocbc.project.im.router.service;

import com.ocbc.project.im.common.dto.IMLoginRequest;

import java.util.Map;

public interface LoginService {

    /**
     * 用户登陆
     */
    void login(IMLoginRequest request);

    /**
     * 用户退出
     */

    void logout(String userid);

    /**
     * 是否已登陆
     */
    Boolean isLogin(String userid);

    /**
     * 获取用户登陆信息
     */

    IMLoginRequest getLoginInfo(String userid);

    /**
     * 获取所有登陆用户
     */

    Map<String, IMLoginRequest> getAllLoginUser();
}
