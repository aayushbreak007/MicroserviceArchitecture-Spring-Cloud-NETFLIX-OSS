package com.example.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;


//we can use @repository as well...but since this is a simple static arraylist--so we use component
@Component
public class UserDAOService {

		private static List<User> users=new ArrayList<>();
		private static int usersCount=3;
		
		//adds to local embedded storage called H2-database
		static {
			users.add(new User(1,"Jack", new Date()));
			users.add(new User(2,"Adam", new Date()));
			users.add(new User(3,"Jim", new Date()));
		}
		
		//1.)
		public List<User> findAll(){
			return users;
		}
		
		//2.)
		public User save(User user) {
			if(user.getId()==null) {
				//pre-increment
				user.setId(++usersCount);
			}
			 users.add(user);
			 return user;
		}
		
		//3.)
		public User findOne(int id) {
			for(User user:users) {
				if(user.getId()==id) {
					return user;
				}
			}
			return null;
		}
		
		//4.)
		public User deleteById(int id) {
			Iterator<User> iterator=users.iterator();
			while(iterator.hasNext()) {
				User user=iterator.next();
				if(user.getId()==id) {
					iterator.remove();
					return user;
				}
			}
			return null;
		}
		
}