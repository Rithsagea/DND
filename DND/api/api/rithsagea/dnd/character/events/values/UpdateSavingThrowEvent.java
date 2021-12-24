package api.rithsagea.dnd.character.events.values;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateAbilityEvent;
import api.rithsagea.dnd.types.enums.Ability;

/**
 * Fired when the character sheet updates a saving throw modifier
 * @author Rithsagea
 *
 */
public class UpdateSavingThrowEvent extends UpdateAbilityEvent {
	public UpdateSavingThrowEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, ability, value);
	}
}
