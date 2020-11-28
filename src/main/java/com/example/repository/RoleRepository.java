package com.example.repository;

import com.example.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {

    Optional<List<Role>> findAll();
}
