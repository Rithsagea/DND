package com.rithsagea.dnd.api.types;

import java.util.List;

import com.rithsagea.dnd.api.types.extras.ItemStack;
import com.rithsagea.dnd.api.types.extras.MulticlassingInfo;
import com.rithsagea.dnd.api.types.extras.Options;

public class DndClass extends IndexedItem {
	public String name;
	public int hitDie;
	public List<Options> proficiencyOptions;
	public List<String> proficiencies;
	public List<String> savingThrows;
	
	public List<ItemStack> startingEquipment;
	public List<Options> equipmentOptions;
	
	public List<String> subclasses;
	
	public MulticlassingInfo multiclassing;
}
