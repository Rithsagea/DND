package com.rithsagea.dnd.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.misc.AbilityScore;
import com.rithsagea.dnd.api.misc.Alignment;
import com.rithsagea.dnd.api.misc.Language;
import com.rithsagea.dnd.api.misc.Proficiency;
import com.rithsagea.dnd.api.misc.Skill;

public class DndRegistry {
	
	public static Registry<AbilityScore> AbilityScore = new Registry<>();
	public static Registry<Skill> Skill = new Registry<>();
	public static Registry<Proficiency> Proficiency = new Registry<>();
	public static Registry<Language> Language = new Registry<>();
	public static Registry<Alignment> Alignment = new Registry<>();
	
	private static HashMap<String, Registry<?>> REGISTRY_INDEX;
	static {
		REGISTRY_INDEX = new HashMap<>();
		REGISTRY_INDEX.put("ability_scores", AbilityScore);
		REGISTRY_INDEX.put("skills", Skill);
		REGISTRY_INDEX.put("proficiencies", Proficiency);
		REGISTRY_INDEX.put("languages", Language);
		REGISTRY_INDEX.put("alignments", Alignment);
	}
	
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(Registry.class, new RegistryAdapter())
			.setPrettyPrinting()
			.create();
	
	private static <T extends DndItem> void registerItems(List<T> items, Registry<T> registry) {
		for(T item : items) {
			registry.register(item.id, item);
		}
	}
	
	public static void registerAbilityScores(List<AbilityScore> abilityScores) {
		registerItems(abilityScores, AbilityScore);
	}
	
	public static void registerSkills(List<Skill> skills) {
		registerItems(skills, Skill);
	}
	
	public static void registerProficiencies(List<Proficiency> proficiencies) {
		registerItems(proficiencies, Proficiency);
	}
	
	public static void registerLanguages(List<Language> languages) {
		registerItems(languages, Language);
	}
	
	public static void registerAlignments(List<Alignment> alignments) {
		registerItems(alignments, Alignment);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadRegistry(File file) {
		JsonObject registry = null;
		try(Reader reader = new FileReader(file)) {
			registry = gson.fromJson(reader, JsonObject.class);
			
			for(String key : registry.keySet()) {
				Registry<?> r = REGISTRY_INDEX.get(key);
				
				gson.fromJson(registry.get(key), r.getClass());
				r.register(gson.fromJson(registry.get(key), r.getClass()));
			}
		} catch (FileNotFoundException e) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveRegistry(File file) {
		JsonObject registry = new JsonObject();
		
		for(String key : REGISTRY_INDEX.keySet()) {
			registry.add(key, gson.toJsonTree(REGISTRY_INDEX.get(key)));
		}
		
		try(Writer writer = new FileWriter(file)) {
			gson.toJson(registry, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
