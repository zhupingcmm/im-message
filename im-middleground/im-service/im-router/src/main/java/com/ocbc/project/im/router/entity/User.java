package com.ocbc.project.im.router.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(
            name = "user_user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<UserGroup> userGroups = new HashSet<>();





    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "last_updated_time", nullable = false)
    private LocalDateTime lastUpdatedTime;


    public void addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
        userGroup.getUsers().add(this);
    }

    public void removeUserGroup(UserGroup userGroup) {
        this.userGroups.remove(userGroup);
        userGroup.getUsers().remove(this);
    }
}
