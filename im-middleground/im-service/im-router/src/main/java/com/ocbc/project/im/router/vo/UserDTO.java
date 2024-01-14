package com.ocbc.project.im.router.vo;

import com.ocbc.project.im.router.entity.UserGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends PageInfo{

    private Long userId;

    private String username;

    private String email;

    private String password;

    private List<Long> groupIds;

}
