package api.rithsagea.atelier;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class User {
	
	@BsonId
	private long id;
	
	private String name;
	private ObjectId characterId;
	
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
	
	public ObjectId getCharacterId() {
		return characterId;
	}
	
	public void setCharacterId(ObjectId id) {
		this.characterId = id;
	}
}
