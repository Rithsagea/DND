package com.rithsagea.dnd.api5e.data;

import java.util.List;

import com.google.gson.JsonElement;

public class Monster extends IndexedItem {
	public String name;
	public String size;
	public String type;
	public String subtype;
	public String alignment;
	public int armorClass;
	public int hitPoints;
	public String hitDice;
	public List<String> forms;
	public JsonElement speed;
	
	public int strength;
	public int dexterity;
	public int constitution;
	public int intelligence;
	public int wisdom;
	public int charisma;
	
	public List<String> proficiencies;
	public List<String> damageVulnerabilities;
	public List<String> damageResistances;
	public List<String> damageImmunities;
	public List<String> conditionImmunities;
	
	public JsonElement senses;
	public String languages;
	public double challengeRating;
	
	public List<JsonElement> specialAbilities;
	public List<JsonElement> actions;
	public List<JsonElement> legendaryActions;
}
