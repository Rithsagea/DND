package com.rithsagea.dnd.api;

import java.util.HashMap;
import java.util.Set;

public class Registry<T> {
	private HashMap<String, T> registry;
	
	public Registry() {
		registry = new HashMap<>();
	}
	
	public T get(String key) {
		return registry.get(key);
	}
	
	public T register(String key, T value) {
		return registry.put(key, value);
	}
	
	public void register(Registry<T> r) {
		for(String k : r.getKeys()) {
			if(!registry.containsKey(k)) {
				registry.put(k, r.get(k));
			}
		}
	}
	
	public Set<String> getKeys() {
		return registry.keySet();
	}
	
	public HashMap<String, T> getMap() {
		return registry;
	}
}
