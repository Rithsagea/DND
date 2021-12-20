package com.rithsagea.dnd.race.elf;

import api.rithsagea.dnd.character.events.UpdateProficiencyEvent.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;

public class KeenSensesTrait extends Trait {

	@Override
	public String getId() {
		return "KeenSenses";
	}
	
	@EventHandler
	public void onProficiencyUpdate(UpdateSkillProficiencyEvent e) {
		e.add(Skill.PERCEPTION);
	}
	
}
