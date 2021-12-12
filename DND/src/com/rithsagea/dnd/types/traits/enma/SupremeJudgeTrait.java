package com.rithsagea.dnd.types.traits.enma;

import api.rithsagea.dnd.character.UpdateSkillModifierEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;

public class SupremeJudgeTrait extends Trait {

	@Override
	public String getId() {
		return "SupremeJudge";
	}
	
	@EventHandler
	public void onSkillUpdate(UpdateSkillModifierEvent e) {
		e.getSheet().addProficiency(Skill.INSIGHT);
		e.getSheet().addProficiency(Skill.INTIMIDATION);
	}
}
