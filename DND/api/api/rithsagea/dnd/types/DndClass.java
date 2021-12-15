package api.rithsagea.dnd.types;

import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.EquipmentProficiency;
import api.rithsagea.dnd.types.enums.Skill;

public class DndClass implements IndexedItem, Listener, Loadable {
	
	private String id;
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	private Set<EquipmentProficiency> equipmentProficiencies;
	
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
	
	protected void addProficiency(EquipmentProficiency equipment) {
		equipmentProficiencies.add(equipment);
	}
	
	public boolean hasProficiency(Skill skill) {
		return skillProficiencies.contains(skill);
	}
	
	public boolean hasProficiency(Ability ability) {
		return savingProficiencies.contains(ability);
	}
	
	public boolean hasProficiency(EquipmentProficiency equipment) {
		return equipmentProficiencies.contains(equipment);
	}
	
	@Override
	public void onLoad(CharacterSheet sheet) {
		skillProficiencies.forEach(sheet::addProficiency);
		savingProficiencies.forEach(sheet::addProficiency);
		equipmentProficiencies.forEach(sheet::addProficiency);
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
