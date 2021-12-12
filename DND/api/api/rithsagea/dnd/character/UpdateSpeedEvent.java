package api.rithsagea.dnd.character;

public class UpdateSpeedEvent extends UpdateValueEvent {

	public UpdateSpeedEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}

	@Override
	public void finish() {
		getSheet().setSpeed(getValue());
	}

}
