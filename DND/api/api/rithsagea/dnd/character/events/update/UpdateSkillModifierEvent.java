package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateAbilityEvent;
import api.rithsagea.dnd.types.enums.Skill;

public class UpdateSkillModifierEvent extends UpdateAbilityEvent {
	
	private Skill skill;
	
	public UpdateSkillModifierEvent(CharacterSheet sheet, Skill skill, int value) {
		super(sheet, skill.getAbility(), value);
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
}