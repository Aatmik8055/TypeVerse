package com.typeverse.repository;

import com.typeverse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 1. Look up user by exact username (used during Login)
    Optional<User> findByUsername(String username);

    // 2. Case-insensitive lookup (e.g., "Alice" matches "alice")
    Optional<User> findByUsernameIgnoreCase(String username);

    // 3. Look up by email (used when logging in via email)
    Optional<User> findByEmail(String email);

    // 4. Fast duplicate checks used during Registration
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}