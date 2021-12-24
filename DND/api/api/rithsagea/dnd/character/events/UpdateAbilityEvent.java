package api.rithsagea.dnd.character.events;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.enums.Ability;

public class UpdateAbilityEvent extends UpdateFieldEvent {

	private Ability ability;
	
	protected UpdateAbilityEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, value);
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
}
