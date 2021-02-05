package com.eureka.auth.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eureka.auth.entities.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
