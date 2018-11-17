package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.UserDAOService;
import com.example.demo.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {
	
	private static final Logger log=LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class); 

	
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user2=new User("Jill", "Admin");
		userRepository.save(user2);//automatically handled by JPA repository---save for insert and update
		log.info("New user created:"+user2);
		
		
	}

}
