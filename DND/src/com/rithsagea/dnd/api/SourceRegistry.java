package com.rithsagea.dnd.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SourceRegistry {
	
	private static File dir;
	private static Map<String, SourceBook> books;
	private static Map<Class<?>, Map<String, Object>> registry;
	
	/**
	 * Reads in header file TODO make this
	 * @param dir the directory of the source books
	 */
	public static void init(File dir) {
		SourceRegistry.dir = dir;
		
		if(!dir.exists()) {
			dir.mkdir();
		}
	}
	
	/**
	 * Reads in and loads all the source books
	 */
	public static void load() {
		registry = new HashMap<>();
		books = new HashMap<>();
		
		if(dir.isDirectory()) {
			for(File file : dir.listFiles()) {
				SourceBook book = new SourceBook(file);
				book.init();
				
				books.put(book.name, book);
			}
		}
		
		books.values().stream().sorted().forEach((book) -> {
			book.load();
			loadBook(book);
		});
	}
	
	private static void loadBook(SourceBook book) {
		for(String key1 : SourceBook.DATA_TYPES.keySet()) {
			Class<?> clazz = SourceBook.DATA_TYPES.get(key1);
			
			if(!registry.containsKey(clazz)) registry.put(clazz, new HashMap<>());
			Map<String, Object> values = book.data.get(clazz);
			Map<String, Object> registryMap = registry.get(clazz);
			
			for(Entry<String, Object> entry : values.entrySet()) {
				if(!registryMap.containsKey(entry.getKey())) // TODO add override flag
					registryMap.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public static File getDir() {
		return dir;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getItem(String id, Class<?> clazz) {
		if(!registry.containsKey(clazz)) return null; // registry doesn't exist
		return (T) (registry.get(clazz).get(id));
	}
}
