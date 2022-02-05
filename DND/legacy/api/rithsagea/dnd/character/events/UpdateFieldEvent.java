package api.rithsagea.dnd.character.events;

import api.rithsagea.dnd.character.CharacterSheet;

public class UpdateFieldEvent extends UpdateSheetEvent {
	
	private int value;
	
	protected UpdateFieldEvent(CharacterSheet sheet, int value) {
		super(sheet);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void add(int value) {
		this.value += value;
	}
	
	public void multiply(int value) {
		this.value *= value;
	}
	
	public void min(int value) {
		this.value = Math.max(value, this.value);
	}
	
	public void max(int value) {
		this.value = Math.min(value, this.value);
	}
}
