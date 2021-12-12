package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.enums.Ability;

public abstract class UpdateAbilityValueEvent extends UpdateValueEvent {
	
	private Ability ability;
	
	public UpdateAbilityValueEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, value);
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
}
