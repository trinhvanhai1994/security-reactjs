package com.homedirect.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homedirect.user.entity.Role;
import com.homedirect.user.util.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleUser);
}
