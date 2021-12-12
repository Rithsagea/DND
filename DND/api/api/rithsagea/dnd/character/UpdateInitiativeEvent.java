package api.rithsagea.dnd.character;

public class UpdateInitiativeEvent extends UpdateValueEvent {

	public UpdateInitiativeEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
	}

	@Override
	public void finish() {
		getSheet().setInitiative(getValue());
	}

}
