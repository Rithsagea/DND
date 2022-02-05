package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateProficiencyEvent;
import api.rithsagea.dnd.types.enums.Ability;

public class UpdateSavingProficiencyEvent extends UpdateProficiencyEvent<Ability> {
	public UpdateSavingProficiencyEvent(CharacterSheet sheet) {
		super(sheet);
	}
}
