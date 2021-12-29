package api.rithsagea.dnd.character.events.values;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateFieldEvent;

public class UpdateSpeedEvent extends UpdateFieldEvent {
	public UpdateSpeedEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}
}
