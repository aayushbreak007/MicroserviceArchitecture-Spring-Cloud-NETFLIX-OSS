package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//this is an entity
//map this entity to a table-user then make User class as an entity

@Entity
public class User {

	@Id   
	@GeneratedValue
	private long id;
	private String name;
	private String role;
	
	//we need a default constructor--required by jpa
	protected User() {}
	
	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
	
}
