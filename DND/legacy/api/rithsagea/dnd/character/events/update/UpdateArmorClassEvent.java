package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateFieldEvent;

public class UpdateArmorClassEvent extends UpdateFieldEvent {
	public UpdateArmorClassEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}
}
