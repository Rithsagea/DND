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
import com.rithsagea.dnd.api.data.DndClass;
import com.rithsagea.dnd.api.data.DndItem;
import com.rithsagea.dnd.api.data.Language;
import com.rithsagea.dnd.api.data.Proficiency;
import com.rithsagea.dnd.api.data.Skill;
import com.rithsagea.dnd.api.data.equipment.Equipment;
import com.rithsagea.dnd.api.data.equipment.EquipmentAdapter;

public class Datapack {
	@SerializedName("ability_scores")
	public HashMap<String, AbilityScore> AbilityScore = new HashMap<>();
	
	@SerializedName("skills")
	public HashMap<String, Skill> Skill = new HashMap<>();
	
	@SerializedName("proficiencies")
	public HashMap<String, Proficiency> Proficiency = new HashMap<>();
	
	@SerializedName("languages")
	public HashMap<String, Language> Language = new HashMap<>();
	
	@SerializedName("alignments")
	public HashMap<String, Alignment> Alignment = new HashMap<>();
	
	@SerializedName("equipment")
	public HashMap<String, Equipment> Equipment = new HashMap<>();
	
	@SerializedName("classes")
	public HashMap<String, DndClass> DndClass = new HashMap<>();
	
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
	
	public void registerEquipment(Collection<Equipment> items) {
		registerItems(Equipment, items);
	}
	
	public void registerClasses(Collection<DndClass> items) {
		registerItems(DndClass, items);
	}
	
	public void registerDatapack(Datapack data) {
		registerAbilityScores(data.AbilityScore.values());
		registerSkills(data.Skill.values());
		registerProficiencies(data.Proficiency.values());
		registerLanguages(data.Language.values());
		registerAlignments(data.Alignment.values());
		registerEquipment(data.Equipment.values());
		registerClasses(data.DndClass.values());
	}
	
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(Equipment.class, new EquipmentAdapter())
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