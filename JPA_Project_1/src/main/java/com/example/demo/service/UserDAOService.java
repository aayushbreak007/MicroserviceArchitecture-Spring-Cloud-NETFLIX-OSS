package com.example.demo.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


//this service helps us interact with the database
//Transactional manages concurrent transactions

@Repository
@Transactional
public class UserDAOService {

	//we need to manage User entity
	//to get data and save from the database we need entityManager
	
	/*
	  A persistence context handles a set of entities which hold data to be persisted 
	  in some persistence store (e.g. a database). 
	  In particular, the context is aware of the different states an entity can have (e.g. managed, detached)
	  in relation to both the context and the underlying persistence store.
	*/
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user) {
		entityManager.persist(user);//makes an entity managed and persistant
		return user.getId();
	}

	
}
