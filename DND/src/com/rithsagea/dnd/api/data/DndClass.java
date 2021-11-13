package com.rithsagea.dnd.api.data;

import java.util.List;

import com.rithsagea.dnd.api.data.extra.EquipmentOptions;
import com.rithsagea.dnd.api.data.extra.ProficiencyOptions;

public class DndClass extends DndItem {
	public String name;
	public int hitDie;
	
	public List<ProficiencyOptions> proficiencyOptions;
	public List<String> proficiencies;
	public List<String> savingThrows;
	
	public List<String> startingEquipment;
	public List<EquipmentOptions> equipmentOptions;
}
