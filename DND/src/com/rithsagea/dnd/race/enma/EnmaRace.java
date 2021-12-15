package com.rithsagea.dnd.race.enma;

import api.rithsagea.dnd.types.DndRace;

public class EnmaRace extends DndRace {

	public EnmaRace() {
		super("Enma", 30);
		addTrait(new SuperiorDarkvisionTrait());
		addTrait(new DivineBeingTrait());
		addTrait(new SupremeJudgeTrait());
	}

}
