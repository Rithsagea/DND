package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateAbilityEvent;
import api.rithsagea.dnd.types.enums.Ability;

/**
 * Fired when the character sheet updates an ability score modifier
 * @author Rithsagea
 *
 */
public class UpdateAbilityModifierEvent extends UpdateAbilityEvent {
	public UpdateAbilityModifierEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, ability, value);
	}
}
