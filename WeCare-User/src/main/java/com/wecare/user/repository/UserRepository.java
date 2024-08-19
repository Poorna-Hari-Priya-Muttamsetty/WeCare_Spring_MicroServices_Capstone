package com.wecare.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wecare.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username = ?1")
    User findByUserName(String username);
}
