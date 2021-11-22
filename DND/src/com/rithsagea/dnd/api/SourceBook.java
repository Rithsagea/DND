package com.rithsagea.dnd.api;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rithsagea.dnd.api.types.AbilityScore;
import com.rithsagea.dnd.api.types.Alignment;

public class SourceBook implements Comparable<SourceBook> {

	public static final Map<String, Class<?>> DATA_TYPES = new LinkedHashMap<>();
	public static final Map<Class<?>, String> TYPE_LABELS = new HashMap<>();
	static {
		DATA_TYPES.put("alignments", Alignment.class);
		DATA_TYPES.put("ability-scores", AbilityScore.class);
		
		for(Entry<String, Class<?>> entry : DATA_TYPES.entrySet()) {
			TYPE_LABELS.put(entry.getValue(), entry.getKey());
		}
	}
	
	private static final Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.create();
	
	protected File dir;
	protected Map<Class<?>, Map<String, Object>> data;
	
	protected int priority;
	protected String name;
	
	public SourceBook(File dir) {
		this.dir = dir;
		name = dir.getName();
		
		data = new HashMap<>();
	}
	
	public void init() {
		//priority + headers
	}
	
	public void load() {
		for(File file : dir.listFiles()) {
			String fileName = file.getName();
			if(fileName.endsWith(".json")) {
				Class<?> clazz = DATA_TYPES.get(fileName.substring(0, fileName.length() - 5));
				Map<String, Object> map = new LinkedHashMap<>();
				try(Reader reader = new FileReader(file)) {
					Map<String, ?> input = gson.fromJson(reader,
							TypeToken.getParameterized(LinkedHashMap.class, String.class, clazz).getType());
					for(Entry<String, ?> entry : input.entrySet()) {
						map.put(entry.getKey(), entry.getValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				data.put(clazz, map);
			}
		}
	}
	
	public void save() {
		for(Entry<String, Class<?>> entry : DATA_TYPES.entrySet()) {
			try(Writer writer = new FileWriter(dir.getPath() + "/" + entry.getKey() + ".json")) {
				gson.toJson(data.get(entry.getValue()), writer);
				
				writer.flush();
				writer.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void register(String key, Object obj) {
		Class<?> clazz = obj.getClass();
		if(!data.containsKey(clazz)) data.put(clazz, new LinkedHashMap<>());
		data.get(clazz).put(key, obj);
	}
	
	public File getDir() {
		return dir;
	}

	@Override
	public int compareTo(SourceBook book) {
		return priority - book.priority;
	}
}