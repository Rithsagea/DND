package com.rithsagea.dnd.api.data.races;

import java.util.List;

import com.rithsagea.dnd.api.data.DndItem;
import com.rithsagea.dnd.api.data.extra.AbilityBonus;
import com.rithsagea.dnd.api.data.extra.ProficiencyOptions;

public class DndRace extends DndItem {
	public String name;
	public int speed;
	public List<AbilityBonus> bonuses;
	
	public String alignment;
	public String age;
	public String size;
	public String sizeDescription;
	
	public List<String> proficiencies;
	public ProficiencyOptions proficiencyOptions;
	public List<String> languages;
	public String languageDesc;
	public List<String> traits;
	public List<String> subraces;
}