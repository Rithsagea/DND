package com.rithsagea.dnd.api;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.rithsagea.dnd.api.types.Alignment;

public class SourceBook implements Comparable<SourceBook> {

	public static final Map<String, Class<?>> DATA_TYPES = new LinkedHashMap<>();
	public static final Map<Class<?>, String> TYPE_LABELS = new HashMap<>();
	static {
		DATA_TYPES.put("alignments", Alignment.class);
		
		for(Entry<String, Class<?>> entry : DATA_TYPES.entrySet()) {
			TYPE_LABELS.put(entry.getValue(), entry.getKey());
		}
	}
	
	protected File dir;
	protected Map<Class<?>, Map<String, Object>> data;
	
	protected int priority;
	protected String name;
	
	public SourceBook(File dir) {
		this.dir = dir;
	}
	
	public void init() {
		//priority + headers
	}
	
	public void load() {
		for(File file : dir.listFiles()) {
			String fileName = file.getName();
			if(fileName.endsWith(".json")) {
				Class<?> clazz = DATA_TYPES.get(fileName.substring(0, fileName.length() - 5));
				
			}
		}
		
		//actually import classes and stuffs
	}
	
	public void save() {
		
	}
	
	public File getDir() {
		return dir;
	}

	@Override
	public int compareTo(SourceBook book) {
		return priority - book.priority;
	}
}
