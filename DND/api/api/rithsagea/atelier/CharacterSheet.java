package api.rithsagea.atelier;

import java.util.UUID;

public class CharacterSheet {
	private String name;
	private UUID id;
	
	public CharacterSheet(UUID id) {
		this.id = id;
	}
	
	public CharacterSheet() {
		id = UUID.randomUUID();
	}
	
	/////////////////
	/// ACCESSORS ///
	/////////////////
	
	public UUID getId() {
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
