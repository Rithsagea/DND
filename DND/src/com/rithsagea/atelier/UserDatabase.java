package com.rithsagea.atelier;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
	private Map<Long, User> users;
	
	public UserDatabase() {
		users = new HashMap<>();
	}
	
	public User getUser(long id) {
		if(!users.containsKey(id)) users.put(id, new User(id));
		
		return users.get(id);
	}
}
