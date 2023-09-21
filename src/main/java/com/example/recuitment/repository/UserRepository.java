package com.example.recuitment.repository;

import com.example.recuitment.model.user.AppUser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findUsersByUsername(String username);
    Boolean existsByUsername(String username);
}
