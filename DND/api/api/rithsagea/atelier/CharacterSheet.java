package api.rithsagea.atelier;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class CharacterSheet {
	
	@BsonId
	private ObjectId id;
	
	private String name;
	
	public CharacterSheet(ObjectId id) {
		this.id = id;
	}
	
	public CharacterSheet() {
	
	}
	
	/////////////////
	/// ACCESSORS ///
	/////////////////
	
	public ObjectId getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	////////////////
	/// MUTATORS ///
	////////////////
	
	public void setName(String name) {
		this.name = name;
	}
}
