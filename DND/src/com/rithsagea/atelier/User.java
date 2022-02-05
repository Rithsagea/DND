package com.rithsagea.atelier;

import java.util.UUID;

public class User {
	private long id;
	private UUID characterId;
	
	public User(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public UUID getCharacterId() {
		return characterId;
	}
	
	public void setCharacterId(UUID id) {
		this.characterId = id;
	}
}
