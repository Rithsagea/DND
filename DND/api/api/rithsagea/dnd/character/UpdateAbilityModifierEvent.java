package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.enums.Ability;

public class UpdateAbilityModifierEvent extends UpdateAbilityValueEvent {
	
	public UpdateAbilityModifierEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, ability, value);
	}
	
	@Override
	public void finish() {
		getSheet().setAbilityModifier(getAbility(), getValue());
	}
}
