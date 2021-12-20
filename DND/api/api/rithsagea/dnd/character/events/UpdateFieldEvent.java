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
	
	public static class UpdatePassiveWisdomEvent extends UpdateFieldEvent {
		public UpdatePassiveWisdomEvent(CharacterSheet sheet, int value) {
			super(sheet, value);
		}
	}
	
	public static class UpdateInitiativeEvent extends UpdateFieldEvent {
		public UpdateInitiativeEvent(CharacterSheet sheet, int value) {
			super(sheet, value);
		}
	}
	
	public static class UpdateSpeedEvent extends UpdateFieldEvent {
		public UpdateSpeedEvent(CharacterSheet sheet, int value) {
			super(sheet, value);
		}
	}
}
