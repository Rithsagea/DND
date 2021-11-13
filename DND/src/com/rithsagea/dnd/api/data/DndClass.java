package com.rithsagea.dnd.api.data;

import java.util.List;

import com.rithsagea.dnd.api.data.extra.EquipmentOption;
import com.rithsagea.dnd.api.data.extra.EquipmentStack;
import com.rithsagea.dnd.api.data.extra.ProficiencyOptions;

public class DndClass extends DndItem {
	public String name;
	public int hitDie;
	
	public List<ProficiencyOptions> proficiencyOptions;
	public List<String> proficiencies;
	public List<String> savingThrows;
	
	public List<EquipmentStack> startingEquipment;
	public List<EquipmentOption> equipmentOptions;
	
	public List<DndClassLevel> levels;
	
	public List<String> subclasses;
	
	//TODO Spells
	//TODO Spellcasting
}
