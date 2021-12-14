package api.rithsagea.dnd.character;

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
