package com.rithsagea.dnd.race.human;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;

public class HumanRace extends DndRace {

	@SuppressWarnings("unchecked")
	public HumanRace() {
		super("Human", 30, Map.ofEntries((Entry<Ability, Integer>[])
				Arrays.asList(Ability.values()).stream()
				.map((Ability a) -> new SimpleEntry<Ability, Integer>(a, 1))
				.collect(Collectors.toList())
				.toArray(new Entry[6])));
	}

}
