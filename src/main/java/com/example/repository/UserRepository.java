package com.example.repository;

import com.example.domain.UserInfo;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {

  Optional<UserInfo> findByUsername(String username);

  boolean existsByUsername(String username);

}
