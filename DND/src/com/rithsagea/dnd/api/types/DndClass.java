package com.rithsagea.dnd.api.types;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.extras.Options;

public class DndClass extends IndexedItem {
	public int hitDie;
	public List<Options> proficiencyOptions;
	public List<String> proficiencies;
	public List<String> savingThrows;
	
	public Map<String, Integer> startingEquipment;
}
