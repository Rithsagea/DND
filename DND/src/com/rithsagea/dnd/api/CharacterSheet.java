package com.rithsagea.dnd.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.AbilityScore;
import com.rithsagea.dnd.api.types.Skill;

public class CharacterSheet {
	
	public String name;
	public String playerName;
	
	public String alignment; //Alignment.class
	
	public int experiencePoints;
	public int level;
	
	public static final int[] EXPERIENCE_TABLE = {
		0,
		300,
		900,
		2700,
		6500,
		14000,
		23000,
		34000,
		48000,
		64000,
		85000,
		100000,
		120000,
		140000,
		165000,
		195000,
		225000,
		265000,
		305000,
		355000
	};
	
	public Map<String, Integer> abilityScores = new HashMap<>(); //AbilityScore.class
	public Map<String, Integer> abilityModifiers = new HashMap<>();
	public Map<String, Integer> savingThrows = new HashMap<>();
	public Map<String, Integer> skills = new HashMap<>(); //Skill.class
	
	public int inspiration;
	public int proficiencyBonus;
	public int passiveWisdom;
	public int initiative;
	
	//Character lore stuffs
	public String background;
	public String personalityTraits;
	public String ideals;
	public String bonds;
	public String flaws;
	
	//UNFINISHED VVV
	
	public String characterClass; // TODO: this includes level, replace this with an object later
	public String characterRace;
	
	//the feature and traits section of the character sheet
	public List<String> features;
	public List<String> traits;

	public String backstory;
//	public String extraTraits; // merge into the above features, traits, objects
	public String treasure; // this is technically inventory, reimplement later
	
	public String skin;
	public String hair;
	public String eyes;
	public int age;
	public int height;
	public int weight;
	
	public int hitPoints;
	public int maxHitPoints;
	public String hitDie; //TODO: maybe represent this as an object
	
	public int armorClass;
	public int speed;
	
	public int failedDeathSaves;
	public int succeededDeathSaves;
	
	public List<String> proficiencies; // TODO: change to new class, should include languages
	public Map<String, Integer> money; // TODO: change to wrapper class or replace string with enum
	public Map<String, Integer> equipment; // TODO: box this, quantitize maybe ahhhh
	
	//MAGIC
	
	public List<Integer> maxSpellSlots;
	public List<Integer> spellSlots;
	public List<List<String>> spells;
	
	public String spellcastingClass;
	public String spellcastingAbility;
	public int spellAttackBonus;
	
	public void calc() {
		for(String key : SourceRegistry.getKeys(AbilityScore.class)) {
			abilityModifiers.put(key, (abilityScores.get(key) - 10) / 2);
			savingThrows.put(key, abilityModifiers.get(key));
		}
		
		passiveWisdom = 10 + abilityModifiers.get("wis"); // TODO: replace with enum or smth
		initiative = abilityModifiers.get("dex");
		
		for(String key : SourceRegistry.getKeys(Skill.class)) {
			Skill skill = SourceRegistry.getItem(key, Skill.class);
			skills.put(key, abilityModifiers.get(skill.abilityScore));
		}
		
		while(level < 20 && experiencePoints >= EXPERIENCE_TABLE[level]) level++;
		
		proficiencyBonus = 2 + (level - 1) / 4;
	}
}
