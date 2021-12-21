package api.rithsagea.dnd.types;

import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.character.events.UpdateProficiencyEvent.UpdateEquipmentProficiencyEvent;
import api.rithsagea.dnd.character.events.UpdateProficiencyEvent.UpdateSavingProficiencyEvent;
import api.rithsagea.dnd.character.events.UpdateProficiencyEvent.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Skill;

public class DndClass implements IndexedItem, Listener {
	
	private String id;
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	private Set<Equipment> equipmentProficiencies;
	
	public DndClass(String id) {
		this.id = id;
		
		skillProficiencies = new TreeSet<>();
		savingProficiencies = new TreeSet<>();
		equipmentProficiencies = new TreeSet<>();
	}
	
	protected void addProficiency(Skill skill) {
		skillProficiencies.add(skill);
	}
	
	protected void addProficiency(Ability ability) {
		savingProficiencies.add(ability);
	}
	
	protected void addProficiency(Equipment equipment) {
		equipmentProficiencies.add(equipment);
	}
	
	protected void addProficiencies(Skill...skills) {
		for(Skill skill : skills) addProficiency(skill);
	}
	
	protected void addProficiencies(Ability...abilities) {
		for(Ability ability : abilities) addProficiency(ability);
	}
	
	protected void addProficiencies(Equipment...equipments) {
		for(Equipment equip : equipments) addProficiency(equip);
	}
	
	@EventHandler
	public void onUpdateSkillProficiency(UpdateSkillProficiencyEvent e) {
		skillProficiencies.forEach(e::add);
	}
	
	@EventHandler
	public void onUpdateAbilityProficiency(UpdateSavingProficiencyEvent e) {
		savingProficiencies.forEach(e::add);
	}
	
	@EventHandler
	public void onUpdateEquipmentProficiency(UpdateEquipmentProficiencyEvent e) {
		equipmentProficiencies.forEach(e::add);
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
