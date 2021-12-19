package com.rithsagea.dnd.race.enma;

import api.rithsagea.dnd.character.UpdateProficiencyEvent.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;

public class SupremeJudgeTrait extends Trait {

	@Override
	public String getId() {
		return "SupremeJudge";
	}
	
	@EventHandler
	public void onUpdateProficiency(UpdateSkillProficiencyEvent e) {
		e.addAll(Skill.INSIGHT,
				Skill.INTIMIDATION);
	}
}
