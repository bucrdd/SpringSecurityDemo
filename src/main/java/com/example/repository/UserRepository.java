package com.example.repository;

import com.example.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {

    boolean existsByUsername(String username);

    List<UserInfo> findAllByUsernameLike(String username);

    Optional<UserInfo> findByUsername(String username);

}
