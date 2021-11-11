package com.rithsagea.dnd.api;

import java.util.HashMap;

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
}
