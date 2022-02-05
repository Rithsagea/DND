package api.rithsagea.dnd.types.classes.features;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.rithsagea.util.DataUtil;
import com.rithsagea.util.choice.Option;
import com.rithsagea.util.event.EventHandler;

import api.rithsagea.dnd.character.events.update.UpdateEquipmentProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateSavingProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.types.classes.AbstractClass;
import api.rithsagea.dnd.types.classes.UniqueFeature;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.choice.IndexedChoice;
import api.rithsagea.dnd.util.choice.OptionedItem;

public class ProficiencyFeature extends UniqueFeature implements OptionedItem {
	
	private Set<Equipment> equipmentProficiencies;
	private Set<Ability> savingProficiencies;
	private Set<Skill> skillProficiencies;
	
	private Option equipmentOption;
	private Option skillOption;
	
	public ProficiencyFeature(AbstractClass parent, Option equipmentOption, Option skillOption,
			Object... proficiencies) {
		super(parent);
		
		equipmentProficiencies = new HashSet<>();
		savingProficiencies = new HashSet<>();
		skillProficiencies = new HashSet<>();
		
		this.equipmentOption = equipmentOption;
		this.skillOption = skillOption;
		
		for(Object obj : proficiencies) {
			if(obj == null) continue;
			
			if(obj instanceof Equipment) equipmentProficiencies.add((Equipment) obj);
			if(obj instanceof Ability) savingProficiencies.add((Ability) obj);
			if(obj instanceof Skill) skillProficiencies.add((Skill) obj);
		}
		
		System.out.println();
	}
	
	public Set<Option> getOptions() {
		Set<Option> set = new LinkedHashSet<>(DataUtil.list(equipmentOption, skillOption));
		set.remove(null);
		return set;
	}

	@Override
	public String getSubId() {
		return "Proficiency";
	}
	
	@EventHandler
	public void onUpdateEquipmentProficiency(UpdateEquipmentProficiencyEvent e) {
		equipmentProficiencies.forEach(e::add);
		equipmentOption.getChosen().stream()
			.map(x -> (Equipment) ((IndexedChoice) x).getItem())
			.forEach(e::add);
	}
	
	@EventHandler
	public void onUpdateSavingProficiency(UpdateSavingProficiencyEvent e) {
		savingProficiencies.forEach(e::add);
	}
	
	@EventHandler
	public void onUpdateSkillProficiency(UpdateSkillProficiencyEvent e) {
		skillProficiencies.forEach(e::add);
		skillOption.getChosen().stream()
			.map(x -> (Skill) ((IndexedChoice) x).getItem())
			.forEach(e::add);
	}
}
