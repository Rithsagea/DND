package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.enums.Skill;

public class UpdateSkillModifierEvent extends UpdateValueEvent {
	
	private Skill skill;
	
	public UpdateSkillModifierEvent(CharacterSheet sheet, Skill skill, int value) {
		super(sheet, value);
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	@Override
	public void finish() {
		getSheet().setSkillModifier(skill, getValue());
	}
}
