package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.enums.Ability;

public class UpdateAbilityScoreEvent extends UpdateAbilityValueEvent {

	public UpdateAbilityScoreEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, ability, value);
	}
	
	@Override
	public void finish() {
		getSheet().setAbilityScore(getAbility(), getValue());
	}
}
