package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;


//JPA REPOSITORY TAKES THE DATA TYPES OF ENTITY AND THE ID OF THAT ENTITY 
//JPA respository will handle basic operations for insertion/deletion and updation
public interface UserRepository extends JpaRepository<User,Long> {

	
	
}
