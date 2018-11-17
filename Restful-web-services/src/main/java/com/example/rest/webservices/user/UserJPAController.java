package com.example.rest.webservices.user;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAController {

	
	//for internationalization
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		
		return userRepository.findAll();//this method is pre-defined in the JPARepository
	}
		
	
	
	//implementing this Rest service with Hateoas
	//this will return a resource/controller
	@GetMapping("/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		Optional<User> user=userRepository.findById(id);//if id doesn't exist then it will return a proper object
		if(!user.isPresent()) {
			throw new UserNotFoundException("id:"+id);//custom exception class created
		}
		
		//now will add link to all users service through HATEOAS
		//ADDING LINK TO "retrieveAllUsers()"
		Resource<User> resource=new Resource<User>(user.get());
		
		//adding links(retrieveAllUsers()) to this resource now
		//do static import for controllerLinkBuilder methods
		ControllerLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(link.withRel("All-Users"));
		
		return resource;
		
	}
	
	
	//we need a restClient called "Postman"
	//we need to validate the user object that we are sending for insertion through the request body
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) {
		
		
		User savedUser=userRepository.save(user);
		
		//now we need to return the status code of created
		//this will get the current uri with the new id
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();//sends the response/status code
	}
	
	
	//delete
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		userRepository.deleteById(id);
		
	}
	
	
	
	//internationalization of the services
	//user will pass the location 
	@GetMapping("/jpa/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
		
	}
	
	//*********************************************************************************************8
	
	//POST CLASS
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){
         
		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Notfound:id-"+id);
		}
		
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createNewPost(@PathVariable int id,@RequestBody Post post) {
		
		//get the user first
		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Notfound:id-"+id);
		}
		
		User user=userOptional.get();
		post.setUser(user);
		
		
		//create a post repository
		
		postRepository.save(post);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();//sends the response/status code
	}
	
	
	
	
	
	
	
	
}
