package com.rithsagea.dnd.api5e.data.classes;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.IndexedItem;

public class Dnd5eClassLevel extends IndexedItem {
	public int level;
	public int abilityScoreBonus;
	public int proficiencyBonus;
	
	public List<String> features;
	public List<Integer> spellcasting; // 0 spells known 1-9 spell slots 10 cantrips known
	public Map<String, Integer> classSpecific;
	
	public String className;
}
