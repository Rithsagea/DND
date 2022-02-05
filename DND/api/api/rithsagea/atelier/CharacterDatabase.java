package api.rithsagea.atelier;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CharacterDatabase {
	
	private Map<UUID, CharacterSheet> characters;
	
	public CharacterDatabase() {
		characters = new HashMap<>();
	}
	
	public CharacterSheet newSheet() {
		CharacterSheet sheet = new CharacterSheet();
		UUID id = UUID.randomUUID();
		
		characters.put(id, sheet);
		
		return sheet;
	}
	
	public CharacterSheet getSheet(UUID id) {
		return characters.get(id);
	}
}
