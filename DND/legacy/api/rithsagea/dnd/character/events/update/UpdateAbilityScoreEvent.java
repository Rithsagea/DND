package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateAbilityEvent;
import api.rithsagea.dnd.types.enums.Ability;

/**
 * Fired when the character sheet updates an ability score value
 * @author Rithsagea
 *
 */
public class UpdateAbilityScoreEvent extends UpdateAbilityEvent {
	public UpdateAbilityScoreEvent(CharacterSheet sheet, Ability ability, int value) {
		super(sheet, ability, value);
	}
}