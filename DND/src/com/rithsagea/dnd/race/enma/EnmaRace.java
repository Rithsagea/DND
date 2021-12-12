package com.rithsagea.dnd.race.enma;

import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.traits.SpeedTrait;

public class EnmaRace extends DndRace {

	public EnmaRace() {
		super("Enma");
		addTraits(
				new SpeedTrait(this, 30),
				new SuperiorDarkvisionTrait(),
				new DivineBeingTrait(),
				new SupremeJudgeTrait());
	}

}
