package api.rithsagea.dnd.character;

import api.rithsagea.dnd.event.Event;

public abstract class UpdateValueEvent implements Event {
	private CharacterSheet sheet;
	private int value;
	
	public UpdateValueEvent(CharacterSheet sheet, int value) {
		this.sheet = sheet;
		this.value = value;
	}
	
	public CharacterSheet getSheet() {
		return sheet;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
