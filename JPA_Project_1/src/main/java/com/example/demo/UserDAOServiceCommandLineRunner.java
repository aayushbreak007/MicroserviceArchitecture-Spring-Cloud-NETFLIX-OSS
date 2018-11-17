package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.UserDAOService;


//component means it is managed by spring framework
@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner {
	
	private static final Logger log=LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class); 

	//DEPENDENCY INJECTION USING AUTOWIRED
	@Autowired
	private UserDAOService userDAOService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user1=new User("Jack", "Admin");
		long insert=userDAOService.insert(user1);//saves to the in-memory database--H2
		log.info("New user created:"+user1);
		
		
	}

}
