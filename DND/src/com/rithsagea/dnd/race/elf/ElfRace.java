package com.rithsagea.dnd.race.elf;

import java.util.Map;

import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.Registry;
import api.rithsagea.dnd.types.enums.Ability;

public class ElfRace extends DndRace {

	public ElfRace() {
		super("Elf", 30, Map.of(Ability.DEXTERITY, 2));
		
		Registry registry = Registry.getInstance();
		addTrait(registry.getTrait("Darkvision"));
		addTrait(registry.getTrait("Trance"));
		addTrait(registry.getTrait("FeyAncestry"));
		addTrait(registry.getTrait("KeenSenses"));
	}

}
