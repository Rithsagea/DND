package com.rithsagea.dnd.api.data.classes;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.data.DndItem;
import com.rithsagea.dnd.api.data.extra.EquipmentOption;
import com.rithsagea.dnd.api.data.extra.EquipmentStack;
import com.rithsagea.dnd.api.data.extra.ProficiencyOptions;
import com.rithsagea.dnd.api.data.extra.SpellcastingInfo;

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
	
	public Map<String, Integer> abilityScoreRequirement;
	public int abilityScoreRequirementCount;
	public List<String> multiclassingProficiencies;
	public List<ProficiencyOptions> multiclassingProficiencyOptions;
	
	public List<String> spells;
	public SpellcastingInfo spellcastingInfo;
}
