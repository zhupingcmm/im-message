package com.ocbc.project.im.router.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_group")
@Data
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(mappedBy = "userGroups")
    private Set<User> users = new HashSet<>();


    @Column(name = "create_time", nullable = false)
    private LocalDateTime create_time;

    @Column(name = "last_updated_time", nullable = false)
    private LocalDateTime last_updated_time;

}
