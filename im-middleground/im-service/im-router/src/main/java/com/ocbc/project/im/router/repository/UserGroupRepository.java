package com.ocbc.project.im.router.repository;

import com.ocbc.project.im.router.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByIdIn(List<Long> ids);
}
