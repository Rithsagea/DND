package api.rithsagea.dnd.character;

public class UpdatePassiveWisdomEvent extends UpdateValueEvent {

	public UpdatePassiveWisdomEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}

	@Override
	public void finish() {
		getSheet().setPassiveWisdom(getValue());
	}

}
