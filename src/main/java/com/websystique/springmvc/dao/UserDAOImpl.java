package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import websystique.springmvc.db.DbConnection;
import websystique.springmvc.util.UserMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.websystique.springmvc.model.User;

public class UserDAOImpl implements UserDAO {

	public List<User> findAllUsers() {
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		List<User> usersList = new ArrayList<User>();
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			try{
				usersList.add(UserMapper.getUser(obj));
			} catch(Exception e){
				//casting errors might occor
				e.printStackTrace();
			}
		}
		return usersList;
	}

	public User findById(long id) {
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		DBObject dbObj = coll.findOne(query);
		if(dbObj != null){
			return UserMapper.getUser(dbObj);
		}else {
			return null;
		}
	}

	public User findByName(String name) {
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		BasicDBObject query = new BasicDBObject();
		query.put("username", name);
		DBObject dbObj = coll.findOne(query);
		if(dbObj != null){
			return UserMapper.getUser(dbObj);
		} else {
			return null;
		}
	}

	public void saveUser(User user) {
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		BasicDBObject document = new BasicDBObject();
		coll.insert(UserMapper.getUserWithId(user));
	}

	public void updateUser(User user) {
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		BasicDBObject searchQuery = new BasicDBObject().append("_id", user.getId());
		coll.update(searchQuery, UserMapper.getUser(user));
	}

	public void deleteUserById(long id) {
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		DBObject dbObj = coll.findOne(query);
		coll.remove(dbObj);
	}
	
	public void deleteAllUsers(){
		DBCollection coll = DbConnection.getMongoClientDBCollection("user");
		coll.drop();
	}
}
