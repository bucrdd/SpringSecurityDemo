package com.example.repository;

import com.example.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserInfo, Long> {

  UserInfo findByUsername(String username);
}
