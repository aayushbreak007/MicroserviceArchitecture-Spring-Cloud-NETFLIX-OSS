package com.example.rest.webservices.user;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResourceController {

	
	//for internationalization
	@Autowired
	private MessageSource messageSource;
	//controllers are used because of mapping Uri to the services
	//make use of the DAL layer or DAO
	
	@Autowired
	private UserDAOService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		
		return service.findAll();
	}
		
	
	
	//implementing this Rest service with Hateoas
	//this will return a resource/controller
	@GetMapping("/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user=service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id:"+id);//custom exception class created
		}
		
		//now will add link to all users service through HATEOAS
		//ADDING LINK TO "retrieveAllUsers()"
		Resource<User> resource=new Resource<User>(user);
		
		//adding links(retrieveAllUsers()) to this resource now
		//do static import for controllerLinkBuilder methods
		ControllerLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(link.withRel("All-Users"));
		
		return resource;
		
	}
	
	
	//we need a restClient called "Postman"
	//we need to validate the user object that we are sending for insertion through the request body
	
	@PostMapping("/users")
	public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) {
		
		//@requestBody-- whatever i pass in the body of the parameter will be mapped to 'user'
		//basically it maps the "JSON" passed in the body to the 'user' object.
		
		User savedUser=service.save(user);
		
		//now we need to return the status code of created
		//this will get the current uri with the new id
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();//sends the response/status code
	}
	
	
	//delete
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user=service.deleteById(id);
		if(user==null) {
			throw new UserNotFoundException("id:"+id);//custom exception class created
		}
		//if nothing is returned then deleted successfully
		
	}
	
	
	
	//internationalization of the services
	//user will pass the location 
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
		
	}
	
	
	
}