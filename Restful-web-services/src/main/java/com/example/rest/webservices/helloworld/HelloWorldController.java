package com.example.rest.webservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//this is how we tell spring that this controller is going to handle HTTP requests
@RestController
public class HelloWorldController {

	//GET
	//URI-/hello-world
	//create a method which returns hello world
	//use Request mapping or we can use GETMapping(path="/hello-world")
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String helloWorld() {
		return "Hello Worlds";
	}
	
	
	//Returning a JAVA bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		//will return in JSON structure
		return new HelloWorldBean("Hello world!!");
	}
	
	//passing a path variable
	///hello-world/path-variable/aayush-----here 'aayush' will be mapped to {name}
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s", name));
	}
	
}
