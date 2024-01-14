package com.ocbc.project.im.router.service.impl;

import com.ocbc.project.im.router.entity.User;
import com.ocbc.project.im.router.entity.UserGroup;
import com.ocbc.project.im.router.repository.UserGroupRepository;
import com.ocbc.project.im.router.repository.UserRepository;
import com.ocbc.project.im.router.service.UserService;
import com.ocbc.project.im.router.vo.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserGroupRepository userGroupRepository;

    private final UserRepository userRepository;
    @Override
    public void addUser(UserDTO userDTO) {

        User user = new User();

        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        user.setCreateTime(LocalDateTime.now());
        user.setLastUpdatedTime(LocalDateTime.now());

        Set<UserGroup> userGroups = new HashSet<>(userGroupRepository.findByIdIn(userDTO.getGroupIds()));

        user.setUserGroups(userGroups);

        userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(UserDTO userDTO) {

        Specification<User> spec = (root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotBlank(userDTO.getUsername())) {
                criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("username"), "%" + userDTO.getUsername() + "%"));
            }

            if (StringUtils.isNotBlank(userDTO.getEmail())) {
                criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("email"), "%" + userDTO.getEmail() + "%"));
            }

           return predicate;
        };

        return userRepository.findAll(spec, PageRequest.of(userDTO.getPageNum(), userDTO.getPageSize()));
    }
}
