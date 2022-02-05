package api.rithsagea.atelier;

import java.util.UUID;

public class User {
	private long id;
	private String name;
	private UUID characterId;
	
	public User() {
		
	}
	
	public User(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public UUID getCharacterId() {
		return characterId;
	}
	
	public void setCharacterId(UUID id) {
		this.characterId = id;
	}
}
