package com.rithsagea.dnd.api;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.rithsagea.dnd.api.data.AbilityScore;
import com.rithsagea.dnd.api.data.Alignment;
import com.rithsagea.dnd.api.data.DndItem;
import com.rithsagea.dnd.api.data.Language;
import com.rithsagea.dnd.api.data.Proficiency;
import com.rithsagea.dnd.api.data.Skill;

public class Datapack {
	@SerializedName("ability_scores")
	public HashMap<String, AbilityScore> AbilityScore;
	
	@SerializedName("skills")
	public HashMap<String, Skill> Skill;
	
	@SerializedName("proficiencies")
	public HashMap<String, Proficiency> Proficiency;
	
	@SerializedName("languages")
	public HashMap<String, Language> Language;
	
	@SerializedName("alignments")
	public HashMap<String, Alignment> Alignment;
	
	private <T extends DndItem> void registerItems(HashMap<String, T> map, Collection<T> items) {
		for(T item : items) {
			if(!map.containsKey(item.id))
				map.put(item.id, item);
		}
	}
	
	public void registerAbilityScores(Collection<AbilityScore> items) {
		registerItems(AbilityScore, items);
	}
	
	public void registerSkills(Collection<Skill> items) {
		registerItems(Skill, items);
	}
	
	public void registerProficiencies(Collection<Proficiency> items) {
		registerItems(Proficiency, items);
	}
	
	public void registerLanguages(Collection<Language> items) {
		registerItems(Language, items);
	}
	
	public void registerAlignments(Collection<Alignment> items) {
		registerItems(Alignment, items);
	}
	
	public void registerDatapack(Datapack data) {
		registerAbilityScores(data.AbilityScore.values());
		registerSkills(data.Skill.values());
		registerProficiencies(data.Proficiency.values());
		registerLanguages(data.Language.values());
		registerAlignments(data.Alignment.values());
	}
	
	private static final Gson gson = new GsonBuilder()
			.create();
	
	public static Datapack loadDatapack(File file) {
		try {
			return gson.fromJson(new FileReader(file), Datapack.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void saveDatapack(File file, Datapack datapack) {
		try(Writer writer = new FileWriter(file)) {
			gson.toJson(datapack, Datapack.class, writer);
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
