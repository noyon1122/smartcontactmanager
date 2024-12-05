package com.smart.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.main.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
}
