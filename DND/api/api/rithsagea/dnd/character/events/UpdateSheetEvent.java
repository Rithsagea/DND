package api.rithsagea.dnd.character.events;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.event.Event;

public class UpdateSheetEvent implements Event {
	
	private CharacterSheet sheet;
	
	public UpdateSheetEvent(CharacterSheet sheet) {
		this.sheet = sheet;
	}
	
	public CharacterSheet getSheet() {
		return sheet;
	}
}
