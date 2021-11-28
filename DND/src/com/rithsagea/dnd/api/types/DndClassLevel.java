package com.rithsagea.dnd.api.types;

import java.util.List;

import com.rithsagea.dnd.api.types.extras.SpellcastingSlots;

public class DndClassLevel extends IndexedItem {
	public String classId;
	
	public int level;
	public int abilityScoreBonus;
	public int proficiencyBonus;
	
	public List<String> features;
	
	public SpellcastingSlots spellcasting;
}
