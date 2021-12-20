package api.rithsagea.dnd.character.events;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.EquipmentProficiency;
import api.rithsagea.dnd.types.enums.Skill;

public class UpdateProficiencyEvent<T extends Enum<?> & IndexedItem> extends UpdateSheetEvent {

	private Set<T> proficiencies;
	
	public UpdateProficiencyEvent(CharacterSheet sheet) {
		super(sheet);
		proficiencies = new HashSet<>();
	}
	
	public final void add(T prof) {
		proficiencies.add(prof);
	}
	
	@SafeVarargs
	public final void addAll(T...prof) {
		proficiencies.addAll(Arrays.asList(prof));
	}
	
	public final void remove(T prof) {
		proficiencies.remove(prof);
	}
	
	@SafeVarargs
	public final void removeAll(T...prof) {
		proficiencies.removeAll(Arrays.asList(prof));
	}
	
	public final boolean has(T prof) {
		return proficiencies.contains(prof);
	}
	
	public final Set<T> getProficiencies() {
		return Collections.unmodifiableSet(proficiencies);
	}
	
	public static class UpdateSkillProficiencyEvent extends UpdateProficiencyEvent<Skill> {
		public UpdateSkillProficiencyEvent(CharacterSheet sheet) {
			super(sheet);
		}
	}
	
	public static class UpdateSavingProficiencyEvent extends UpdateProficiencyEvent<Ability> {
		public UpdateSavingProficiencyEvent(CharacterSheet sheet) {
			super(sheet);
		}
	}
	
	public static class UpdateEquipmentProficiencyEvent extends UpdateProficiencyEvent<EquipmentProficiency> {
		public UpdateEquipmentProficiencyEvent(CharacterSheet sheet) {
			super(sheet);
		}
	}
}
