package com.rithsagea.dnd.api.types;

import java.util.List;

public class DndClassLevel extends IndexedItem {
	public String className;
	
	public int level;
	public int abilityScoreBonus;
	public int proficiencyBonus;
	
	public List<String> features;
}
