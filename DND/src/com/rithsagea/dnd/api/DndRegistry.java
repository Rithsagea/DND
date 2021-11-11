package com.rithsagea.dnd.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rithsagea.dnd.api.misc.AbilityScore;
import com.rithsagea.dnd.api.misc.Alignment;
import com.rithsagea.dnd.api.misc.CharacterSize;
import com.rithsagea.dnd.api.misc.Language;

public class DndRegistry {
	
	public static Registry<AbilityScore> AbilityScore = new Registry<>();
	public static Registry<Alignment> Alignment = new Registry<>();
	public static Registry<CharacterSize> CharacterSize = new Registry<>();
	public static Registry<Language> Language = new Registry<>();
	
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(Registry.class, new RegistryAdapater())
			.create();
	
	public static void registerAbilityScores(List<AbilityScore> abilityScores) {
		for(AbilityScore abilityScore : abilityScores) {
			AbilityScore.register(abilityScore.id, abilityScore);
		}
	}
	
	public static void loadRegistry(File file) {
		JsonObject registry = null;
		try(Reader reader = new FileReader(file)) {
			registry = gson.fromJson(reader, JsonObject.class);
			AbilityScore = gson.fromJson(registry.get("ability_score"), new TypeToken<Registry<AbilityScore>>() {}.getType());
			Alignment = gson.fromJson(registry.get("alignment"), new TypeToken<Registry<Alignment>>() {}.getType());
			CharacterSize = gson.fromJson(registry.get("character_size"), new TypeToken<Registry<CharacterSize>>() {}.getType());
			Language = gson.fromJson(registry.get("language"), new TypeToken<Registry<Language>>() {}.getType());

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
		registry.add("ability_score", gson.toJsonTree(AbilityScore));
		registry.add("alignment", gson.toJsonTree(Alignment));
		registry.add("character_size", gson.toJsonTree(CharacterSize));
		registry.add("language", gson.toJsonTree(Language));
		
		try(Writer writer = new FileWriter(file)) {
			gson.toJson(registry, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
