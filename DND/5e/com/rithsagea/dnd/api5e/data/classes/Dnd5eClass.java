package com.rithsagea.dnd.api5e.data.classes;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.IndexedItem;
import com.rithsagea.dnd.api5e.data.extra.EquipmentOption;
import com.rithsagea.dnd.api5e.data.extra.EquipmentStack;
import com.rithsagea.dnd.api5e.data.extra.ProficiencyOptions;
import com.rithsagea.dnd.api5e.data.extra.SpellcastingInfo;

public class Dnd5eClass extends IndexedItem {
	public String name;
	public int hitDie;
	
	public List<ProficiencyOptions> proficiencyOptions;
	public List<String> proficiencies;
	public List<String> savingThrows;
	
	public List<EquipmentStack> startingEquipment;
	public List<EquipmentOption> equipmentOptions;
	
	public List<Dnd5eClassLevel> levels; //TODO: procrastinate this
	
	public List<String> subclasses;
	
	public Map<String, Integer> abilityScoreRequirement;
	public int abilityScoreRequirementCount;
	public List<String> multiclassingProficiencies;
	public List<ProficiencyOptions> multiclassingProficiencyOptions;
	
	public List<String> spells;
	public SpellcastingInfo spellcastingInfo;
}
