package api.rithsagea.dnd.character.events.update;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateProficiencyEvent;
import api.rithsagea.dnd.types.enums.Skill;

public class UpdateSkillProficiencyEvent extends UpdateProficiencyEvent<Skill> {

	public UpdateSkillProficiencyEvent(CharacterSheet sheet) {
		super(sheet);
	}

}
