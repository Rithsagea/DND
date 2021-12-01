package com.rithsagea.dnd.api.game;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.extras.Speed;

public class PlayerCharacterSheet extends CharacterSheet {
	private String playerName;
	
	private int experiencePoints;
	private int level;
	
	private int failedDeathSaves;
	private int succeededDeathSaves;
	
	private int inspiration;
	private int proficiencyBonus;
	
	private String background;
	private String personalityTraits;
	private String ideals;
	private String bonds;
	private String backstory;
	
	private String skin;
	private String hair;
	private String eyes;
	private int age;
	private int height;
	private int weight;
	
	private Map<String, Integer> money;
	private Map<String, Integer> inventory;
	private List<String> equipment;
	
	private String characterClass;
	private CharacterSpellcasting spellcasting;
}
