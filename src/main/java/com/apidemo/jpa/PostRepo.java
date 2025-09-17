package com.apidemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidemo.user.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
