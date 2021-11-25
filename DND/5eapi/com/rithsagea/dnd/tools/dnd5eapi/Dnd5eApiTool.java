package com.rithsagea.dnd.tools.dnd5eapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.types.AbilityScore;
import com.rithsagea.dnd.api.types.Alignment;
import com.rithsagea.dnd.api.types.Language;
import com.rithsagea.dnd.api.types.Skill;
import com.rithsagea.dnd.api5e.data.Condition;
import com.rithsagea.dnd.api5e.data.DamageType;
import com.rithsagea.dnd.api5e.data.MagicSchool;
import com.rithsagea.dnd.api5e.data.Monster;
import com.rithsagea.dnd.api5e.data.Proficiency;
import com.rithsagea.dnd.api5e.data.Rule;
import com.rithsagea.dnd.api5e.data.RuleSection;
import com.rithsagea.dnd.api5e.data.Spell;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClass;
import com.rithsagea.dnd.api5e.data.classes.DndClassFeature;
import com.rithsagea.dnd.api5e.data.classes.DndSubclass;
import com.rithsagea.dnd.api5e.data.equipment.Equipment;
import com.rithsagea.dnd.api5e.data.races.DndRace;
import com.rithsagea.dnd.api5e.data.races.DndRaceTrait;
import com.rithsagea.dnd.api5e.data.races.DndSubrace;

public class Dnd5eApiTool {
	
	private static String url;
	private static Gson gson;
	
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.registerTypeAdapter(AbilityScore.class, new AbilityScoreAdapter());
		builder.registerTypeAdapter(Skill.class, new SkillAdapter());
		builder.registerTypeAdapter(Proficiency.class, new ProficiencyAdapter());
		builder.registerTypeAdapter(Language.class, new LanguageAdapter());
		builder.registerTypeAdapter(Alignment.class, new AlignmentAdapter());
		builder.registerTypeAdapter(Equipment.class, new EquipmentAdapter());
		builder.registerTypeAdapter(Dnd5eClass.class, new DndClassAdapter());
		builder.registerTypeAdapter(DndSubclass.class, new DndSubclassAdapter());
		builder.registerTypeAdapter(DndClassFeature.class, new DndClassFeatureAdapter());
		builder.registerTypeAdapter(DndRace.class, new DndRaceAdapter());
		builder.registerTypeAdapter(DndSubrace.class, new DndSubraceAdapter());
		builder.registerTypeAdapter(DndRaceTrait.class, new DndTraitAdapter());
		builder.registerTypeAdapter(Spell.class, new SpellAdapter());
		builder.registerTypeAdapter(Monster.class, new MonsterAdapter());
		builder.registerTypeAdapter(Condition.class, new ConditionAdapter());
		builder.registerTypeAdapter(DamageType.class, new DamageTypeAdapter());
		builder.registerTypeAdapter(MagicSchool.class, new MagicSchoolAdapter());
		builder.registerTypeAdapter(Rule.class, new RuleAdapter());
		builder.registerTypeAdapter(RuleSection.class, new RuleSectionAdapter());
		Dnd5eApiTool.gson = builder.create();
	}
	
	public static void setUrl(String url) {
		Dnd5eApiTool.url = url;
	}
	
	public static String get(String header) {
		URL url;
		HttpURLConnection conn;
		StringBuilder builder = new StringBuilder();
		InputStream is = null;
		
		try {
			url = new URL(Dnd5eApiTool.url + header);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			
			is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			for(String line; (line = reader.readLine()) != null;) {
				builder.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null) is.close();
			} catch(IOException e) {
				
			}
		}
		
		return builder.toString();
	}
	
	public static List<String> getIndex(String header) {
		JsonObject obj = gson.fromJson(get(header), JsonObject.class);
		
		List<String> index = new ArrayList<String>();
		for(JsonElement elem : obj.get("results").getAsJsonArray()) {
			index.add(elem.getAsJsonObject().get("index").getAsString());
		}
		
		return index;
	}
	
	public static <T> List<T> getItems(String header, Class<T> clazz) {
		List<String> index = getIndex(header);
		List<T> res = new ArrayList<>();
		
		for(String item : index) {
			System.out.println("Getting: " + header + "/" + item);
			res.add(gson.fromJson(get(header + "/" + item), clazz));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public static List<AbilityScore> getAbilityScores() {
		return getItems("/ability-scores", AbilityScore.class);
	}
	
	public static List<Skill> getSkills() {
		return getItems("/skills", Skill.class);
	}
	
	public static List<Proficiency> getProficiencies() {
		return getItems("/proficiencies", Proficiency.class);
	}
	
	public static List<Language> getLanguages() {
		return getItems("/languages", Language.class);
	}
	
	public static List<Alignment> getAlignments() {
		return getItems("/alignments", Alignment.class);
	}
	
	public static List<Equipment> getEquipment() {
		return getItems("/equipment", Equipment.class);
	}
	
	public static List<Equipment> getMagicEquipment() {
		return getItems("/magic-items", Equipment.class);
	}
	
	public static List<Dnd5eClass> getClasses() {
		return getItems("/classes", Dnd5eClass.class);
	}
	
	public static List<DndSubclass> getSubclasses() {
		return getItems("/subclasses", DndSubclass.class);
	}
	
	public static List<DndClassFeature> getClassFeatures() {
		return getItems("/features", DndClassFeature.class);
	}
	
	public static List<DndRace> getRaces() {
		return getItems("/races", DndRace.class);
	}
	
	public static List<DndSubrace> getSubraces() {
		return getItems("/subraces", DndSubrace.class);
	}
	
	public static List<DndRaceTrait> getTraits() {
		return getItems("/traits", DndRaceTrait.class);
	}
	
	public static List<Spell> getSpells() {
		return getItems("/spells", Spell.class);
	}
	
	public static List<Monster> getMonsters() {
		return getItems("/monsters", Monster.class);
	}
	
	public static List<Condition> getConditions() {
		return getItems("/conditions", Condition.class);
	}
	
	public static List<DamageType> getDamageTypes() {
		return getItems("/damage-types", DamageType.class);
	}
	
	public static List<MagicSchool> getMagicSchools() {
		return getItems("/magic-schools", MagicSchool.class);
	}
	
	public static List<Rule> getRules() {
		return getItems("/rules", Rule.class);
	}
	
	public static List<RuleSection> getRuleSections() {
		return getItems("/rule-sections", RuleSection.class);
	}
}