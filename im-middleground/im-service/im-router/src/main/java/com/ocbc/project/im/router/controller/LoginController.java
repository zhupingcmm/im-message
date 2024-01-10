package com.ocbc.project.im.router.controller;

import com.alibaba.fastjson2.JSON;
import com.ocbc.project.im.common.dto.IMLoginRequest;
import com.ocbc.project.im.common.dto.IMLoginResponse;
import com.ocbc.project.im.router.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping(value = "/login")
    public IMLoginResponse login(@RequestBody IMLoginRequest request) {

        IMLoginResponse response = new IMLoginResponse();

        if (loginService.isLogin(request.getUserid())) {

            response.setCode("2001");
            response.setMsg( request.getUserid() + "Duplicate login");
            return response;
        }

        loginService.login(request);
        log.info("login success: {}", JSON.toJSONString(request));
        return response;
    }


    @PostMapping(value = "/logout/{userid}")
    public void logout(@PathVariable("userid") String userid) {
        loginService.logout(userid);
        log.info("{}: logout success", userid);

    }

}
