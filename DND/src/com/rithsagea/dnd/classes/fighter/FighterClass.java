package com.rithsagea.dnd.classes.fighter;

import api.rithsagea.dnd.character.events.UpdateSheetEvent.RefreshSheetEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.DndClass;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Equipment;

public class FighterClass extends DndClass {

	public FighterClass() {
		super("Fighter");
		addProficiencies(Equipment.ARMOR,
				Equipment.SHIELDS,
				Equipment.SIMPLE_WEAPONS,
				Equipment.MARTIAL_WEAPONS);
		
		addProficiencies(
				Ability.STRENGTH,
				Ability.CONSTITUTION);
	}
	
	@EventHandler
	public void onRefresh(RefreshSheetEvent e) {
		
	}

}
