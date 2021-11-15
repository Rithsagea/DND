package com.rithsagea.dnd.api;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.rithsagea.dnd.api.data.AbilityScore;
import com.rithsagea.dnd.api.data.Alignment;
import com.rithsagea.dnd.api.data.DndItem;
import com.rithsagea.dnd.api.data.Language;
import com.rithsagea.dnd.api.data.Monster;
import com.rithsagea.dnd.api.data.Proficiency;
import com.rithsagea.dnd.api.data.Skill;
import com.rithsagea.dnd.api.data.Spell;
import com.rithsagea.dnd.api.data.classes.DndClass;
import com.rithsagea.dnd.api.data.classes.DndClassFeature;
import com.rithsagea.dnd.api.data.classes.DndSubclass;
import com.rithsagea.dnd.api.data.equipment.Equipment;
import com.rithsagea.dnd.api.data.equipment.EquipmentAdapter;
import com.rithsagea.dnd.api.data.races.DndRace;
import com.rithsagea.dnd.api.data.races.DndRaceTrait;
import com.rithsagea.dnd.api.data.races.DndSubrace;

public class Datapack {
	@SerializedName("ability_scores")
	public Map<String, AbilityScore> AbilityScore = new HashMap<>();
	
	@SerializedName("skills")
	public Map<String, Skill> Skill = new HashMap<>();
	
	@SerializedName("proficiencies")
	public Map<String, Proficiency> Proficiency = new HashMap<>();
	
	@SerializedName("languages")
	public Map<String, Language> Language = new HashMap<>();
	
	@SerializedName("alignments")
	public Map<String, Alignment> Alignment = new HashMap<>();
	
	@SerializedName("equipment")
	public Map<String, Equipment> Equipment = new HashMap<>();
	
	@SerializedName("classes")
	public Map<String, DndClass> DndClass = new HashMap<>();
	
	@SerializedName("subclasses")
	public Map<String, DndSubclass> DndSubclass = new HashMap<>();
	
	@SerializedName("class_features")
	public Map<String, DndClassFeature> DndClassFeature = new HashMap<>();
	
	@SerializedName("races")
	public Map<String, DndRace> DndRace = new HashMap<>();
	
	@SerializedName("subraces")
	public Map<String, DndSubrace> DndSubrace = new HashMap<>();
	
	@SerializedName("traits")
	public Map<String, DndRaceTrait> DndRaceTrait = new HashMap<>();
	
	@SerializedName("spells")
	public Map<String, Spell> Spell = new HashMap<>();
	
	@SerializedName("monsters")
	public Map<String, Monster> Monster = new HashMap<>();
	
	private <T extends DndItem> void registerItems(Map<String, T> map, Collection<T> items) {
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
	
	public void registerSubclasses(Collection<DndSubclass> items) {
		registerItems(DndSubclass, items);
	}
	
	public void registerClassFeatures(Collection<DndClassFeature> items) {
		registerItems(DndClassFeature, items);
	}
	
	public void registerRaces(Collection<DndRace> items) {
		registerItems(DndRace, items);
	}
	
	public void registerSubraces(Collection<DndSubrace> items) {
		registerItems(DndSubrace, items);
	}
	
	public void registerTraits(Collection<DndRaceTrait> items) {
		registerItems(DndRaceTrait, items);
	}
	
	public void registerSpells(Collection<Spell> items) {
		registerItems(Spell, items);
	}
	
	public void registerMonsters(Collection<Monster> items) {
		registerItems(Monster, items);
	}
	
	public void registerDatapack(Datapack data) {
		registerAbilityScores(data.AbilityScore.values());
		registerSkills(data.Skill.values());
		registerProficiencies(data.Proficiency.values());
		registerLanguages(data.Language.values());
		registerAlignments(data.Alignment.values());
		registerEquipment(data.Equipment.values());
		registerClasses(data.DndClass.values());
		registerSubclasses(data.DndSubclass.values());
		registerClassFeatures(data.DndClassFeature.values());
		registerRaces(data.DndRace.values());
		registerSubraces(data.DndSubrace.values());
		registerTraits(data.DndRaceTrait.values());
		registerSpells(data.Spell.values());
		registerMonsters(data.Monster.values());
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
