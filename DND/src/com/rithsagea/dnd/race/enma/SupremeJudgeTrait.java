package com.rithsagea.dnd.race.enma;

import api.rithsagea.dnd.character.UpdateAbilityEvent.UpdateSkillModifierEvent;
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
