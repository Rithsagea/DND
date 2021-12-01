package com.rithsagea.dnd.api.game;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.extras.Speed;

public class CharacterSheet {
	private String name;
	
	private String alignment;
	
	private int hitPoints;
	private int maxHitPoints;
	private String hitDie;
	
	private int armorClass;
	private Speed speed;
	
	private Map<String, Integer> abilityScores;
	private Map<String, Integer> abilityModifiers;
	private Map<String, Integer> savingThrows;
	
	private List<String> languages;
	private List<String> traits; // race specific
	private List<String> features; // class specific
	private List<String> proficiencies;
	
	private int passiveWisdom;
	private int initiative; // dex mod
	
	private String size;
}
