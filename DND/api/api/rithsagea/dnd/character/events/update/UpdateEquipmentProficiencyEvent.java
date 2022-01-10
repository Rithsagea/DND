package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateProficiencyEvent;
import api.rithsagea.dnd.types.enums.Equipment;

public class UpdateEquipmentProficiencyEvent extends UpdateProficiencyEvent<Equipment> {

	public UpdateEquipmentProficiencyEvent(CharacterSheet sheet) {
		super(sheet);
	}

}
