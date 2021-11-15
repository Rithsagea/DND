package com.rithsagea.dnd.api.data.races;

import java.util.List;

import com.rithsagea.dnd.api.data.DndItem;
import com.rithsagea.dnd.api.data.extra.AbilityBonus;
import com.rithsagea.dnd.api.data.extra.Choice;

public class DndSubrace extends DndItem {
	public String name;
	public String race;
	public String description;
	public List<AbilityBonus> bonuses;
	public List<String> proficiencies;
	public List<String> languages;
	public Choice<String> languageChoices;
	public List<String> traits;
}
