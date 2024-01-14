package com.ocbc.project.im.router.controller;

import com.ocbc.project.im.router.entity.User;
import com.ocbc.project.im.router.service.UserService;
import com.ocbc.project.im.router.vo.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }


    @GetMapping
    Page<User> getUsers(@RequestBody UserDTO userDTO){
        return userService.getUsers(userDTO);
    }
}
