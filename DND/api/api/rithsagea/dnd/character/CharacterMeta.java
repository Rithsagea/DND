package api.rithsagea.dnd.character;

import java.util.HashMap;
import java.util.Map;

public class CharacterMeta {
	
	private Map<String, Meta> metadata;
	
	public CharacterMeta() {
		metadata = new HashMap<>();
	}
	
	public Meta get(String key) {
		return metadata.get(key);
	}
	
	public void set(String key, Meta meta) {
		metadata.put(key, meta);
	}
}
