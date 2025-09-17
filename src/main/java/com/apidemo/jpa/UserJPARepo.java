package com.apidemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidemo.user.User;

public interface UserJPARepo extends JpaRepository<User, Integer>{
	

}
