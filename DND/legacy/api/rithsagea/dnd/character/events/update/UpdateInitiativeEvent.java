package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateFieldEvent;

public class UpdateInitiativeEvent extends UpdateFieldEvent {
	public UpdateInitiativeEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}
}
