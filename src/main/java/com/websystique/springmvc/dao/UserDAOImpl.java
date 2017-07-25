package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

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
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "mydb" );
		System.out.println("findAllUsers User::::::::::::::::: Started");
		DBCollection coll = db.getCollection("user");
		List<User> usersList = new ArrayList<User>();
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Long id = (Long)obj.get("_id");
			String username = (String)obj.get("username");
			String address = (String)obj.get("address");
			String email = (String)obj.get("email");
			User userone = new User(id, username, address, email);
			usersList.add(userone);
		}
		return usersList;
	}

	public User findById(long id) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection("user");

		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);

		DBObject dbObj = col.findOne(query);
		if(dbObj != null){

			Long userId = (Long)dbObj.get("_id");
			String username = (String)dbObj.get("username");
			String address = (String)dbObj.get("address");
			String email = (String)dbObj.get("email");
			User userone = new User(userId, username, address, email);
			return userone;
		}else {
			return null;
		}
	}

	public User findByName(String name) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection("user");

		BasicDBObject query = new BasicDBObject();
		query.put("username", name);

		DBObject dbObj = col.findOne(query);
		if(dbObj != null){

			Long id = (Long)dbObj.get("_id");
			String username = (String)dbObj.get("username");
			String address = (String)dbObj.get("address");
			String email = (String)dbObj.get("email");
			User userone = new User(id, username, address, email);
			return userone;
		} else {
			return null;
		}
	}

	public void saveUser(User user) {
		//		DBObject doc = createDBObject(user);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection("user");
		//		user.setId(counter.incrementAndGet());
		//create user

		BasicDBObject document = new BasicDBObject();

		document.put("_id", System.currentTimeMillis());
		document.put("username", user.getUsername());
		document.put("address", user.getAddress());
		document.put("email", user.getEmail());
		col.insert(document);
		System.out.println("Save User::::::::::::::::: Started");
		//		WriteResult result = col.insert(doc);
		//		System.out.println(result.getUpsertedId());
		//		System.out.println(result.getN());
		//		System.out.println(result.isUpdateOfExisting());
		System.out.println("Save User::::::::::::::::: Done");
		//		users.add(user);
	}

	private static DBObject createDBObject(User user) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("_id", user.getId());
		docBuilder.append("username", user.getUsername());
		docBuilder.append("address", user.getAddress());
		docBuilder.append("email", user.getEmail());
		return docBuilder.get();
	}

	public void updateUser(User user) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection("user");

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("username", user.getUsername());
		newDocument.put("address", user.getAddress());
		newDocument.put("email", user.getEmail());

		BasicDBObject searchQuery = new BasicDBObject().append("_id", user.getId());

		col.update(searchQuery, newDocument);
	}

	public void deleteUserById(long id) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection("user");

		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);

		DBObject dbObj = col.findOne(query);
		col.remove(dbObj);
	}
	
	public void deleteAllUsers(){
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection("user");
		col.drop();
	}
}
