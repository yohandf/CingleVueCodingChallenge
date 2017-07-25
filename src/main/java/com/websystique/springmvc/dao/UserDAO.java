package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.User;

public interface UserDAO {
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
}
