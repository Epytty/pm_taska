package com.taska.pm.repository;

import com.taska.pm.entity.ProjectUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsUsersRepository extends JpaRepository<ProjectUsers, Long> {

    ProjectUsers findByUserId(Long userId);
}
