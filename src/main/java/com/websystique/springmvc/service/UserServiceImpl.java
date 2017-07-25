package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.UserDAO;
import com.websystique.springmvc.dao.UserDAOImpl;
import com.websystique.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	public List<User> findAllUsers() {
		UserDAO userDao  = new UserDAOImpl();
		return userDao.findAllUsers();
	}
	
	public User findById(long id) {
		UserDAO userDao  = new UserDAOImpl();
		return userDao.findById(id);
	}
	
	public User findByName(String name) {
		UserDAO userDao  = new UserDAOImpl();
		return userDao.findByName(name);
	}
	
	public void saveUser(User user) {
		UserDAO userDao  = new UserDAOImpl();
		userDao.saveUser(user);
	}

	public void updateUser(User user) {
		UserDAO userDao  = new UserDAOImpl();
		userDao.updateUser(user);
	}

	public void deleteUserById(long id) {
		UserDAO userDao  = new UserDAOImpl();
		userDao.deleteUserById(id);
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		UserDAO userDao  = new UserDAOImpl();
		userDao.deleteAllUsers();
	}


}
