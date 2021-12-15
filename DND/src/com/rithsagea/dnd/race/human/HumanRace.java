package com.rithsagea.dnd.race.human;

import java.util.Map;
import java.util.TreeMap;

import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.traits.AbilityScoreIncreaseTrait;

public class HumanRace extends DndRace {

	public HumanRace() {
		super("Human", 30);
		
		Map<Ability, Integer> scores = new TreeMap<>();
		for(Ability ability : Ability.values()) scores.put(ability, 1);
		addTrait(new AbilityScoreIncreaseTrait(this, scores));
	}

}
