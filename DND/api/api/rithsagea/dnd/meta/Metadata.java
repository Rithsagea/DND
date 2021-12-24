package api.rithsagea.dnd.meta;

import java.util.Map;
import java.util.TreeMap;

public class Metadata {
	private Map<String, Meta> data;
	
	public Metadata() {
		data = new TreeMap<>();
	}
	
	public Meta get(String key) {
		return data.get(key);
	}
	
	public Meta set(String key, Meta obj) {
		return data.put(key, obj);
	}
}
