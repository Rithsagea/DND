package api.rithsagea.dnd.character.events.values;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateFieldEvent;

public class UpdatePassiveWisdomEvent extends UpdateFieldEvent {
	public UpdatePassiveWisdomEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}
}
