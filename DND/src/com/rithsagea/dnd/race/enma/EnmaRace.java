package com.rithsagea.dnd.race.enma;

import com.rithsagea.dnd.traits.SuperiorDarkvisionTrait;

import api.rithsagea.dnd.types.DndRace;

public class EnmaRace extends DndRace {

	public EnmaRace() {
		super("Enma", 30);
		addTrait(new SuperiorDarkvisionTrait());
		addTrait(new DivineBeingTrait());
		addTrait(new SupremeJudgeTrait());
		addTrait(new CompletelyLevelHeadedTrait());
	}

}
