package com.rithsagea.dnd.classes.fighter;

import com.rithsagea.util.event.EventHandler;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateSheetEvent.RefreshSheetEvent;
import api.rithsagea.dnd.types.DndClass;

public class FighterClass extends DndClass {

	public FighterClass() {
		super("Fighter");
	}
	
	@EventHandler
	public void onRefresh(RefreshSheetEvent e) {
		
	}

	@Override
	public void onLoad(CharacterSheet sheet) {
		// TODO Auto-generated method stub
		
	}

}
