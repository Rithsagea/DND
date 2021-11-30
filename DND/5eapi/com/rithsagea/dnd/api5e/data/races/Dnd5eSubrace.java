package com.rithsagea.dnd.api5e.data.races;

import java.util.List;

import com.rithsagea.dnd.api.types.IndexedItem;
import com.rithsagea.dnd.api5e.data.extra.AbilityBonus;
import com.rithsagea.dnd.api5e.data.extra.Choice;

public class Dnd5eSubrace extends IndexedItem {
	public String name;
	public String race;
	public String description;
	public List<AbilityBonus> bonuses;
	public List<String> proficiencies;
	public List<String> languages;
	public Choice<String> languageChoices;
	public List<String> traits;
}
