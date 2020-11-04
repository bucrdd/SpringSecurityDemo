package com.example.repository;

import com.example.domain.UserInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {

  boolean existsByUsername(String username);

  List<UserInfo> findAllByUsernameLike(String username);

  Optional<UserInfo> findByUsername(String username);

}
