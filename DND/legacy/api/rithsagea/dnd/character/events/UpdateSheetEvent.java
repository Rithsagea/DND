package api.rithsagea.dnd.character.events;

import com.rithsagea.util.event.Event;

import api.rithsagea.dnd.character.CharacterSheet;

public class UpdateSheetEvent implements Event {
	
	private CharacterSheet sheet;
	
	public UpdateSheetEvent(CharacterSheet sheet) {
		this.sheet = sheet;
	}
	
	public CharacterSheet getSheet() {
		return sheet;
	}
	
	public static class RefreshSheetEvent extends UpdateSheetEvent {
		public RefreshSheetEvent(CharacterSheet sheet) {
			super(sheet);
		}
	}
}
