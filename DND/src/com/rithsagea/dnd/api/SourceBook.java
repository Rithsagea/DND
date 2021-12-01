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
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rithsagea.dnd.api.types.AbilityScore;
import com.rithsagea.dnd.api.types.Alignment;
import com.rithsagea.dnd.api.types.Coin;
import com.rithsagea.dnd.api.types.DndClass;
import com.rithsagea.dnd.api.types.DndRace;
import com.rithsagea.dnd.api.types.DndSubclass;
import com.rithsagea.dnd.api.types.DndSubrace;
import com.rithsagea.dnd.api.types.Equipment;
import com.rithsagea.dnd.api.types.Feature;
import com.rithsagea.dnd.api.types.IndexedItem;
import com.rithsagea.dnd.api.types.Language;
import com.rithsagea.dnd.api.types.Proficiency;
import com.rithsagea.dnd.api.types.Skill;
import com.rithsagea.dnd.api.types.Trait;

public class SourceBook implements Comparable<SourceBook> {

	public static final Map<String, Class<?>> DATA_TYPES = new LinkedHashMap<>();
	public static final Map<Class<?>, String> TYPE_LABELS = new HashMap<>();
	static {
		DATA_TYPES.put("alignments", Alignment.class);
		DATA_TYPES.put("ability-scores", AbilityScore.class);
		DATA_TYPES.put("skills", Skill.class);
		DATA_TYPES.put("coins", Coin.class);
		DATA_TYPES.put("languages", Language.class);
		DATA_TYPES.put("classes", DndClass.class);
		DATA_TYPES.put("subclasses", DndSubclass.class);
		DATA_TYPES.put("features", Feature.class);
		DATA_TYPES.put("races", DndRace.class);
		DATA_TYPES.put("subraces", DndSubrace.class);
		DATA_TYPES.put("trait", Trait.class);
		DATA_TYPES.put("equipment", Equipment.class);
		DATA_TYPES.put("proficiencies", Proficiency.class);
		
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
				Map<String, Object> map = null;
				try(Reader reader = new FileReader(file)) {
					map = gson.fromJson(reader,
							TypeToken.getParameterized(TreeMap.class, String.class, clazz).getType());
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				if(map != null) data.put(clazz, map);
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
		
		SourceRegistry.registerItem(key, obj);
	}
	
	public void register(IndexedItem item) {
		register(item.id, item);
	}
	
	public boolean unregister(String key, Class<?> clazz) {
		if(data.containsKey(clazz)) {
			data.get(clazz).remove(key);
			
			return true;
		}
		
		return false;
	}
	
	public File getDir() {
		return dir;
	}

	@Override
	public int compareTo(SourceBook book) {
		return priority - book.priority;
	}
}
