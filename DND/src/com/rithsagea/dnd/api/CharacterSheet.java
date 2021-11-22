package com.rithsagea.dnd.api;

import java.util.List;
import java.util.Map;

public class CharacterSheet {
	
	public String name;
	public String playerName;
	
	public String alignment; //Alignment.class
	
	public Map<String, Integer> abilityScores;
	
	//UNFINISHED VVV
	
	public String characterClass; // TODO: this includes level, replace this with an object later
	public String characterRace;
	
	public String background;
	public String personalityTraits;
	public String ideals;
	public String bonds;
	public String flaws;
	public String featuresAndTraits;

	public String backstory;
	public String extraTraits; // not sure if this is necessary
	public String treasure; // this is technically inventory, reimplement later
	
	public String skin;
	public String hair;
	public String eyes;
	public int age;
	public int height;
	public int weight;
	
	public int experiencePoints;
	public int level;
	
	public Map<String, Integer> abilityModifiers; //TODO: these might not need to be transient
	public Map<String, Integer> savingThrows;
	public Map<String, Integer> skills;
	
	public int inspiration;
	public int proficiencyBonus;
	
	public int hitPoints;
	public int maxHitPoints;
	public String hitDie; //TODO: maybe represent this as an object
	
	public int initiative;
	public int armorClass;
	public int speed;
	
	public int failedDeathSaves;
	public int succeededDeathSaves;
	
	public int passiveWisdom;
	
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
		for(String ability : abilityScores.keySet()) {
			abilityModifiers.put(ability, (abilityScores.get(ability) - 10) / 2);
		}
		
		passiveWisdom = 10 + abilityModifiers.get("wisdom"); // TODO: replace with enum or smth
		initiative = abilityModifiers.get("dexterity");
	}
}
