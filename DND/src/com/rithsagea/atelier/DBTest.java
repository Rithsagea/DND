package com.rithsagea.atelier;

import java.io.File;
import java.util.UUID;

import api.rithsagea.atelier.AtelierDatabase;
import api.rithsagea.atelier.Config;
import api.rithsagea.atelier.User;

public class DBTest {
	public static void main(String[] args) {
		Config config = new Config(new File("config.properties"));
		config.load(); config.save();
		AtelierDatabase db = new AtelierDatabase(config.getDatabaseUrl());
		
		User user = new User(0l);
		user.setName("Console");
		user.setCharacterId(UUID.randomUUID());
		db.updateUser(user);
		System.out.println(user.getName() + ": " + user.getCharacterId());
		
		user = db.findUser(0l);
		
		System.out.println(user.getName() + ": " + user.getCharacterId());
	}
}
