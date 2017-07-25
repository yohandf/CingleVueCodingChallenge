package websystique.springmvc.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.websystique.springmvc.model.User;

public class UserMapper {

	
	public static User getUser(DBObject obj){
		Long id = (Long)obj.get("_id");
		String username = (String)obj.get("username");
		String address = (String)obj.get("address");
		String email = (String)obj.get("email");
		User userone = new User(id, username, address, email);
		return userone;
	}
	
	public static BasicDBObject getUser(User user){
		BasicDBObject document = new BasicDBObject();

		document.put("username", user.getUsername());
		document.put("address", user.getAddress());
		document.put("email", user.getEmail());
		return document;
	}
	
	public static BasicDBObject getUserWithId(User user){
		BasicDBObject document = new BasicDBObject();

		document.put("_id", System.currentTimeMillis());
		document.put("username", user.getUsername());
		document.put("address", user.getAddress());
		document.put("email", user.getEmail());
		return document;
	}
}
