package com.example.repository;

import com.example.domain.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {

  Optional<List<Role>> findAll();
}
