package com.example.rest.webservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



//this is a java entity managed by JPA now
@ApiModel(description=" All details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	//the name should have at-least 2 characters
	@Size(min=2,message="Name should have atleast 2 characters")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;
	
	//birth date cannot be in the future
	@Past(message="Birth date cannot be in the future")
	@ApiModelProperty(notes="Birth date cannot be in the future")
	private Date birthDate;
	
	
	//Post relationship 
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	protected User() {}
	public User(Integer id,String name, Date birthDate) {
		super();
		this.id=id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
	
	
	
}
