package com.example.security.repository;

import com.example.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserInfo, Long> {

  UserInfo findByUsername(String username);
}
