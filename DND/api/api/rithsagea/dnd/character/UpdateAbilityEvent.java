package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Skill;

public class UpdateAbilityEvent extends UpdateFieldEvent {

	private Ability ability;
	
	protected UpdateAbilityEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, value);
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public static class UpdateAbilityScoreEvent extends UpdateAbilityEvent {
		public UpdateAbilityScoreEvent(CharacterSheet sheet, Ability ability, int value) {
			super(sheet, ability, value);
		}
	}
	
	public static class UpdateAbilityModifierEvent extends UpdateAbilityEvent {
		public UpdateAbilityModifierEvent(CharacterSheet sheet, Ability ability, int value) {
			super(sheet, ability, value);
		}
	}
	
	public static class UpdateSavingThrowEvent extends UpdateAbilityEvent {
		public UpdateSavingThrowEvent(CharacterSheet sheet, Ability ability, int value) {
			super(sheet, ability, value);
		}
	}
	
	public static class UpdateSkillModifierEvent extends UpdateAbilityEvent {
		
		private Skill skill;
		
		public UpdateSkillModifierEvent(CharacterSheet sheet, Skill skill, int value) {
			super(sheet, skill.getAbility(), value);
			this.skill = skill;
		}
		
		public Skill getSkill() {
			return skill;
		}
	}
}
