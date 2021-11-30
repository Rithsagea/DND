package com.rithsagea.dnd.api.types;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.extras.Options;

public class DndSubrace extends IndexedItem {
	public String name;
	public String race;
	public String description;
	
	public Map<String, Integer> bonuses;
	public List<String> proficiencies;
	public List<String> languages;
	public List<String> traits;
	
	public Options languageChoices;
	
}
