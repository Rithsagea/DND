package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.enums.Ability;

public class UpdateSavingThrowEvent extends UpdateAbilityValueEvent {
	public UpdateSavingThrowEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, ability, value);
	}
	
	@Override
	public void finish() {
		getSheet().setSavingThrow(getAbility(), getValue());
	}
}
