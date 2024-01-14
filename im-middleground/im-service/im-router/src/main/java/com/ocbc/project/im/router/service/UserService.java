package com.ocbc.project.im.router.service;

import com.ocbc.project.im.router.entity.User;
import com.ocbc.project.im.router.vo.UserDTO;
import org.springframework.data.domain.Page;



public interface UserService {

    void addUser(UserDTO userDTO);

    Page<User> getUsers(UserDTO userDTO);


}
