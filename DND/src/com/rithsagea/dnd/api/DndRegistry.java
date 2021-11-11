package com.rithsagea.dnd.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rithsagea.dnd.api.misc.AbilityScore;
import com.rithsagea.dnd.api.misc.Alignment;
import com.rithsagea.dnd.api.misc.CharacterSize;
import com.rithsagea.dnd.api.misc.DndItem;
import com.rithsagea.dnd.api.misc.Language;
import com.rithsagea.dnd.api.misc.Skill;

public class DndRegistry {
	
	public static Registry<AbilityScore> AbilityScore = new Registry<>();
	public static Registry<Skill> Skill = new Registry<>();
	
	public static Registry<Alignment> Alignment = new Registry<>();
	public static Registry<CharacterSize> CharacterSize = new Registry<>();
	public static Registry<Language> Language = new Registry<>();
	
	private static HashMap<String, Registry<?>> REGISTRY_INDEX;
	static {
		REGISTRY_INDEX = new HashMap<>();
		REGISTRY_INDEX.put("ability_score", AbilityScore);
		REGISTRY_INDEX.put("skill", Skill);
		REGISTRY_INDEX.put("alignment", Alignment);
		REGISTRY_INDEX.put("character_size", CharacterSize);
		REGISTRY_INDEX.put("language", Language);
	}
	
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(Registry.class, new RegistryAdapter())
			.create();
	
	public static void registerAbilityScores(List<AbilityScore> abilityScores) {
		for(AbilityScore abilityScore : abilityScores) {
			AbilityScore.register(abilityScore.id, abilityScore);
		}
	}
	
	public static void registerSkills(List<Skill> skills) {
		for(Skill skill : skills) {
			Skill.register(skill.id, skill);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadRegistry(File file) {
		JsonObject registry = null;
		try(Reader reader = new FileReader(file)) {
			registry = gson.fromJson(reader, JsonObject.class);
			
			for(String key : registry.keySet()) {
				Registry<?> r = REGISTRY_INDEX.get(key);
				r.registry = gson.fromJson(registry.get(key), r.registry.getClass());
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
