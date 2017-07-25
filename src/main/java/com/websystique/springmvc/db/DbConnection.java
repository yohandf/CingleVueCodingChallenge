package websystique.springmvc.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DbConnection {

	public static MongoClient getMongoClient(){
		MongoClient mongo = new MongoClient("localhost", 27017);
		return mongo;
	}
	
	public static DB getMongoClientDB(){
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		return db;
	}
	
	public static DBCollection getMongoClientDBCollection(String collection){
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mydb");
		DBCollection col = db.getCollection(collection);
		return col;
	}
}
