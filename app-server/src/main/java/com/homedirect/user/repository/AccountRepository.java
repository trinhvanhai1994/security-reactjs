package com.homedirect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.homedirect.user.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * from account a INNER JOIN user_roles r ON a.id = account_id where r.role_id =3")
	List<Account> findAllMember();
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsernameOrEmail(String username, String email);
    List<Account> findByIdIn(List<Long> userIds);
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
