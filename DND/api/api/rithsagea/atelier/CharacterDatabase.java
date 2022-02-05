package api.rithsagea.atelier;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class CharacterDatabase {
	
	private Map<UUID, CharacterSheet> characters;
	private Random rand;
	
	public CharacterDatabase() {
		characters = new HashMap<>();
		rand = new Random(29l);
	}
	
	public CharacterSheet newSheet() {
		
		UUID id = new UUID(rand.nextLong(), rand.nextLong());
		CharacterSheet sheet = new CharacterSheet(id);
		
		characters.put(id, sheet);
		
		return sheet;
	}
	
	public CharacterSheet getSheet(UUID id) {
		return characters.get(id);
	}
	
	public Set<UUID> getIds() {
		return characters.keySet();
	}
}
